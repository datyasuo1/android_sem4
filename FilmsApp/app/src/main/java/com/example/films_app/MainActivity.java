package com.example.films_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.films_app.adapter.FilmAdapter;
import com.example.films_app.adapter.SectionAdapter;
import com.example.films_app.api.ApiManager;
import com.example.films_app.event.MessageEvent;
import com.example.films_app.model.Data;
import com.example.films_app.model.Film;
import com.example.films_app.model.ParentData;
import com.example.films_app.model.Section;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Section> listSection = new ArrayList<>();
    SectionAdapter sectionAdapter = new SectionAdapter();
    FilmAdapter filmAdapter = new FilmAdapter();
    List<List<Film>> listTitle = new ArrayList<>();


    CarouselView carouselView;

    int[] sampleImages = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBanner();
        initView();
    }
    public void initView(){
        RecyclerView rvHome = findViewById(R.id.rvHome);

        callApi();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        sectionAdapter = new SectionAdapter(this,listSection);

        rvHome.setAdapter(sectionAdapter);
        rvHome.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void callApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiManager.SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiManager service = retrofit.create(ApiManager.class);
        service.apiGetListData().enqueue(new Callback<ParentData>() {
            @Override
            public void onResponse(Call<ParentData> call, Response<ParentData> response) {
                ParentData parentData = response.body();
                Data data = parentData.getData();
                listTitle.add(data.getTrending());
                listTitle.add(data.getHot());
                listTitle.add(data.getPopular());
                listTitle.add(data.getUpcoming());
                filmAdapter.reloadData(listTitle.get(0));
                filmAdapter.reloadData(listTitle.get(1));
                filmAdapter.reloadData(listTitle.get(2));
                filmAdapter.reloadData(listTitle.get(3));
                listSection.add(new Section("Trending",listTitle.get(0)));
                listSection.add(new Section("Hot", listTitle.get(1)));
                listSection.add(new Section("Popular", listTitle.get(2)));
                listSection.add(new Section("Upcoming", listTitle.get(3)));
                sectionAdapter.reloadDataSection(listSection);
            }

            @Override
            public void onFailure(Call<ParentData> call, Throwable t) {

            }
        });
    }

    private void initBanner(){
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(sampleImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Log.d("TAG", "onClick: "+position);
            }
        });
    }

    private void goToDetail(Film film) {
       Intent intent = new Intent(this,DetailActivity.class);
       intent.putExtra("FILM",film);
        startActivity(intent);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent.FilmEvent filmEvent) {
        Film film = filmEvent.getFilm();
        Log.d("TAG", "onMessageEvent: "+film.getName());
        goToDetail(film);
    }
}