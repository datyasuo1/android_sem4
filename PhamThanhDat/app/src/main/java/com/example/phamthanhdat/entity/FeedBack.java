package com.example.phamthanhdat.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "feedback")

public class FeedBack implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "username")
    public String username;
    @ColumnInfo(name = "email")
    public String email;
    @ColumnInfo(name = "status")
    public String status;
    @ColumnInfo(name = "des")
    public String des;
}
