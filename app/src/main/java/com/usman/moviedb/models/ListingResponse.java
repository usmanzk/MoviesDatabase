package com.usman.moviedb.models;

import com.google.gson.annotations.SerializedName;
import com.usman.moviedb.utility.Utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by HP on 12/13/2017.
 */

public class ListingResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int total_results;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("results")
    private ArrayList<Movies> movies;

    @SerializedName("dates")
    private ResponseDate date;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movies> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movies> movies) {
        this.movies = movies;
    }

    public ResponseDate getDate() {
        return date;
    }

    public void setDate(ResponseDate date) {
        this.date = date;
    }

    public String getMaxDate() {
        return date.maxDate;
    }

    public String getMinDate() {
        return date.minDate;
    }

    public void setMaxDate(String maxDate) {
        date.maxDate = maxDate;
    }

    public void setMinDate(String minDate) {
         date.minDate = minDate;
    }


    private class ResponseDate {
        @SerializedName("maximum")
        private String maxDate;
        @SerializedName("minimum")
        private String minDate;


    }


    public ArrayList<Movies> filterMovies(Date minDate, Date maxDate) {
        ArrayList<Movies> filteredMovies = new ArrayList<>();

        for (Movies movie : movies) {
            Date date = null;

            date = Utils.getDateFromString(movie.getRelease_date());

            if (date != null && date.before(maxDate) && date.after(minDate)) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }
}
