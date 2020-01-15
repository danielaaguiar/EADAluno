package com.eda.eadaluno.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eda.eadaluno.R;
import com.eda.eadaluno.activity.aulas.ActivityHabilidade;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

/*import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;*/


public class ActivityPontuacao extends AppCompatActivity {
    private static final String TAG = "PontuacaoActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        //TextView title = (TextView) findViewById(R.id.activityTitle1);
        //title.setText("This is ActivityOne");

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent intent1 = new Intent(ActivityPontuacao.this, ActivityHabilidade.class);
                        startActivity(intent1);
                        break;


                    case R.id.navigation_dashboard:
                        Intent intent2 = new Intent(ActivityPontuacao.this, ActivityPontuacao.class);
                        startActivity(intent2);
                        break;

                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(ActivityPontuacao.this, ActivityForum.class);
                        startActivity(intent3);
                        break;
                }


                return false;
            }
        });
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
}
