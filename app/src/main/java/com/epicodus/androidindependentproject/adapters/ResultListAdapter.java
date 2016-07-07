package com.epicodus.androidindependentproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidindependentproject.R;
import com.epicodus.androidindependentproject.models.Brewery;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResultListAdapter extends RecyclerView.Adapter<ResultListAdapter.BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public ResultListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public ResultListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ResultListAdapter.BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.breweryImageView) ImageView mBreweryImageView;
        @Bind(R.id.breweryNameTextView) TextView mNameTextView;
        @Bind(R.id.addressTextView) TextView mAddressTextView;
        @Bind(R.id.localityTextView) TextView mLocalityTextView;

        private Context mContext;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindBrewery(Brewery brewery) {
            mNameTextView.setText(brewery.getName());
            mAddressTextView.setText(brewery.getAddress());
            mLocalityTextView.setText(brewery.getLocality());
        }
    }
}