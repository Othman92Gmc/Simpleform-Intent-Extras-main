package com.example.myapplicationtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivityComosantGui extends AppCompatActivity {

    private RadioGroup radioGroup;
    private EditText nom;
    private EditText prenom;
    private CheckBox hack;
    private CheckBox session;
    private Button submit;
    private RadioButton selectRadio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comosant_gui);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Etudiant etudiant = new Etudiant();

                String genreVide = "";
                String saisieVide = "";
                String choixVide = "";


                // Radio Group
                radioGroup = (RadioGroup) findViewById(R.id.groupRadio);
                int selectId = radioGroup.getCheckedRadioButtonId();
                String radioValue;

                if(selectId != -1){
                    selectRadio = (RadioButton) findViewById(selectId);
                    radioValue = selectRadio.getText().toString();
                    etudiant.setGenre(radioValue);
                }else{
                    genreVide = "vide";
                }

                // Nom
                nom = (EditText) findViewById(R.id.TextNom);
                // Prenom
                prenom = (EditText) findViewById(R.id.TextPrenom);

                if(nom.getText().toString().isEmpty() || prenom.getText().toString().isEmpty()){
                    saisieVide = "vide";
                }else{
                    etudiant.setNom(nom.getText().toString());
                    etudiant.setPrenom(prenom.getText().toString());
                }

                // CheckBox

                hack = (CheckBox) findViewById(R.id.checkHack);
                session = (CheckBox) findViewById(R.id.checkSession);

                if(hack.isChecked() && session.isChecked()){
                    etudiant.setChoix(hack.getText().toString() + " et " + session.getText().toString());
                }else if(hack.isChecked() == false && session.isChecked()){
                    etudiant.setChoix(session.getText().toString());
                }else if(hack.isChecked() && session.isChecked() == false){
                    etudiant.setChoix(hack.getText().toString());
                }else{
                    choixVide = "vide";
                }

                if(genreVide.equals("vide")){
                    Toast.makeText(MainActivityComosantGui.this, "Vous n'avez pas coché le genre", Toast.LENGTH_SHORT).show();
                }else if(saisieVide.equals("vide")){
                    Toast.makeText(MainActivityComosantGui.this, "Remplir tous les champs", Toast.LENGTH_SHORT).show();
                }else if(choixVide.equals("vide")) {
                    Toast.makeText(MainActivityComosantGui.this, "Vous n'avez pas fait votre choix", Toast.LENGTH_SHORT).show();
                }else{
                    // Boite de dialog
                    if (!isFinishing()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityComosantGui.this);
                        builder.setMessage("Voulez-vous enregistrer les informations ?")
                                .setCancelable(false)
                                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // send object

                                        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
                                        intent.putExtra("data", etudiant);
                                        startActivity(intent);
                                    }
                                })
                                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }

            }
        });

    }
}