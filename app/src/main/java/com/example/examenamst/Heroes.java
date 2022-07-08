package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Heroes extends AppCompatActivity {
    private RequestQueue mQueue;
    String token = "7680606351981757";
    String valor = "";
    JSONArray resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        mQueue = Volley.newRequestQueue(this);
        Intent busqueda = getIntent();
        this.valor = (String) busqueda.getExtras().get("busqueda");
        System.out.println("Hay valor aqui");
        solicitudHeroes();


    }

    private void solicitudHeroes(){
        String url_temp = "https://www.superheroapi.com/api.php/7680606351981757/search/"+valor;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        try {
                            resp = response.getJSONArray("results");
                            PresentacionHeroes(resp);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        mQueue.add(request);
    }

    private void PresentacionHeroes(JSONArray resp){
//        int numero = resp.length();
//        System.out.println("cantidad :");
        Toast toast = Toast.makeText(this, "Valores "+ resp.length(), Toast.LENGTH_LONG);
        toast.show();
    }


}