package com.usman.moviedb.ApiCalls;

import com.google.gson.JsonObject;
import com.usman.moviedb.application.Constants;
import com.usman.moviedb.models.ListingResponse;
import com.usman.moviedb.models.Movies;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HP on 12/13/2017.
 */

public interface MdpApi {

    @GET("movie/now_playing")
    Call<ListingResponse> getLatestMovies(@Query("page") Integer page,@Query("api_key") String api_key);

    @GET("movie/{movie_id}")
    Call<JsonObject> getMoviesDetail(@Path("movie_id") Integer movie_id,@Query("api_key") String api_key);
}
