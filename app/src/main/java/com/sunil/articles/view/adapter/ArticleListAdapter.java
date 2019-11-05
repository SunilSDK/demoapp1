package com.sunil.articles.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.sunil.articles.data.local.entity.RowsItem;
import com.sunil.articles.databinding.ItemArticleListBinding;
import com.sunil.articles.view.base.BaseAdapter;
import com.sunil.articles.view.callbacks.ArticleListCallback;

import java.util.ArrayList;
import java.util.List;


public class ArticleListAdapter extends  BaseAdapter<ArticleListAdapter.ArticleViewHolder, RowsItem>
implements Filterable{

    private List<RowsItem> articleEntities;

    private List<RowsItem> articleEntitiesFiltered;

    private final ArticleListCallback articleListCallback;

    public ArticleListAdapter(@NonNull ArticleListCallback articleListCallback) {
        articleEntities = new ArrayList<>();
        articleEntitiesFiltered = new ArrayList<>();
        this.articleListCallback = articleListCallback;
    }

    @Override
    public void setData(List<RowsItem> entities) {
        this.articleEntities = entities;
        this.articleEntitiesFiltered = entities;
        Log.d("Sunil ", String.valueOf(entities.size()));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return ArticleViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, articleListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int i) {
        viewHolder.onBind(articleEntitiesFiltered.get(i));
    }

    @Override
    public int getItemCount() {
        return articleEntitiesFiltered.size();
    }

    @Override
    public Filter getFilter() {
         return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    articleEntitiesFiltered = articleEntities;
                } else {
                    List<RowsItem> filteredList = new ArrayList<>();
                    for (RowsItem row : articleEntities) {

                        // name match condition. this might differ depending on your requirement
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getTitle().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    articleEntitiesFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = articleEntitiesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                articleEntitiesFiltered = (ArrayList<RowsItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        private static ArticleViewHolder create(LayoutInflater inflater, ViewGroup parent, ArticleListCallback callback) {
            ItemArticleListBinding itemMovieListBinding = ItemArticleListBinding.inflate(inflater, parent, false);
            return new ArticleViewHolder(itemMovieListBinding, callback);
        }

        final ItemArticleListBinding binding;

        private ArticleViewHolder(ItemArticleListBinding binding, ArticleListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onArticleClicked(binding.getArticle()));
        }

        private void onBind(RowsItem articleEntity) {
            binding.setArticle(articleEntity);
            binding.executePendingBindings();
        }
    }
}
