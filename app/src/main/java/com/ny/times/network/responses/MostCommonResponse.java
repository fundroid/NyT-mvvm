package com.ny.times.network.responses;

import com.google.gson.annotations.SerializedName;
import com.ny.times.models.Article;

import java.util.List;

public class MostCommonResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("num_results")
    private int num_results;

    @SerializedName("results")
    private List<Article> results;

    public MostCommonResponse(String status, int num_results, List<Article> results) {
        this.status = status;
        this.num_results = num_results;
        this.results = results;
    }

    public List<Article> getResults() {
        return results;
    }
}