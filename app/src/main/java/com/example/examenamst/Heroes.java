package com.example.examenamst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ListView lista;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes);
        mQueue = Volley.newRequestQueue(this);
        lista = (ListView) findViewById(R.id.listViewId);
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

                        try {
                            resp = response.getJSONArray("results");
                            System.out.println(resp);
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
        Toast toast = Toast.makeText(this, "Valores "+ resp.length(), Toast.LENGTH_LONG);
        toast.show();
        final TextView cantidad = (TextView) findViewById(R.id.txtCantidad);
        cantidad.setText("Resultado "+resp.length());
        ArrayList<String> nombres = new ArrayList<>();

        try {
            for (int i = 0; i < resp.length(); i++) {
                JSONObject name = new JSONObject(resp.getString(i));
                nombres.add(name.getString("name"));
                id = name.getString("id");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nombres);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent puntaje = new Intent(getBaseContext(), Habilidades.class);
                puntaje.putExtra("nombre", nombres.get(position));
                puntaje.putExtra("valor", valor);
                puntaje.putExtra("id", id);
                startActivity(puntaje);
            }
        });


    }


}