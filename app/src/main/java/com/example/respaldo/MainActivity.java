package com.example.respaldo;

import com.example.respaldo.Class.Bank;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retro();
    }
    public void btnEnviar(View view) {
        Intent intent = new Intent(MainActivity.this, Login.class);
        EditText txtUser = (EditText) findViewById(R.id.txtUser);
        EditText txtPass = (EditText) findViewById(R.id.txtPassw);
        Bundle b = new Bundle();
        b.putString("User", txtUser.getText().toString());
        b.putString("Pass", txtPass.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }

    public void Retro() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);
        Call<List<Bank>> reqData = service.getListBank("fccb5e2bf1874697ae4af772ed29d56c");

        reqData.enqueue(new Callback<List<Bank>>() {
            @Override
            public void onResponse(Call<List<Bank>> call, Response<List<Bank>> response) {

                if (!response.isSuccessful()) {
                    Log.i("TAG", "Error" + response.code());
                } else {
                    ArrayList<String> lstBancos = new ArrayList<String>();
                    List<Bank> bankL = response.body();
                    TextView textB = (TextView) findViewById(R.id.txtBanc);
                    for (Bank c : bankL) {
                        lstBancos.add(c.getName() + "\n");
                    }
                    textB.setText("Respuesta Retrofit: \n" + lstBancos);
                }
            }

            @Override
            public void onFailure(Call<List<Bank>> call, Throwable t) {
                Log.e("TAG", "Error: " + t.getMessage());
            }
        });
    }
}