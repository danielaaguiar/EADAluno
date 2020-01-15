package com.eda.eadaluno.activity.aulas;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.eda.eadaluno.Config.Config;
import com.eda.eadaluno.R;
import com.eda.eadaluno.activity.ActivityForum;
import com.eda.eadaluno.activity.ActivityPontuacao;
import com.eda.eadaluno.adapter.LicaoRecyclerViewAdapter;
import com.eda.eadaluno.model.Licao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ActivityLicao extends AppCompatActivity implements YouTubePlayer.OnInitializedListener, View.OnClickListener{


    private static final String TAG = "LicaoActivity";
    public static final String KEY_LICAO_ID_TAREFA= "licao_ID";
    
    //Firebase
    private FirebaseAuth.AuthStateListener mAuthListener;


    public Licao licao;

    //widgets
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //vars
    private View mParentLayout;
    private ArrayList<Licao> mLicaos = new ArrayList<>();
    private DocumentSnapshot mLastQueriedDocument;
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    public TextView mDescricao;
    public Boolean isFABOpen = false;
    private YouTubePlayerSupportFragment youTubePlayerFragment;

    //widget
    FloatingActionButton fab_pdf;
    FloatingActionButton fab_task;
    FloatingActionButton fab_youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licao);
        mParentLayout = findViewById(android.R.id.content);
        //---------------------------------------------------
        //botao voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //---------------------------------------------------

        fab_task = findViewById(R.id.fab_task);
        fab_pdf = findViewById(R.id.fab_pdf);
        fab_youtube = findViewById(R.id.fab_youtube);
        fab_task.setOnClickListener(this);
        fab_pdf.setOnClickListener(this);
        fab_youtube.setOnClickListener(this);


        FloatingActionButton fab_menu = (FloatingActionButton) findViewById(R.id.fab_menu);
        fab_task = (FloatingActionButton) findViewById(R.id.fab_task);
        fab_pdf  = (FloatingActionButton) findViewById(R.id.fab_pdf);
        fab_youtube = (FloatingActionButton) findViewById(R.id.fab_youtube);
        fab_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });



        //---------------------------------------------------
        //bARRA DE NAVEGACAO INFERIROR

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);

        //navegacao
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(ActivityLicao.this, ActivityHabilidade.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(ActivityLicao.this, ActivityPontuacao.class);
                        startActivity(intent2);
                        break;


                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(ActivityLicao.this, ActivityForum.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }});



       /* YouTubePlayerSupportFragment youTubeView;
        youTubeView = (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, (YouTubePlayer.OnInitializedListener) this);*/

        mDescricao = (TextView) findViewById(R.id.descricao);
        //mName_licao = (TextView) findViewById(R.id.licao_name);

        //Recupero a activity
        Intent intent = getIntent();
        mDescricao.setText(intent.getStringExtra(LicaoRecyclerViewAdapter.KEY_LICAO_DESCRICAO));
       // mName_licao.setText(intent.getStringExtra(LicaosActivity.KEY_LESSON_NAME));

    }

    // ---------------------------------------------------------------------------

    private void showFABMenu(){
        isFABOpen=true;
        fab_task.animate().translationY(-getResources().getDimension(R.dimen.standard_1));
        fab_pdf.animate().translationY(-getResources().getDimension(R.dimen.standard_2));
        fab_youtube.animate().translationY(-getResources().getDimension(R.dimen.standard_3));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fab_task.animate().translationY(0);
        fab_pdf.animate().translationY(0);
        fab_youtube.animate().translationY(0);
    }
    // ---------------------------------------------------------------------------
    //BOTAO VOLTAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar

        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
               startActivity(new Intent(this, ActivityLicoes.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
    // ---------------------------------------------------------------------------
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {

            Intent intent = getIntent();
           // String yt = intent.getStringExtra(LicaosActivity.KEY_LESSON_YOUTUBE);
           // player.cueVideo(yt); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }
    // ---------------------------------------------------------------------------
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
           // String error = String.format(getString(R.string.player_error), errorReason.toString());
           // Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
    // ---------------------------------------------------------------------------
/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }*/
    // ---------------------------------------------------------------------------
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
    // ---------------------------------------------------------------------------
    private void makeSnackBarMessage(String message){
        Snackbar.make(mParentLayout, message, Snackbar.LENGTH_SHORT).show();
    }
    // ---------------------------------------------------------------------------
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.fab_pdf:{
                Snackbar.make(view, "Boa leitura!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Recupero a activity
                Intent intent = getIntent();
                String licao_pdf = intent.getStringExtra(LicaoRecyclerViewAdapter.KEY_LICAO_PDF);
                Uri uri = Uri.parse(licao_pdf);
                Intent intent_nova = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent_nova);

                break;
            }
            case R.id.fab_task:{

                Snackbar.make(view, "Boa tarefa!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent0 = getIntent();
                String licao_id = intent0.getStringExtra(LicaoRecyclerViewAdapter.KEY_LICAO_ID);
                Intent intent1 = new Intent(this, ActivityTarefa.class);
                intent1.putExtra(KEY_LICAO_ID_TAREFA, licao_id);
                startActivity(intent1);

                break;
            }
            case R.id.fab_youtube:{
                Snackbar.make(view, "Boa aula!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Recupero a activity
                Intent intent = getIntent();
                String licao_YOUTUBE = intent.getStringExtra(LicaoRecyclerViewAdapter.KEY_LICAO_YOUTUBE);
                Uri uri = Uri.parse(licao_YOUTUBE);
                Intent intent_nova = new Intent(Intent.ACTION_VIEW);
                intent_nova.setData(uri);
                intent.setPackage("com.google.android.youtube");
                startActivity(intent_nova);

            }
        }
    }



}




