package com.sunil.articles.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sunil.articles.data.local.entity.RowsItem;

import java.util.List;


@Dao
public interface RowItemDao {
    @Query("SELECT * FROM RowsItem")
    LiveData<List<RowsItem>> loadPopularArticles();

    @Insert
    void saveArticles(List<RowsItem> articleEntities);

    @Query("delete from RowsItem")
    void clear();

}
