package com.nytimes.articles.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.nytimes.articles.data.local.entity.RowsItem;
import com.nytimes.articles.data.remote.Resource;
import com.nytimes.articles.data.remote.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

public class ArticleListViewModel extends ViewModel {
    private final LiveData<Resource<List<RowsItem>>> popularArticles;

    @Inject
    public ArticleListViewModel(ArticleRepository articleRepository) {
        popularArticles = articleRepository.loadPopularArticles();
    }

    public LiveData<Resource<List<RowsItem>>> getPopularArticles() {
        return popularArticles;
    }
}
