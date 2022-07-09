package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class Habilidades extends AppCompatActivity {
    String nombre = "";
    JSONArray rq;
    String valor = "";
    private RequestQueue mQueue;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habilidades);
        mQueue = Volley.newRequestQueue(this);
        Intent IdName = getIntent();
        nombre = (String)IdName.getExtras().get("nombre");
        valor = (String)IdName.getExtras().get("valor");
        TextView nombreH = (TextView) findViewById(R.id.txtNombreH);
        TextView identidad = (TextView) findViewById(R.id.txtIdentidad);
        nombreH.setText(nombre);
        Solicitud();
        identidad.setText((String)IdName.getExtras().get("identidad"));
    }

    private void Solicitud(){
        String url_temp = "https://www.superheroapi.com/api.php/7680606351981757/search/"+valor;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url_temp, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            rq = response.getJSONArray("results");
                            System.out.println(rq);
                            llenarIdentidad(rq);


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


    private void llenarIdentidad(JSONArray resp){
        try {
            for (int i = 0; i < resp.length(); i++) {
                String identidad1 = resp.getJSONObject(i).getString("id").toString().trim();
                System.out.println(identidad1);
                System.out.println(identidad1==id);
                if(identidad1==id){
                    System.out.println("marica yaaa");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}