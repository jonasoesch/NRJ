package com.example.mwsproject.activities.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mwsproject.R;

/**
 * A Tab 2 fragment containing a simple view.
 */
public class Tab2Fragment extends Fragment {

    public Tab2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main_tab2, container, false);
        return rootView;
    }
}