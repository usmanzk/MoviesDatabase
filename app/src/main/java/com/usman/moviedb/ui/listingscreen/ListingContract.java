package com.usman.moviedb.ui.listingscreen;

import com.usman.moviedb.models.ListingResponse;
import com.usman.moviedb.models.Movies;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by HP on 12/13/2017.
 */

public class ListingContract {

    public interface IListingPresenter{
        void setView(IListingView view);
        void getMovies(int page);
        void filterResults(@NotNull ListingResponse listingData, String minDate, String maxDate);
    }

    public interface IListingView{
        void setDataOnViews(ListingResponse response);
        void setDataInAdapter(ListingResponse response);
        void initializeViews();
        void showProgressDialog();
        void hideProgressDialog();
        void showMessage(String msg);
        void appendDataInAdapter(ListingResponse response);
        void showFilteredMovies(ArrayList<Movies> movies);
        void showToast(String msg);
        void setListeners();
    }
}
