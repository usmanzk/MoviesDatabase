package com.usman.moviedb.dagger;

import com.usman.moviedb.ui.BaseActivity;
import com.usman.moviedb.ui.listingscreen.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HP on 12/13/2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(MainActivity activity);
}
