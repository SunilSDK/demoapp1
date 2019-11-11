package com.sunil.articles.data.remote.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.sunil.articles.data.local.dao.RowItemDao;
import com.sunil.articles.data.local.entity.RowsItem;
import com.sunil.articles.data.remote.ApiService;
import com.sunil.articles.data.remote.NetworkBoundResource;
import com.sunil.articles.data.remote.Resource;
import com.sunil.articles.data.remote.model.PostResponse;
import com.sunil.articles.view.callbacks.ResponseListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class ArticleRepository {

    private final RowItemDao articleDao;
//    private final ApiService apiService;

    @Inject
    ArticleRepository(RowItemDao dao) {
        this.articleDao = dao;
  //      this.apiService = service;
    }


    public LiveData<Resource<List<RowsItem>>> loadPopularArticles(ApiService apiService) {
        return new NetworkBoundResource<List<RowsItem>, PostResponse>() {

            @Override
            protected void saveCallResult(PostResponse item) {
                articleDao.clear();
                if(null != item)
                    articleDao.saveArticles(item.getRows());
            }

            @NonNull
            @Override
            protected LiveData<List<RowsItem>> loadFromDb() {
                return articleDao.loadPopularArticles();
            }

            @NonNull
            @Override
            protected Call<PostResponse> createCall() {
                return apiService.loadPopularArticles();
            }
        }.getAsLiveData();
    }


    /**
     * This method fetches the HTML comntent from the url and parses it and fills the model
     * @param url url to be fetched
     * @param responseListener callback
     */
    @SuppressLint("CheckResult")
    public void loadArticleDetails(String url, ResponseListener responseListener) {
        RowsItem articleDetails = new RowsItem();
        Observable.fromCallable(() -> {
            Document document = Jsoup.connect(url).get();
            articleDetails.setTitle(document.title());
            articleDetails.setDescription(document.select("p").text());
            return false;
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> responseListener.onSuccess(articleDetails),
                 (error -> responseListener.onFailure(error.getMessage())));

    }
}
