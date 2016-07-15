package com.epicodus.androidindependentproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Review;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WriteReviewActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG = CreateProfileActivity.class.getSimpleName();

    @Bind(R.id.ratingSpinner) Spinner mRatingSpinner;
    @Bind(R.id.reviewContentEditText) EditText mReviewContentEditText;
    @Bind(R.id.createReviewButton) Button mCreateReviewButton;
    public String finalRating;

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

        mCreateReviewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void OnClick(View v) {
                String reviewContent = mReviewContentEditText.getText().toString();
                Date date = new Date();
                Review review = new Review("john", reviewContent, date, "ambacht", "10101");
                Intent intent = new Intent(WriteReviewActivity.this, ReviewListActivity.class);
                startActivity(intent);
            }
        });
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
