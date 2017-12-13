package com.usman.moviedb.dagger;

import com.usman.moviedb.ApiCalls.MdpApi;
import com.usman.moviedb.application.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HP on 12/13/2017.
 */

@Module
public class NetworkModule {

    @Named("BASE_URL")
    @Provides
    String getBaseUrl() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideConverter() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit getRetrofit(Converter.Factory converter, @Named("BASE_URL") String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides
    @Singleton
    MdpApi getCall(Retrofit retrofit) {

        return retrofit.create(MdpApi.class);
    }

}
