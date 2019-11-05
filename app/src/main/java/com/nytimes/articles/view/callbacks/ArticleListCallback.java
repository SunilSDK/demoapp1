package com.nytimes.articles.view.callbacks;

import com.nytimes.articles.data.local.entity.RowsItem;


public interface ArticleListCallback {
    void onArticleClicked(RowsItem articleEntity);
}

