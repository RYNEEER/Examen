package com.facci.examen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String URL_P = "https://backend-posts.herokuapp.com/student/%7Bcedulaestudiante%7D";
    private static String URL_E = "";
    private ArrayList<Modelo> MateriasArrayList;
    private Adaptador adaptadorEstudiantes;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private TextView cedula, ciudad, pais, descripcion, nombre, apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String id = getIntent().getStringExtra("cedula");
        MateriasArrayList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("CARGANDO");
        progressDialog.show();
        adaptadorEstudiantes = new Adaptador(MateriasArrayList);
        cedula = (TextView)findViewById(R.id.LBLcedula);
        ciudad = (TextView)findViewById(R.id.LBLciudad);
        pais = (TextView)findViewById(R.id.LBLpais);
        descripcion = (TextView)findViewById(R.id.LBLdescripcion);
        nombre = (TextView)findViewById(R.id.LBLnombre);
        apellido = (TextView)findViewById(R.id.LBLapellido);
        URL_E = "https://backend-posts.herokuapp.com/subject/" + id;
        Estudiante(id);
        Materias(URL_E);


    }
    private void Materias(String URl) {
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    Log.e("Hola", response);
                    for (int i= 0; i<jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Modelo materias = new Modelo();
                        materias.setId(object.getString("id"));

                        materias.setNombre(object.getString("nombre"));
                        materias.setEstado(object.getString("estado"));
                        MateriasArrayList.add(materias);
                    }
                    adaptadorEstudiantes = new Adaptador(MateriasArrayList);
                    recyclerView.setAdapter(adaptadorEstudiantes);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void Estudiante(String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_P+ id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    cedula.setText(jsonObject.getString("cedula"));
                    ciudad.setText(jsonObject.getString("ciudad"));
                    pais.setText(jsonObject.getString("pais"));
                    descripcion.setText(jsonObject.getString("descripcion"));
                    nombre.setText(jsonObject.getString("nombre"));
                    apellido.setText(jsonObject.getString("apellido"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}