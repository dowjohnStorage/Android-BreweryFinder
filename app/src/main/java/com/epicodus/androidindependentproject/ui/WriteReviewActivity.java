package com.epicodus.androidindependentproject.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.epicodus.androidindependentproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WriteReviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG = CreateProfileActivity.class.getSimpleName();

    @Bind(R.id.ratingSpinner) Spinner mRatingSpinner;

    private String finalRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        ButterKnife.bind(this);


        //spinner adapter display
        ArrayAdapter<CharSequence> ratingSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.ratingsArray, android.R.layout.simple_spinner_item);
        ratingSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mRatingSpinner.setAdapter(ratingSpinnerAdapter);

        mRatingSpinner.setOnItemSelectedListener(this);
    }

    //required methods for OnItemSelectedListener
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        Log.d(TAG, parent.getItemAtPosition(pos).toString());
        finalRating = parent.getItemAtPosition(pos).toString();
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
