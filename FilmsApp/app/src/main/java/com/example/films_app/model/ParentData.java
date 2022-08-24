package com.example.films_app.model;

public class ParentData {
    Data data;

    public ParentData(Data data) {
        this.data = data;
    }

    public ParentData() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
