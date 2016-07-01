package com.epicodus.androidindependentproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.searchBreweriesEntry) EditText mSearchBreweriesEntry;
    @Bind(R.id.searchBreweriesButton) Button mSearchBreweriesButton;
    @Bind(R.id.searchBrewsEntry) EditText mSearchBrewsEntry;
    @Bind(R.id.searchBrewsButton) Button mSearchBrewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchBreweriesButton.setOnClickListener(this);
        mSearchBrewsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchBreweriesButton) {
            String brewerySearchItem = mSearchBreweriesEntry.getText().toString();
            Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
            intent.putExtra("brewerySearchItem", brewerySearchItem);
            startActivity(intent);
        }
        if(v == mSearchBrewsButton) {
            String brewSearchItem = mSearchBrewsEntry.getText().toString();
            Intent intent = new Intent(SearchActivity.this, ResultsActivity.class);
            intent.putExtra("brewSearchItem", brewSearchItem);
            startActivity(intent);
        }
    }
}
