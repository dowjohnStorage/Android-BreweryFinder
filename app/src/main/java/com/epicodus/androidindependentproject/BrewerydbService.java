package com.epicodus.androidindependentproject;

import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BrewerydbService {
    public static void findLocatlBreweries(String location, Callback callback) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BREWERYDB_LOCATIONS_PARAMETER, location);
        urlBuilder.addPathSegment(Constants.APP_KEY);
        String url = urlBuilder.build().toString();
    }

}


//http://api.brewerydb.com/v2/search/upc?code=606905008303?key=[KEY]