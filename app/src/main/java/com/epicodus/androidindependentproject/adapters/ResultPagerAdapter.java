package com.epicodus.androidindependentproject.adapters;

        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;

        import com.epicodus.androidindependentproject.models.Brewery;
        import com.epicodus.androidindependentproject.ui.ResultDetailFragment;

        import java.util.ArrayList;

public class ResultPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Brewery> mBreweries;

    public ResultPagerAdapter(FragmentManager fm, ArrayList<Brewery> breweries) {
        super(fm);
        mBreweries = breweries;
    }

    @Override
    public Fragment getItem(int position) {
        return ResultDetailFragment.newInstance(mBreweries.get(position));
    }

    @Override
    public int getCount() {
        return mBreweries.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mBreweries.get(position).getName();
    }
}
