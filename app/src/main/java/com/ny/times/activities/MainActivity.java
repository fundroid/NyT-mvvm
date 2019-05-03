package com.ny.times.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ny.times.R;
import com.ny.times.adapters.ArticleAdapter;
import com.ny.times.models.Article;
import com.ny.times.network.ArticleApi;
import com.ny.times.network.ArticleApiClient;
import com.ny.times.network.responses.MostCommonResponse;
import com.ny.times.utils.ArticleApp;
import com.ny.times.utils.Constants;
import com.ny.times.utils.NetworkUtils;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView noDataTv;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Type listType;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inits();

        if (NetworkUtils.isOnline(this)) {
            callApi("all-sections", "1"); // for section - all-sections, sports, international
        } else {
            Toast.makeText(MainActivity.this, "No network connection. Loaded Offline data", Toast.LENGTH_LONG).show();
            getSavedData();
        }
    }

    private void inits() {
        listType = new TypeToken<List<Article>>() {}.getType();
        gson = new Gson();

        progressBar =  findViewById(R.id.progressBar);
        noDataTv =  findViewById(R.id.textViewNoData);
        recyclerView =  findViewById(R.id.recycler_article);
    }

    private void callApi(String section, String period) {
        ArticleApi apiService = ArticleApiClient.getClient().create(ArticleApi.class);
        Call<MostCommonResponse> call = apiService.getArticleDetails(section, period, Constants.API_KEY);
        call.enqueue(new Callback<MostCommonResponse>() {
            @Override
            public void onResponse(Call<MostCommonResponse> call, Response<MostCommonResponse> response) {

                int statusCode = response.code();

                if (statusCode == 200) {
                    List<Article> newsList = response.body().getResults();
                    saveData(newsList);
                } else {
                    showError("Server Problem. Try again!");
                }
            }

            @Override
            public void onFailure(Call<MostCommonResponse> call, Throwable t) {
                showError(t.getMessage());
            }
        });
    }

    private void saveData(List<Article> newsList) {
        //LIST DATA CONVERT TO GSON STRING
        String gsonStr = gson.toJson(newsList, listType);

        //SAVE IN SHARED-PREFERENCE
        ArticleApp.setArticleList(this, gsonStr);

        getSavedData();
    }

    private void getSavedData() {
        //GET SAVED DATA
        String gsonList = ArticleApp.getArticleList(this);

        if (!gsonList.equals("n/a")) {
            //CONVERT TO LIST
            List<Article> newsList = gson.fromJson(gsonList, listType);

            setupRecycler(newsList);
        } else {
            showError("No saved news to display...!");
        }
    }

    private void setupRecycler(List<Article> dataList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new ArticleAdapter(this, dataList));

        assert dataList != null;
        if (dataList.size() > 0) {
            dataVisible();
        } else {
            showError("No Article..!");
        }
    }

    private void showError(String message) {
        noDataTv.setText(message);

        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        noDataTv.setVisibility(View.VISIBLE);
    }

    private void dataVisible() {
        noDataTv.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

}
