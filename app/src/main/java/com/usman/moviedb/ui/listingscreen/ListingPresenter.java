package com.usman.moviedb.ui.listingscreen;

import android.support.annotation.NonNull;

import com.usman.moviedb.ApiCalls.MdpApi;
import com.usman.moviedb.application.Constants;
import com.usman.moviedb.models.ListingResponse;
import com.usman.moviedb.utility.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HP on 12/13/2017.
 */

public class ListingPresenter implements ListingContract.IListingPresenter {


    private ListingContract.IListingView view;

    @Inject
    MdpApi mdpApi;

    @Inject
    public ListingPresenter() {

    }

    @Override
    public void setView(ListingContract.IListingView view) {
        this.view = view;
        view.initializeViews();
    }

    @Override
    public void getMovies(int page) {

        if (page == 1)
            view.showProgressDialog();

        mdpApi.getLatestMovies(page, Constants.API_KEY).enqueue(new Callback<ListingResponse>() {
            @Override
            public void onResponse(@NonNull Call<ListingResponse> call, @NonNull Response<ListingResponse> response) {

                view.hideProgressDialog();

                if (page == 1) {
                    view.setDataOnViews(response.body());
                    view.setDataInAdapter(response.body());
                    view.setListeners();
                } else  {
                    view.appendDataInAdapter(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListingResponse> call, @NonNull Throwable t) {
                view.hideProgressDialog();
                view.showMessage("Something went wrong");
            }
        });
    }

    @Override
    public void filterResults(@NotNull ListingResponse listingData, String minDate, String maxDate) {

        Date min = Utils.getDateFromString(minDate);
        Date max = Utils.getDateFromString(maxDate);

        if (min.after(max))
            view.showToast("Date Range is Invalid");
        else {
            view.showFilteredMovies(listingData.filterMovies(min, max));
        }

    }

}
