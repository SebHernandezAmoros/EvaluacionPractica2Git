package com.example.evaluacionpractica2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.evaluacionpractica2.Adapter.AnimeAdapter;
import com.example.evaluacionpractica2.Entities.Anime;
import com.example.evaluacionpractica2.Service.AnimeService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Anime> animes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6284e8eaa48bd3c40b77c280.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimeService service = retrofit.create(AnimeService.class);
        Call<List<Anime>> call= service.GetAnimes();
        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                if(!response.isSuccessful()){
                    Log.i("APP_VJ20221","Error de aplicacion");
                }else{
                    Log.i("APP_VJ20221","Respuesta correcta");
                    Log.i("APP_VJ20221",new Gson().toJson(response.body()));

                    //Obtencion de lista y envio al adapter
                    animes = response.body();

                    AnimeAdapter adapter = new AnimeAdapter(animes);
                    //Obtencion del recyclerview y envio del adapter
                    RecyclerView rv = findViewById(R.id.rvLista);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Log.i("APP_VJ20221","No pudo conectar con el servicio");
            }
        });
    }
}