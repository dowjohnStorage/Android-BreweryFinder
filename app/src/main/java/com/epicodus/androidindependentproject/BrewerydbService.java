package com.epicodus.androidindependentproject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BrewerydbService {
    public static void findLocalBreweries(String location, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BREWERYDB_LOCATIONS_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.APP_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}


//http://api.brewerydb.com/v2/search/upc?code=606905008303?key=[KEY]