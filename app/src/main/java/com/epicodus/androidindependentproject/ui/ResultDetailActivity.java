package com.epicodus.androidindependentproject.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.adapters.ResultPagerAdapter;
import com.epicodus.androidindependentproject.models.Brewery;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ResultPagerAdapter adapterViewPager;
    ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_detail);
        ButterKnife.bind(this);

        mBreweries = Parcels.unwrap(getIntent().getParcelableExtra("breweries"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new ResultPagerAdapter(getSupportFragmentManager(), mBreweries);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}