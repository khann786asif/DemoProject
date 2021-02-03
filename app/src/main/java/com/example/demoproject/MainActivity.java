package com.example.demoproject;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityTag";
    private RecyclerView recyclerView;
    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getCountriesData();
    }

    private void getCountriesData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/region/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceholderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceholderApi.class);
        Call<List<AsianCountries>> call = jsonPlaceHolderApi.getData();
        call.enqueue(new Callback<List<AsianCountries>>() {
            @Override
            public void onResponse(Call<List<AsianCountries>> call, Response<List<AsianCountries>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: "+response.errorBody());
                    return;
                }
                List<AsianCountries> countries = response.body();
                adapter = new ExampleAdapter(MainActivity.this, countries);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<AsianCountries>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}