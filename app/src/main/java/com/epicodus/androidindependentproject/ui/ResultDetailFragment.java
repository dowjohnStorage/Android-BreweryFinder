package com.epicodus.androidindependentproject.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Brewery;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

import com.epicodus.androidindependentproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment implements View.OnClickListener {
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
        mBrewery = Parcels.unwrap(getArguments().getParcelable("brewery"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mBrewery.getImgURL()).into(mImageLabel);

        mNameLabel.setText(mBrewery.getName());
        mBreweryTypeLabel.setText(mBrewery.getLocationType());
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
        if(mWebsiteLabel == null || mWebsiteLabel.equals("")){

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
                    Uri.parse("geo:0,0?q="
                            + mBrewery.getSearchableAddress()));
            startActivity(mapIntent);
        }
        if (v == mViewBreweryReviewsButton) {
            Intent reviewsIntent = new Intent(getActivity(), ReviewListActivity.class);
            startActivity(reviewsIntent);
        }
    }
}