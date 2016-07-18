package com.epicodus.androidindependentproject.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Review;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReviewListActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = ReviewListActivity.class.getSimpleName();

    @Bind(R.id.reviewListView) ListView mReviewListView;
    @Bind(R.id.writeReviewButton) Button mWriteReviewButton;
    private String breweryId;
    private FirebaseListAdapter mAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    public DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        breweryId = intent.getStringExtra("breweryId");
        mWriteReviewButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        mAdapter = new FirebaseListAdapter<Review>(this, Review.class, android.R.layout.two_line_list_item, ref) {
            @Override
            protected void populateView(View view, Review review, int position) {
                ((TextView)view.findViewById(android.R.id.text1)).setText("Submitted by: " + review.getReviewer() + " at " + review.getDateSubmitted());
                ((TextView)view.findViewById(android.R.id.text2)).setText(review.getReviewContent());
            }
        };
        mReviewListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == mWriteReviewButton) {
            Intent intent = getIntent();
            breweryId = intent.getStringExtra("breweryId");
            Log.d(TAG, breweryId);
            Intent intentOn = new Intent(ReviewListActivity.this, WriteReviewActivity.class);
            intentOn.putExtra("breweryId", breweryId);
            startActivity(intentOn);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
