package com.codepath.recyclerviewlab.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.codepath.recyclerviewlab.models.Article;
import com.codepath.recyclerviewlab.R;

import java.util.ArrayList;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Article> articles = new ArrayList();
    private Context context;
    // Define a static int for each view type, loading = showing the loading spinner at the end of the list
    public static final int VIEW_TYPE_LOADING = 0;
    // article = each article that shows up
    public static final int VIEW_TYPE_ARTICLE = 1;
    public static final int VIEW_TYPE_FIRST_PAGE_ARTICLE = 2;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == VIEW_TYPE_ARTICLE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_article_result, parent, false);
            viewHolder = new ArticleViewHolder(view);
        }else if (viewType == VIEW_TYPE_FIRST_PAGE_ARTICLE) {
            View view = LayoutInflater.from(parent.getContext()) .inflate(R.layout.fragment_article_result_first_page, parent, false);
            viewHolder = new FirstPageArticleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_progress, parent, false);
            viewHolder = new LoadingViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Article article = articles.get(position);


        // Choice the right ViewHolder for the movieRecyclerView.
        if (holder.getItemViewType() == VIEW_TYPE_ARTICLE) {
            ArticleViewHolder vh1 = (ArticleViewHolder) holder;
            vh1.bind(article);
        }else if (holder.getItemViewType() == VIEW_TYPE_FIRST_PAGE_ARTICLE){
            FirstPageArticleViewHolder vh3 = (FirstPageArticleViewHolder) holder;
            vh3.firstPageHeader.setText(holder.itemView.getContext().getString(R.string.first_page, articles.get(position).sectionName));
        }else {
            LoadingViewHolder vh2 = (LoadingViewHolder) holder;
            vh2.bind(article);
        }

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
        return articles.size() ;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= articles.size()) {
            return VIEW_TYPE_LOADING;
        } else if ("1".equals(articles.get(position).printPage)){
            return VIEW_TYPE_FIRST_PAGE_ARTICLE;
        } else {
            return VIEW_TYPE_ARTICLE;
        }


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


    public static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ContentLoadingProgressBar progress;

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progress = itemView.findViewById(R.id.progress);
        }

        public  void bind(Article article){
            progress.show();
        }
    }


    // rather than creating a completely new ViewHolder, we can extend the existing ArticleViewHolder
    static class FirstPageArticleViewHolder extends ArticleViewHolder {
        final TextView firstPageHeader;

        FirstPageArticleViewHolder(View view) {
            super(view);
            firstPageHeader = view.findViewById(R.id.first_page_header);
        }


    }
}
