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
import com.example.projet.Db.UserCrud;
import com.example.projet.Model.Entree;
import com.example.projet.R;

import java.util.Calendar;

public class addentree extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText date,libelle,description,emplacement;
    int year_y,month_y,day_y;
    Button showdatedialog,add,logout,accueil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addentree);

        date=findViewById(R.id.date1);
        showdatedialog=findViewById(R.id.btndate);
        libelle=findViewById(R.id.libelle1);
        description=findViewById(R.id.description1);
        emplacement=findViewById(R.id.emplacement1);
        logout=findViewById(R.id.logout);
        accueil=findViewById(R.id.accueil);

        add=findViewById(R.id.addentree);
        final UserCrud usercrud=new UserCrud(getApplicationContext());
        final SessionManager sessionManager=new SessionManager(getApplicationContext());

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

        showdatedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdatepickerdialog();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(libelle.getText().toString().equals("") || description.getText().toString().equals("") ||  emplacement.getText().toString().equals("") ||  date.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs",Toast.LENGTH_LONG).show();
                }
                else{
                    Entree e=new Entree();
                    e.setLibelle(libelle.getText().toString());
                    e.setDescription(description.getText().toString());
                    e.setEmplacement(emplacement.getText().toString());
                    e.setDate(date.getText().toString());
                    e.setUser_id(usercrud.getuserid(sessionManager.getusername()));

                    EntreeCrud datasource=new EntreeCrud(getApplicationContext());
                    if(datasource.addentree(e)==true){
                        Toast.makeText(getApplicationContext(),"votre entree a été bien enregistré",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),allentree.class));
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "votre entree n'a pas été enregistré", Toast.LENGTH_LONG).show();
                    }
                }

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
         date.setText(dayOfMonth +"/"+ month +"/"+ year);
    }
}
