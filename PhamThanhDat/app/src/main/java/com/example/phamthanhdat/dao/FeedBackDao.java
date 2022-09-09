package com.example.phamthanhdat.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.phamthanhdat.entity.FeedBack;

import java.util.List;

@Dao
public interface FeedBackDao {
    @Insert
    long insertFeed(FeedBack feedBack);

    @Query("Select * from feedback")
    List<FeedBack> listAll();
}
