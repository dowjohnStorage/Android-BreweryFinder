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
import android.widget.RatingBar;
import android.widget.Spinner;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Review;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WriteReviewActivity extends AppCompatActivity {
    public static final String TAG = WriteReviewActivity.class.getSimpleName();

    @Bind(R.id.reviewRatingBar) RatingBar mReviewRatingBar;
    @Bind(R.id.reviewContentEditText) EditText mReviewContentEditText;
    @Bind(R.id.createReviewButton) Button mCreateReviewButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        ButterKnife.bind(this);

        mCreateReviewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userCreate;
                if (user != null) {
                    userCreate = user.getDisplayName();
                    Intent breweryIdIntent = getIntent();
                    String breweryId = breweryIdIntent.getStringExtra("breweryId");
                    String reviewContent = mReviewContentEditText.getText().toString();
                    Date date = new Date();
                    String rating = String.valueOf(mReviewRatingBar.getRating());
                    Review review = new Review(userCreate, reviewContent, date, rating, breweryId);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.push().setValue(review);
                    Intent intent = new Intent(WriteReviewActivity.this, ReviewListActivity.class);
                    intent.putExtra("breweryId", breweryId);
                    startActivity(intent);
                } else {
                    return;
                }
            }
        });
    }
}
