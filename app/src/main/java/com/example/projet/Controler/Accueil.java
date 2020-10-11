package com.example.projet.Controler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projet.Db.SessionManager;
import com.example.projet.Db.UserCrud;
import com.example.projet.R;

public class Accueil extends AppCompatActivity {

    Button add,showentrees,logout;
    TextView titre,titre2;
    ImageView bgimage;
    Animation bottomanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        add=findViewById(R.id.addentree);
        showentrees=findViewById(R.id.allentree);
        logout=findViewById(R.id.logout);
        titre=findViewById(R.id.titre2);
        titre2=findViewById(R.id.textView5);
        bgimage=findViewById(R.id.bgapp);
        bottomanim= AnimationUtils.loadAnimation(this, R.anim.bottomanim);
        bgimage.animate().translationY(-1400).setDuration(1200).setStartDelay(400);
        titre.animate().translationY(-780).alpha(0).setDuration(900).setStartDelay(400);
        titre2.animate().translationY(-680).alpha(0).setDuration(900).setStartDelay(400);
        final SessionManager sessionmanager=new SessionManager(getApplicationContext());

        add.setAnimation(bottomanim);
        showentrees.setAnimation(bottomanim);


         UserCrud usercrud=new UserCrud(getApplicationContext());
        //get username
        titre.setText(sessionmanager.getusername());



        //logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setTitle("se déconnecter");
                builder.setMessage("Voulez-vous vraiment vous déconnecter ?");

                //positive action
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionmanager.setlogin(false);
                        sessionmanager.setusername("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });

                //negative action

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                //initialiser aler dialog
                AlertDialog alertDialog=builder.create();
                //show alert dialog
                alertDialog.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),addentree.class);
                startActivity(it);
            }
        });

        showentrees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getApplicationContext(),allentree.class);
                startActivity(it);
            }
        });
    }
}
