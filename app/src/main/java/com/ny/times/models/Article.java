package com.ny.times.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.ny.times.BR;

import java.io.Serializable;

public class Article extends BaseObservable implements Serializable {

    @SerializedName("title")
    @Bindable
    private String title;

    @SerializedName("byline")
    @Bindable
    private String byline;

    @SerializedName("published_date")
    @Bindable
    private String published_date;

    public Article(String byline, String title, String published_date) {
        this.title = title;
        this.byline = byline;
        this.published_date = published_date;

        notifyPropertyChanged(BR.title);
        notifyPropertyChanged(BR.byline);
        notifyPropertyChanged(BR.published_date);
    }

    public String getTitle() {
        return title;
    }

    public String getByline() {
        return byline;
    }

    public String getPublished_date() {
        return published_date;
    }
}