package com.sunil.articles.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sunil.articles.R;
import com.sunil.articles.databinding.ActivityMain1Binding;
import com.sunil.articles.utils.FragmentUtils;
import com.sunil.articles.view.base.BaseActivity;
import com.sunil.articles.view.fragment.ArticleListFragment;

import static com.sunil.articles.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMain1Binding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main1;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {

            FragmentUtils.replaceFragment(this, ArticleListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);
        } else {

        }


    }
}
