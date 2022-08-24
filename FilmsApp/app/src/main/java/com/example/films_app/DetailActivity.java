package com.example.films_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.films_app.model.Film;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.films_detail);

        Film film = (Film) getIntent().getSerializableExtra("FILM");
        TextView tvTitle = findViewById(R.id.tvTitle);
        ImageView ivFilm = findViewById(R.id.ivFilm);
        tvTitle.setText(film.getName());
        Glide.with(this).load(film.getThumbnail()).into(ivFilm);
    }
}