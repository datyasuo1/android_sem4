package com.example.films_app.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.films_app.R;
import com.example.films_app.event.MessageEvent;
import com.example.films_app.model.Film;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Film> filmList;
    private String section;

    public FilmAdapter(Activity activity, List<Film> filmList) {
        this.activity = activity;
        this.filmList = filmList;
    }

    public FilmAdapter() {

    }

    public void reloadData(List<Film> list){
        filmList = list;
        notifyDataSetChanged();
    }

    public void setSection(String section) {
        this.section = section;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View filmView = activity.getLayoutInflater().inflate(R.layout.item_films,parent,false);
        if (section.equalsIgnoreCase("Hot") || section.equalsIgnoreCase("Upcoming")) {
            filmView = activity.getLayoutInflater().inflate(R.layout.item_films_big, parent,false);
        }
        FilmHolder holder = new FilmHolder(filmView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Film model =filmList.get(position);
        FilmHolder filmHolder = (FilmHolder) holder;
        Glide.with(activity).load(model.getThumbnail()).into(filmHolder.ivCover);
        filmHolder.tvName.setText(model.getName());
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public class FilmHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvName;

        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvName = itemView.findViewById(R.id.tvName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", "onClick: "+getAdapterPosition());
                    Film film = filmList.get(getAdapterPosition());
                    EventBus.getDefault().post(new MessageEvent.FilmEvent(film));
                }
            });
        }
    }
}
