package com.example.respaldo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final TextView txtResult = (TextView) findViewById(R.id.textUserCorrect);
        Bundle bundle = this.getIntent().getExtras();
        user=bundle.getString("User");
        Log.i("TAG", "Error" + user);
        pass=bundle.getString("Pass");
        Log.i("TAG", "Error" + pass);
        String URL = "http://uealecpeterson.net/ws/login.php?usr="+user+"&pass="+pass;
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                txtResult.setText(response);
                /*if (response.trim().equals("Error en Clave del Usuario")) {
                    txtResult.setText("No es");
                }
                else {
                    if (response.trim().equals("Login Correcto!"))
                        txtResult.setText("Si es");
                    else
                    {
                        txtResult.setText("Peor");
                    }
                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "Error" + error);
            }
        })
        {
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
        requestQueue.add(stringRequest);
    }
}