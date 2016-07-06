package com.epicodus.androidindependentproject;

import android.util.Log;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BrewerydbService {
    public static final String TAG = ResultsActivity.class.getSimpleName();
    public static void findLocalBreweries(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
        urlBuilder.addPathSegments(Constants.BREWERYDB_LOCATIONS_PARAMETER);
        urlBuilder.addQueryParameter(Constants.BREWERYDB_POSTALCODE_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.APP_KEY);
        String url = urlBuilder.build().toString();
        Log.d(TAG, url);

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}

//curl "http://api.brewerydb.com/v2/locations?postalCode=97124&key=<key>"

//http://api.brewerydb.com/v2/locations/postalCode?code=606905008303?key=[KEY]