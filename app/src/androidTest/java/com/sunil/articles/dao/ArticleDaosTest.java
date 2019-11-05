package com.sunil.articles.dao;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.sunil.articles.data.local.ArticleDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ArticleDaosTest {

    private ArticleDatabase articleDatabase;

    @Before
    public void init(){
        articleDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                ArticleDatabase.class).build();
    }

    @After
    public void uninit(){
        articleDatabase.close();
    }

}
