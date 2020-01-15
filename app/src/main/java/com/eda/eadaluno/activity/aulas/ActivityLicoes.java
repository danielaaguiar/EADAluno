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
import com.eda.eadaluno.adapter.LicaoRecyclerViewAdapter;
import com.eda.eadaluno.model.Licao;
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


public class ActivityLicoes extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "LicaoActivity";

    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;


    //widgets
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //vars
    private View mParentLayout;
    private DocumentSnapshot mLastQueriedDocument;
    private ArrayList<Licao> mLicaos = new ArrayList<>();
    private LicaoRecyclerViewAdapter mLicaoRecyclerViewAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licoes);
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
                        Intent intent1 = new Intent(ActivityLicoes.this, ActivityHabilidade.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(ActivityLicoes.this, ActivityPontuacao.class);
                        startActivity(intent2);
                        break;


                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(ActivityLicoes.this, ActivityForum.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }});
        //---------------------------------------------------
        //botao voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        //---------------------------------------------------
        //mFab.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        //---------------------------------------------------

        setupFirebaseAuth();
        initRecyclerView();
        getLicaos();

    }

    //------------------------------------------------------
    private void initRecyclerView(){
        if(mLicaoRecyclerViewAdapter == null){
            mLicaoRecyclerViewAdapter = new LicaoRecyclerViewAdapter(this, mLicaos);
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mLicaoRecyclerViewAdapter);
    }
    //------------------------------------------------------

    //Mostrar
    private void getLicaos(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        CollectionReference licoesCollectionRef = db
                .collection("licoes");


        Query licoesQuery = null;
        if(mLastQueriedDocument != null){
            licoesQuery = licoesCollectionRef
                    //.whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .orderBy("timestamp", Query.Direction.ASCENDING)
                    .startAfter(mLastQueriedDocument);
        }
        else{
            licoesQuery = licoesCollectionRef
                    // .whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .orderBy("timestamp", Query.Direction.ASCENDING);

        }

        licoesQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot document: task.getResult()){
                        Licao licao = document.toObject(Licao.class);
                        mLicaos.add(licao);
//                        Log.d(TAG, "onComplete: got a new licao. Position: " + (mLicaos.size() - 1));
                    }

                    if(task.getResult().size() != 0){
                        mLastQueriedDocument = task.getResult().getDocuments()
                                .get(task.getResult().size() -1);
                    }
                    //contarlicoes();
                    mLicaoRecyclerViewAdapter.notifyDataSetChanged();
                }
                else{
                    makeSnackBarMessage("Query Failed. Check Logs.");
                }
            }
        });
    }

    //------------------------------------------------------
    public void onRefresh() {
        getLicaos();
        mSwipeRefreshLayout.setRefreshing(false);
        //contarlicoes();
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
                    Intent intent = new Intent(ActivityLicoes.this, SignInActivity.class);
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
