package com.epicodus.androidindependentproject.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidindependentproject.Constants;
import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Brewery;
import com.epicodus.androidindependentproject.models.Review;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.epicodus.androidindependentproject.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ResultDetailFragment.class.getSimpleName();

    @Bind(R.id.breweryImageView) ImageView mImageLabel;
    @Bind(R.id.breweryNameTextView) TextView mNameLabel;
    @Bind(R.id.breweryTypeTextView) TextView mBreweryTypeLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.viewBreweryReviewsButton) Button mViewBreweryReviewsButton;

    private Brewery mBrewery;
    private String breweryId;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private DataSnapshot firebaseSnapShot;
    private ArrayList<Review> reviews = new ArrayList<>();

    public static ResultDetailFragment newInstance(Brewery brewery) {
        ResultDetailFragment resultDetailFragment = new ResultDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("brewery", Parcels.wrap(brewery));
        resultDetailFragment.setArguments(args);
        return resultDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBrewery = Parcels.unwrap(getArguments().getParcelable(Constants.BREWERY_PARAM));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mBrewery.getImgURL()).into(mImageLabel);

        mNameLabel.setText(mBrewery.getName());
        mBreweryTypeLabel.setText(mBrewery.getLocationType());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("There are " + dataSnapshot.getChildrenCount() + " blog posts");
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Review review = postSnapshot.getValue(Review.class);
                    System.out.println(review.getBreweryID());
                    reviews.add(review);
                }
                Log.d(TAG, String.valueOf(reviews.size()));
                Integer totalRatings = 0;
                Double averageRating = 0.0;
                ArrayList<Double> averageArray = new ArrayList<>();
                for (Review review : reviews) {
                    if (review.getBreweryID().equals(mBrewery.getBreweryID())) {
                        Double reviewRating = Double.parseDouble(review.getRating());
                        averageArray.add(reviewRating);
                        totalRatings++;
                    }
                }

                for (Double rating : averageArray) {
                    averageRating += rating;
                }

                Double test = (averageRating.doubleValue() / averageArray.size());

                if (test > -0.001) {
                    mRatingLabel.setText(String.valueOf(test) + "/5");
                } else {
                    mRatingLabel.setText("No Ratings");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mRatingLabel.setText("rating");
        mPhoneLabel.setText(mBrewery.getPhone());
        mAddressLabel.setText(android.text.TextUtils.concat(mBrewery.getAddress(), ", ", mBrewery.getLocality(), ", ", mBrewery.getRegion()));
        mWebsiteLabel.setText(mBrewery.getWebsite());
        mDescriptionLabel.setText(mBrewery.getDescription());

        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mViewBreweryReviewsButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (mWebsiteLabel == null || mWebsiteLabel.equals("")){

        } else{
            if (v == mWebsiteLabel) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mBrewery.getWebsite()));
                startActivity(webIntent);
            }
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mBrewery.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + mBrewery.getSearchableAddress()));
            startActivity(mapIntent);
        }
        if (v == mViewBreweryReviewsButton) {
            Intent reviewsIntent = new Intent(getActivity(), ReviewListActivity.class);
            breweryId = mBrewery.getBreweryID();
            Log.d(TAG, breweryId);
            reviewsIntent.putExtra("breweryId", breweryId);
            startActivity(reviewsIntent);
        }
    }
}