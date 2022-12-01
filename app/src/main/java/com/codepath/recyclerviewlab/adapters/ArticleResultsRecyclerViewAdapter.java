package com.codepath.recyclerviewlab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.codepath.recyclerviewlab.models.Article;
import com.codepath.recyclerviewlab.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<ArticleResultsRecyclerViewAdapter.ArticleViewHolder> {

    private final List<Article> articles = new ArrayList();
    private Context context;

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_article_result, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.bind(article);
    }

    // This method clears the existing dataset and adds new articles
    public void setNewArticles(List<Article> articleList) {
        articles.clear();
        articles.addAll(articleList);
    }

    // This method adds all articles to the existing dataset
    public void addArticles(List<Article> articleList) {
        articles.addAll(articleList);
    }


    @Override
    public int getItemCount() {
        return articles.size();
    }


    public static class ArticleViewHolder extends RecyclerView.ViewHolder{

        private TextView article_pub_date, article_headline, article_snippet;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            article_pub_date = itemView.findViewById(R.id.article_pub_date);
            article_headline = itemView.findViewById(R.id.article_headline);
            article_snippet = itemView.findViewById(R.id.article_snippet);
        }


        public  void bind(Article article){
            article_pub_date.setText(article.publishDate);
            article_headline.setText(article.headline.main);
            article_snippet.setText(article.snippet);
        }
    }
}
