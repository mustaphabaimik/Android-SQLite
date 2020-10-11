package com.example.projet.Controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.projet.Db.SessionManager;
import com.example.projet.Db.UserCrud;
import com.example.projet.Model.User;
import com.example.projet.R;

public class MainActivity extends AppCompatActivity {

    EditText log;
    EditText pass;
    Button cnx;
    Button register;
    Animation topanim;
    ImageView img;
    SessionManager sessionmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sessionmanager=new SessionManager(getApplicationContext());
        topanim = AnimationUtils.loadAnimation(this, R.anim.topanim);
        img = findViewById(R.id.imageView);
        log = findViewById(R.id.libelle1);
        pass = findViewById(R.id.pass);
        cnx = findViewById(R.id.cnx);
        register=findViewById(R.id.registration);
        img.setAnimation(topanim);





        cnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                User u=new User();
                u.setLogin(log.getText().toString());
                u.setPassword(pass.getText().toString());
                UserCrud datasource=new UserCrud(getApplicationContext());


                if(datasource.checkuser(u)==true){

                    sessionmanager.setlogin(true);
                    sessionmanager.setusername(log.getText().toString());
                    Intent it=new Intent(MainActivity.this,Accueil.class);

                    startActivity(it);

                }
                else{
                    Toast.makeText(getApplicationContext(),"nom utilisateur ou mot de passe incorrect",Toast.LENGTH_LONG).show();

                }
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(MainActivity.this,registration.class);
                startActivity(it);
            }
        });

        if(sessionmanager.getlogin()){
            Intent it=new Intent(MainActivity.this,Accueil.class);
            startActivity(it);
        }
    }
}
