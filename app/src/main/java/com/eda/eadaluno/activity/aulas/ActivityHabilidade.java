package com.eda.eadaluno.activity.aulas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eda.eadaluno.R;
import com.eda.eadaluno.activity.ActivityForum;
import com.eda.eadaluno.activity.ActivityPontuacao;
import com.eda.eadaluno.activity.SignInActivity;
import com.eda.eadaluno.adapter.HabilidadeRecyclerViewAdapter;
import com.eda.eadaluno.model.Habilidade;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ActivityHabilidade extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "HabilidadeActivity";

    //Firebase
   private FirebaseAuth.AuthStateListener mAuthListener;


    //widgets
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //vars
    private View mParentLayout;
    private DocumentSnapshot mLastQueriedDocument;
    private ArrayList<Habilidade> mHabilidades = new ArrayList<>();
    private HabilidadeRecyclerViewAdapter mHabilidadeRecyclerViewAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidade);
        //--------------------------------------------------
        mRecyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        mParentLayout = findViewById(android.R.id.content);
        //---------------------------------------------------

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);

        //navegacao
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(ActivityHabilidade.this, ActivityHabilidade.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(ActivityHabilidade.this, ActivityPontuacao.class);
                        startActivity(intent2);
                        break;


                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(ActivityHabilidade.this, ActivityForum.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }});

       //---------------------------------------------------
        //mFab.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //---------------------------------------------------
        setupFirebaseAuth();
        initRecyclerView();
        getHabilidades();

    }

    //------------------------------------------------------
    private void initRecyclerView(){
        if(mHabilidadeRecyclerViewAdapter == null){
            mHabilidadeRecyclerViewAdapter = new HabilidadeRecyclerViewAdapter(this, mHabilidades);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHabilidadeRecyclerViewAdapter);
    }
    //------------------------------------------------------

    //Mostrar
    private void getHabilidades(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        CollectionReference habilidadesCollectionRef = db
                .collection("habilidades");


        Query habilidadesQuery = null;
        if(mLastQueriedDocument != null){
            habilidadesQuery = habilidadesCollectionRef
                    //.whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .startAfter(mLastQueriedDocument);
        }
        else{
            habilidadesQuery = habilidadesCollectionRef
                   // .whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .orderBy("timestamp", Query.Direction.ASCENDING);

        }

        habilidadesQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot document: task.getResult()){
                        Habilidade habilidade = document.toObject(Habilidade.class);
                        mHabilidades.add(habilidade);
//                        Log.d(TAG, "onComplete: got a new habilidade. Position: " + (mHabilidades.size() - 1));
                    }

                    if(task.getResult().size() != 0){
                        mLastQueriedDocument = task.getResult().getDocuments()
                                .get(task.getResult().size() -1);
                    }
                    //contarhabilidades();
                   mHabilidadeRecyclerViewAdapter.notifyDataSetChanged();
                }
                else{
                    makeSnackBarMessage("Query Failed. Check Logs.");
                }
            }
        });
    }

    //------------------------------------------------------
    public void onRefresh() {
        getHabilidades();
        mSwipeRefreshLayout.setRefreshing(false);
        //contarhabilidades();
    }
    //------------------------------------------------------

    //------------------------------------------------------

    private void makeSnackBarMessage(String message){
        Snackbar.make(mParentLayout, message, Snackbar.LENGTH_SHORT).show();
    }


    //------------------------------------------------------
    // MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.optionSignOut:
                signOut();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut(){
        Log.d(TAG, "signOut: signing out");
        FirebaseAuth.getInstance().signOut();
    }

    //----------------------------- Firebase setup ---------------------------------

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: started.");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Intent intent = new Intent(ActivityHabilidade.this, SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }



}
