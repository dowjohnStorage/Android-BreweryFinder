package com.epicodus.androidindependentproject.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.androidindependentproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment {


    public ResultDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_detail, container, false);
    }

}
