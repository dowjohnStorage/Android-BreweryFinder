package com.epicodus.androidindependentproject.services;

import android.util.Log;

import com.epicodus.androidindependentproject.Constants;
import com.epicodus.androidindependentproject.ui.ResultListActivity;
import com.epicodus.androidindependentproject.models.Brewery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BrewerydbService {
    public static final String TAG = BrewerydbService.class.getSimpleName();
    public static void findLocalBreweries(String location, Callback callback) {

        if (location == null || location.equals("")) {
            Log.d(TAG, "Failure in Service");
            return;
        } else if (Character.isLetter(location.charAt(0))) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
            urlBuilder.addPathSegments(Constants.BREWERYDB_LOCATIONS_PARAMETER);
            urlBuilder.addQueryParameter(Constants.BREWERYDB_CITY_PARAMETER, location);
            urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.APP_KEY);
            String url = urlBuilder.build().toString();

            Request request= new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);
        } else {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
            urlBuilder.addPathSegments(Constants.BREWERYDB_LOCATIONS_PARAMETER);
            urlBuilder.addQueryParameter(Constants.BREWERYDB_POSTALCODE_PARAMETER, location);
            urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.APP_KEY);
            String url = urlBuilder.build().toString();

            Request request= new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);
        }
    }
    public ArrayList<Brewery> procesResults(Response response) {
        ArrayList<Brewery> breweries = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject breweryDBJSON = new JSONObject(jsonData);
                JSONArray dataJSON = breweryDBJSON.getJSONArray("data");
                for (int i = 0; i < dataJSON.length(); i++) {
                    JSONObject breweryJSON = dataJSON.getJSONObject(i);
                    String breweryID = breweryJSON.getJSONObject("brewery").getString("id");
                    String name = breweryJSON.optJSONObject("brewery").getString("name");
                    String phone = breweryJSON.optString("phone");
                    String address = breweryJSON.optString("streetAddress");
                    String locationType = breweryJSON.optString("locationType");
                    String description = breweryJSON.optJSONObject("brewery").optString("description");
                    String website = breweryJSON.optString("website");
                    String locality = breweryJSON.optString("locality");
                    String region = breweryJSON.optString("region");
                    JSONObject imgURLObject = breweryJSON.optJSONObject("brewery").optJSONObject("images");
                    if (imgURLObject != null) {
                        String imgURL = imgURLObject.optString("squareMedium");
                        Brewery brewery = new Brewery(breweryID, name, phone, address, locationType, description, website, locality, region, imgURL);
                        breweries.add(brewery);
                    } else {
                        String imgURL = "http://image.shutterstock.com/z/stock-photo-set-of-beer-production-icon-brewery-process-infographic-flat-style-production-beer-brewery-372188656.jpg";
                        Brewery brewery = new Brewery(breweryID, name, phone, address, locationType, description, website, locality, region, imgURL);
                        breweries.add(brewery);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return breweries;
    }

}