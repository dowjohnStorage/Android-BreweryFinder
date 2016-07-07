package com.epicodus.androidindependentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Bind(R.id.resultsList) ListView mResultsList;
    @Bind(R.id.searchItemDisplay) TextView mSearchItemDisplay;

    public ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String brewerySearchItem = intent.getStringExtra("brewerySearchItem");
        String brewSearchItem = intent.getStringExtra("brewSearchItem");
        if(brewerySearchItem == null || brewerySearchItem.equals("")) {
            mSearchItemDisplay.setText(brewSearchItem);
        } else if (!brewerySearchItem.equals("")) {
            mSearchItemDisplay.setText(brewerySearchItem);
        } else {
            mSearchItemDisplay.setText("no entry");
        }

        getBreweries(brewerySearchItem);
    }

    private void getBreweries(String location) {
        final BrewerydbService breweryDBService = new BrewerydbService();

        breweryDBService.findLocalBreweries(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mBreweries = breweryDBService.procesResults(response);

                ResultsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] breweryNames = new String[mBreweries.size()];
                        for (int i = 0; i < breweryNames.length; i++) {
                            breweryNames[i] = mBreweries.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ResultsActivity.this,
                                android.R.layout.simple_list_item_1, breweryNames);
                        mResultsList.setAdapter(adapter);

                        for (Brewery brewery : mBreweries) {
                            Log.d(TAG, "Name: " + brewery.getName());
                            Log.d(TAG, "Phone: " + brewery.getPhone());
                            Log.d(TAG, "Website: " + brewery.getWebsite());
                    }
                }
            });
        }
    });
}
}