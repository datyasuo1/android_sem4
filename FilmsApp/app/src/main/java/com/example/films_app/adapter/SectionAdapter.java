package com.example.films_app.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.films_app.R;
import com.example.films_app.model.Section;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Section> sectionList;

    public SectionAdapter(Activity activity, List<Section> sectionList) {
        this.activity = activity;
        this.sectionList = sectionList;
    }
    public SectionAdapter() {

    }
    public void reloadDataSection(List<Section> sections){
        sectionList = sections;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sectionView =activity.getLayoutInflater().inflate(R.layout.item_section, parent,false);
        SectionHolder holder = new SectionHolder(sectionView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SectionHolder sectionHolder =(SectionHolder) holder;
        //b1:
        Section model = sectionList.get(position);
        //b2:
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        //b3:
        FilmAdapter adapter = new FilmAdapter(activity, model.getListFilm());
        adapter.setSection(model.getTitle());
        //b4:
        sectionHolder.rvSection.setLayoutManager(layoutManager);
        sectionHolder.rvSection.setAdapter(adapter);
        sectionHolder.rvSection.setHasFixedSize(true);
        sectionHolder.tvTitle.setText(model.getTitle());
    }


    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class SectionHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        RecyclerView rvSection;
        public SectionHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            rvSection = itemView.findViewById(R.id.rvSection);
        }
    }

}
