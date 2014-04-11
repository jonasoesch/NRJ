/*
 * Creating a list item view
 */
package com.example.mwsproject.activities;

import com.example.mwsproject.R;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.mwsproject.model.Fact;

public class FactAdapter extends ArrayAdapter<Fact> {

	private ArrayList<Fact> items; // facts list

	public FactAdapter(Context context, int textViewResourceId, ArrayList<Fact> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	// Returns the view for a given fact
	public View getView(int position, View convertView, ViewGroup parent) {

		Log.d("FactAdapter", "Preparing view for list view");
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.activity_main_fact_list_item, null); // The view description is stored in a xml file
		}
		Fact fact = items.get(position); // getting the fact
		if (fact != null) {
			TextView tt = (TextView) v.findViewById(R.id.line1); // Getting the first line text view
			TextView bt = (TextView) v.findViewById(R.id.line2); // Getting the second line text view
			if (tt != null) {
				tt.setText(fact.getName()); // Setting the first line text to the name of the fact
			}
			if (bt != null) {
				bt.setText(fact.getText()); // Setting the second line text to the text of the fact
			}
		}
		return v; // Returning the view
	}
}

