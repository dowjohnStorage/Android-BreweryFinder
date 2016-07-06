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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {
    public static final String TAG = ResultsActivity.class.getSimpleName();

    @Bind(R.id.resultsList) ListView mResultsList;
    @Bind(R.id.searchItemDisplay) TextView mSearchItemDisplay;
    private String[] results = {"Jojo's Brew", "Andrews's Brew", "John's Brew", "Coors Light", "Coors Original"};

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


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
        mResultsList.setAdapter(adapter);

        mResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = ((TextView) view).getText().toString();
                Toast.makeText(ResultsActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });

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
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
