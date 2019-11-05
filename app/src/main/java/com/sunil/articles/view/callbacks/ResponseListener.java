package com.sunil.articles.view.callbacks;

import com.sunil.articles.data.local.entity.RowsItem;

public interface ResponseListener {

    void onSuccess(RowsItem data);
    void onFailure(String message);
}
