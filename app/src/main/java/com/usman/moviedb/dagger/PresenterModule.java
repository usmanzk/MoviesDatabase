package com.usman.moviedb.dagger;

import com.usman.moviedb.ui.listingscreen.ListingContract;
import com.usman.moviedb.ui.listingscreen.ListingPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HP on 12/13/2017.
 */

@Module
public class PresenterModule {

    @Provides
    ListingContract.IListingPresenter getListingPresenter(ListingPresenter listingPresenter) {
        return listingPresenter;
    }

}
