package com.example.evaluacionpractica2.Service;

import com.example.evaluacionpractica2.Entities.Anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimeService {
    @GET("animes")
    Call<List<Anime>> GetAnimes();
}
