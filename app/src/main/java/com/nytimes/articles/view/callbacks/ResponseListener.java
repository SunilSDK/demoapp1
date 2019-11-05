package com.nytimes.articles.view.callbacks;

import com.nytimes.articles.data.local.entity.RowsItem;

public interface ResponseListener {

    void onSuccess(RowsItem data);
    void onFailure(String message);
}
