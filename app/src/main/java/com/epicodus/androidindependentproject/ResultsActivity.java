package com.epicodus.androidindependentproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultsActivity extends AppCompatActivity {

    @Bind(R.id.resultsList) ListView mResultsList;
    private String[] results = {"Jojo's Brew", "Andrews's Brew", "John's Brew", "Coors Light", "Coors Original"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, results);
        mResultsList.setAdapter(adapter);

        mResultsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String result = ((TextView) view).getText().toString();
                Toast.makeText(ResultsActivity.this, result, Toast.LENGTH_LONG).show();
            }
        });
    }
}
