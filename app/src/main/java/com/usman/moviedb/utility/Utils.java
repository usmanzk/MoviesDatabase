package com.usman.moviedb.utility;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HP on 12/13/2017.
 */

public class Utils {

    public static Date getDateFromString(String text){

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Date date = null;
        try {
            date = formatter.parse(text);
        } catch (ParseException e) {
            Log.e("Exception", "date parse exception");
        }

        return date;
    }
}
