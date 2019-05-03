package com.ny.times.network;

import com.ny.times.network.responses.MostCommonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleApi {

    @GET("mostpopular/v2/mostviewed/{section}/{period}.json")
    Call<MostCommonResponse> getArticleDetails(@Path("section") String section, @Path("period") String period,
                                            @Query("api-key") String apiKey);
}