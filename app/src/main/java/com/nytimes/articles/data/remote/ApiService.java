package com.nytimes.articles.data.remote;

import com.nytimes.articles.data.remote.model.PostResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    Call<PostResponse> loadPopularArticles();

}
