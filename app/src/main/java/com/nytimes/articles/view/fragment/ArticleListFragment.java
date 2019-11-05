package com.nytimes.articles.view.fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.nytimes.articles.R;
import com.nytimes.articles.common.Constants;
import com.nytimes.articles.data.local.entity.RowsItem;
import com.nytimes.articles.data.remote.Status;
import com.nytimes.articles.databinding.FragmentListArticlesBinding;
import com.nytimes.articles.utils.FragmentUtils;
import com.nytimes.articles.view.adapter.ArticleListAdapter;
import com.nytimes.articles.view.base.BaseFragment;
import com.nytimes.articles.view.callbacks.ArticleListCallback;
import com.nytimes.articles.viewmodel.ArticleListViewModel;


/**
 * The article list fragment which will list the popular articles
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
public class ArticleListFragment extends BaseFragment<ArticleListViewModel, FragmentListArticlesBinding>
        implements ArticleListCallback, SwipeRefreshLayout.OnRefreshListener {

    public static ArticleListFragment newInstance() {
        Bundle args = new Bundle();
        ArticleListFragment fragment = new ArticleListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<ArticleListViewModel> getViewModel() {
        return ArticleListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_articles;
    }

    @Override
    public void onArticleClicked(RowsItem articleEntity) {
        if(null != getActivity()){

        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new ArticleListAdapter(this));

        dataBinding.swiperefreshItems.setOnRefreshListener(this);
        dataBinding.swiperefreshItems.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        dataBinding.swiperefreshItems.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                dataBinding.recyclerView.setAdapter(new ArticleListAdapter(ArticleListFragment.this));
            }
        });


        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getData();


    }

    void getData(){
        viewModel.getPopularArticles()
                .observe(this, listResource -> {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.loginProgress.setVisibility(View.GONE);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dataBinding.swiperefreshItems.isRefreshing()) {
                                    dataBinding.swiperefreshItems.setRefreshing(false);
                                }
                            }
                        }, 1000);

                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onRefresh() {

    }
}
