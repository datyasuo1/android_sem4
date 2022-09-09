package com.example.phamthanhdat;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.phamthanhdat.dao.FeedBackDao;
import com.example.phamthanhdat.entity.FeedBack;

@Database(entities = {FeedBack.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract FeedBackDao feedBackDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null){
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class,"FeedBack.db").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
