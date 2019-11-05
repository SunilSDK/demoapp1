package com.sunil.articles.view.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunil.articles.R;
import com.sunil.articles.data.local.entity.RowsItem;
import com.sunil.articles.data.remote.Status;
import com.sunil.articles.databinding.FragmentListArticlesBinding;
import com.sunil.articles.view.adapter.ArticleListAdapter;
import com.sunil.articles.view.base.BaseFragment;
import com.sunil.articles.view.callbacks.ArticleListCallback;
import com.sunil.articles.viewmodel.ArticleListViewModel;


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
