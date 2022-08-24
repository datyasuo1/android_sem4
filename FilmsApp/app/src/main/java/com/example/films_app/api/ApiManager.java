package com.example.films_app.api;

import com.example.films_app.model.Film;
import com.example.films_app.model.ParentData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String SERVER = "https://springfilm.herokuapp.com";

    @GET("/apifree/home")
    Call<ParentData> apiGetListData();

}
