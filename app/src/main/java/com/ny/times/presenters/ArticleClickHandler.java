package com.ny.times.presenters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ny.times.activities.DetailsActivity;
import com.ny.times.models.Article;

public class ArticleClickHandler {

    private final Context context;

    public ArticleClickHandler(Context context) {
        this.context = context;
    }

    public void onSaveClick(View view, Article article) {
        Intent nextInt = new Intent(view.getContext(), DetailsActivity.class);
        nextInt.putExtra("SELECTED_ARTICLE", article);
        context.startActivity(nextInt);
    }
}
