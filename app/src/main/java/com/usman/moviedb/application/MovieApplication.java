package com.usman.moviedb.application;

import android.app.Application;

import com.usman.moviedb.dagger.AppComponent;
import com.usman.moviedb.dagger.AppModule;
import com.usman.moviedb.dagger.DaggerAppComponent;
import com.usman.moviedb.dagger.NetworkModule;
import com.usman.moviedb.dagger.PresenterModule;

/**
 * Created by HP on 12/13/2017.
 */

public class MovieApplication extends Application {

    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .presenterModule(new PresenterModule())
                .build();

    }

    public AppComponent getComponent() {
        return component;
    }
}
