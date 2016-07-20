package com.epicodus.androidindependentproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.androidindependentproject.Constants;
import com.epicodus.androidindependentproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.searchBreweriesEntry) EditText mSearchBreweriesEntry;
    @Bind(R.id.searchBreweriesButton) Button mSearchBreweriesButton;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSearchBreweriesButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

    }

    @Override
    public void onClick(View v) {
        if(v == mSearchBreweriesButton) {
            String brewerySearchItem = mSearchBreweriesEntry.getText().toString();
            if(!(brewerySearchItem).equals("")) {
                addToSharedPreferences(brewerySearchItem);
            }
            Intent intent = new Intent(SearchActivity.this, ResultListActivity.class);
            intent.putExtra("brewerySearchItem", brewerySearchItem);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}
