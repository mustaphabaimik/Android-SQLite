package com.example.projet.Controler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projet.Db.EntreeCrud;
import com.example.projet.Db.SessionManager;
import com.example.projet.Model.Entree;
import com.example.projet.R;

import java.util.List;

public class allentree extends AppCompatActivity {
    ListView listentrees;
    TextView titre3;
    Button logout,accueil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allentree);

        listentrees=findViewById(R.id.listentrees);
        titre3=findViewById(R.id.titre3);
        logout=findViewById(R.id.logout);
        accueil=findViewById(R.id.accueil);
        final EntreeCrud datasource=new EntreeCrud(getApplicationContext());
        final SessionManager sessionManager=new SessionManager(getApplicationContext());

        final List<Entree> entrees=datasource.getallentrees();

        if(entrees.size()>0){
            titre3.setText("Liste des entrees");
        }
        else{
            titre3.setText("vous n'avez aucune entree");
        }
        //entrees= datasource.getallentrees();

        class customadapter extends BaseAdapter {

            Context context;

            public  customadapter(Context context){
                this.context=context;
            }
            @Override
            public int getCount() {
                return entrees.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {

                LayoutInflater inf=getLayoutInflater();
                view= inf.inflate(R.layout.itementree,null,true);


                TextView libelle=view.findViewById(R.id.libelle1);
                TextView description=view.findViewById(R.id.description1);
                TextView date=view.findViewById(R.id.date1);
                TextView emplacement=view.findViewById(R.id.emplacement1);
                Button edit=view.findViewById(R.id.edit);
                Button delete=view.findViewById(R.id.delete);
                final int id=entrees.get(position).getId();


                libelle.setText(entrees.get(position).getLibelle());
                description.setText(entrees.get(position).getDescription());
                date.setText(entrees.get(position).getDate());
                emplacement.setText("à " + entrees.get(position).getEmplacement());

                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                        builder.setTitle("supression");
                        builder.setMessage("voulez vous vraiment supprimer cette entree");
                        //positive action
                        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(datasource.deleteentree(id)){
                                    Toast.makeText(getApplicationContext(),"votre entree a été bien supprimé", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(),allentree.class));
                                }
                            }
                        });
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
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"edit", Toast.LENGTH_LONG).show();
                        Intent it=new Intent(getApplicationContext(),updateentree.class);
                        it.putExtra("id",id);
                        startActivity(it);

                    }
                });

                return view;
            }
        }

        customadapter cs=new customadapter(getApplicationContext());
        final ArrayAdapter<Entree> adapter=new ArrayAdapter<Entree>(getApplicationContext(),android.R.layout.simple_list_item_1,entrees);

        listentrees.setAdapter(cs);

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
}
