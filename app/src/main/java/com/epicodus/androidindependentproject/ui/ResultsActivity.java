package com.epicodus.androidindependentproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.adapters.ResultListAdapter;
import com.epicodus.androidindependentproject.models.Brewery;
import com.epicodus.androidindependentproject.services.BrewerydbService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    private ResultListAdapter mAdapter;

    public ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String brewerySearchItem = intent.getStringExtra("brewerySearchItem");
        String brewSearchItem = intent.getStringExtra("brewSearchItem");

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
                        mAdapter = new ResultListAdapter(getApplicationContext(), mBreweries);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ResultsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}