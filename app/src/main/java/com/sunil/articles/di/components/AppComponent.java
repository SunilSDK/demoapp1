package com.sunil.articles.di.components;

import android.app.Application;

import com.sunil.articles.MainApp;
import com.sunil.articles.di.builder.ActivityBuilderModule;
import com.sunil.articles.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
@Singleton
@Component(modules = {
        AppModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(MainApp mainApp);
}