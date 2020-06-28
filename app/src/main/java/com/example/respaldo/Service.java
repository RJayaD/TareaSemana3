package com.example.respaldo;

import com.example.respaldo.Class.Bank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Service {
    public static final String BASE_URL = "https://api-uat.kushkipagos.com/transfer-subscriptions/v1/";
    @GET("bankList")
    Call<List<Bank>> getListBank(@Header("Public-Merchant-Id") String id);
}

