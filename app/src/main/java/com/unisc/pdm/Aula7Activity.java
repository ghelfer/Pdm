package com.unisc.pdm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Aula7Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7);
    }

    public void aula7ExClick(View view) {
        startActivity(new Intent(this,Aula7ExActivity.class));
    }


    public void ExercicioClick(View view) {
        startActivity(new Intent(this, Aula71Activity.class));
    }

    public void Aula72Click(View view) {
        startActivity(new Intent(this, TrabalhoActivity.class));
    }
}
