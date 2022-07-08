package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ResultadoHeroes(View v){
        final EditText busqueda = (EditText) findViewById(R.id.txtBusqueda);
        String valBusqueda = busqueda.getText().toString();
        Intent heroe = new Intent(getBaseContext(), Heroes.class);
        heroe.putExtra("busqueda", valBusqueda);
        startActivity(heroe);
    }
}