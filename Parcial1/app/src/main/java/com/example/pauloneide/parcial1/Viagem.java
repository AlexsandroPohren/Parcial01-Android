package com.example.pauloneide.parcial1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Viagem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagem);
    }
    public void onClick(View view){

        Intent intent = new Intent(this, Custos.class);

        startActivity(intent);
    }
}
