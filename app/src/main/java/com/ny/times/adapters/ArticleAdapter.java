package com.ny.times.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ny.times.databinding.ArticleDataBinding;
import com.ny.times.models.Article;
import com.ny.times.presenters.ArticleClickHandler;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Article> newsList;
    private LayoutInflater layoutInflater;

    public ArticleAdapter(Context context, List<Article> dataList) {
        this.mContext = context;
        this.newsList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ArticleDataBinding dataBinding = ArticleDataBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(dataBinding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //SET VIEW DATA
        final Article news = newsList.get(position);
        holder.bind(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ArticleDataBinding newsDataBinding;

        ViewHolder(ArticleDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.newsDataBinding = dataBinding;
            newsDataBinding.setHandler(new ArticleClickHandler(mContext));
        }

        void bind(Article news) {
            this.newsDataBinding.setArticle(news);
        }
    }
}