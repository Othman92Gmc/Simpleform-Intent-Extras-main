package com.example.myapplicationtest;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DisplayActivity extends AppCompatActivity {
    private TextView fullName;
    private TextView choix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent intent = getIntent();
        // get data
        Etudiant etudiant = (Etudiant) intent.getSerializableExtra("data");
        fullName = (TextView) findViewById(R.id.fullName);
        choix = (TextView) findViewById(R.id.choix);
        fullName.setText(etudiant.getGenre() + " " + etudiant.getNom() + " " + etudiant.getPrenom());
        choix.setText(etudiant.getChoix());
    }
}