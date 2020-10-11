package com.example.projet.Controler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projet.Db.SessionManager;
import com.example.projet.Db.UserCrud;
import com.example.projet.Model.User;
import com.example.projet.R;

public class registration extends AppCompatActivity {

    EditText nomprenom;
    EditText email;
    EditText username;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        nomprenom=findViewById(R.id.nomPrenom);
        email=findViewById(R.id.email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.pass);
        register=findViewById(R.id.enregistrer);

        final SessionManager sessionManager=new SessionManager(getApplicationContext());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nomprenom.getText().toString().equals("") || email.getText().toString().equals("") || username.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
                }
                else{
                    User u=new User();
                    u.setNomPrenom(nomprenom.getText().toString());
                    u.setEmail(email.getText().toString());
                    u.setLogin(username.getText().toString());
                    u.setPassword(password.getText().toString());

                    UserCrud datasource=new UserCrud(getApplicationContext());
                    if(datasource.getuserid(username.getText().toString())==-1) {
                        if (datasource.adduser(u) == true) {

                            Toast.makeText(getApplicationContext(), "inscription avec success", Toast.LENGTH_LONG).show();
                            Intent it = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(it);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "nom d'utilisateur deja exist", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });





    }
}
