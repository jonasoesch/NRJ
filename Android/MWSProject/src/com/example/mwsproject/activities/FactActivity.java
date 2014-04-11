package com.example.mwsproject.activities;

import com.example.mwsproject.R;
import com.example.mwsproject.R.id;
import com.example.mwsproject.R.layout;
import com.example.mwsproject.R.menu;
import com.example.mwsproject.model.Fact;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class FactActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fact);

		// Getting intent extras
		Bundle extras = getIntent().getExtras();
		Fact fact = (Fact) extras.get(MainActivity.SELECTED_FACT);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment(fact)).commit(); // fact is given to the fragment
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fact, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private Fact fact; // The fact to display

		public PlaceholderFragment(Fact fact) {
			this.fact = fact; // recording the fact to display
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.activity_fact_fragment, container,
					false);
			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) { 
			super.onActivityCreated(savedInstanceState);
			TextView textView = (TextView) getActivity().findViewById(R.id.text_view); // Getting the text view
			textView.setText(fact.getText()); // Setting the TextView text
		}
	}
}
