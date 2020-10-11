package com.example.projet.Controler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projet.Db.EntreeCrud;
import com.example.projet.Db.SessionManager;
import com.example.projet.Model.Entree;
import com.example.projet.R;

import java.util.Calendar;

public class updateentree extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    EditText libelle,description,emplacement,date;
    Button modifier,showdatepicker,logout,accueil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateentree);
        final EntreeCrud datasource=new EntreeCrud(getApplicationContext());

        libelle=findViewById(R.id.newlibelle);
        description=findViewById(R.id.newdescription);
        emplacement=findViewById(R.id.newemplacement);
        date=findViewById(R.id.newdate);
        modifier=findViewById(R.id.modifier);
        showdatepicker=findViewById(R.id.showdatepicker);
        logout=findViewById(R.id.logout);
        accueil=findViewById(R.id.accueil);
        final SessionManager sessionManager=new SessionManager(getApplicationContext());

        showdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdatepickerdialog();
            }
        });


        Bundle bd=getIntent().getExtras();
        final int id=bd.getInt("id");
        Entree e=datasource.getone(id);
        //Toast.makeText(this,"bonjour = "+ e.getLibelle(),Toast.LENGTH_LONG).show();

        libelle.setText(e.getLibelle());
        description.setText(e.getDescription());
        emplacement.setText(e.getEmplacement());
        date.setText(e.getDate());

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(libelle.getText().toString().equals("") || description.getText().toString().equals("") ||  emplacement.getText().toString().equals("") ||  date.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
                }
                else{
                    Entree entree=new Entree();
                    entree.setLibelle(libelle.getText().toString());
                    entree.setDescription(description.getText().toString());
                    entree.setEmplacement(emplacement.getText().toString());
                    entree.setDate(date.getText().toString());
                    if(datasource.updateentree(entree,id)){
                        Toast.makeText(getApplicationContext(),"votre entree a été bien modifié ",Toast.LENGTH_LONG).show();
                        Intent it=new Intent(getApplicationContext(),allentree.class);
                        startActivity(it);
                    }
                }

            }
        });

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
                        sessionManager.setlogin(false);
                        sessionManager.setusername("");
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

        //vers accueil page
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Accueil.class));
            }
        });


    }

    private void showdatepickerdialog(){
        DatePickerDialog datepicker=new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datepicker.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date.setText(dayOfMonth +"/"+month +"/" + year);
    }
}
