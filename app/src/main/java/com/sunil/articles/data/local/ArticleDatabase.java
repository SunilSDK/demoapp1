package com.sunil.articles.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sunil.articles.data.local.dao.RowItemDao;
import com.sunil.articles.data.local.entity.RowsItem;


@Database(entities = {RowsItem.class},exportSchema = false,version = 5)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract RowItemDao rowItemDao();
}