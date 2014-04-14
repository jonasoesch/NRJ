package com.example.mwsproject.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mwsproject.R;

/**
 * A Facts fragment containing a simple view.
 */
public class FactsFragment extends Fragment {

	public FactsFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_main_facts, container, false);
		return rootView;
	}
}