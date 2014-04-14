package com.example.mwsproject.activities;

import java.util.ArrayList;

import com.example.mwsproject.R;
import com.example.mwsproject.R.id;
import com.example.mwsproject.R.layout;
import com.example.mwsproject.R.menu;
import com.example.mwsproject.R.string;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

import com.example.mwsproject.activities.fragments.FactsFragment;
import com.example.mwsproject.activities.fragments.Tab2Fragment;
import com.example.mwsproject.model.Fact;
import com.example.mwsproject.tasks.FactsRESTClientTask;
import com.example.mwsproject.util.TabListener;

public class MainActivity extends ActionBarActivity {

	public static final String SELECTED_FACT = "com.example.myfirstapp.SELECTED_FACT"; // extra key, using the package name is a good practice avoiding conflict between activities and apps
	public static final int DISPLAY_FACT_DETAILS_RETURN_CODE = 2205; // Event code when we are coming back to the activity after an intent to another activity
	private EditText searchEditText; // Search field
	private ArrayList<Fact> facts; // List of facts

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Notice that setContentView() is not used, because we use the root
		// android.R.id.content as the container for each fragment

		// setup action bar and tabs
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); // We want tabs
		actionBar.setDisplayShowTitleEnabled(true); // Showing the title

		// First tab : "Facts"
		Tab tab = actionBar.newTab()
				.setText(R.string.facts)
				.setTabListener(new TabListener<FactsFragment>(this, "facts", FactsFragment.class)); // The fragment class is in the fragments package
		actionBar.addTab(tab);

		// Second tab : Tab 2
		tab = actionBar.newTab()
				.setText(R.string.tab2)
				.setTabListener(new TabListener<Tab2Fragment>(this, "tab 2", Tab2Fragment.class));
		actionBar.addTab(tab);

		setContentView(R.layout.activity_main); // The main view for the activity (a fragment container)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test, menu);
		return true;
	}

	/**
	 * This method is called when an option item is selected in the Action bar
	 */
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
	 * This method is called when the search button is clicked
	 * @param view
	 */
	public void searchFacts(View view) {
		EditText searchEditText = (EditText) findViewById(R.id.search_text);
		String param = searchEditText.getText().toString();
		new FactsRESTClientTask(this).execute(param); // Creating an asynch task to avoid blocking the GUI, the search term is given as a parameter
	}

	/**
	 * This method is called by the asynch task when it is completed. Based on the new facts we update the list view.
	 * @param facts
	 */
	public void updateFacts(ArrayList<Fact> facts) {
		this.facts = facts; // keeping facts
		ListView listview = (ListView) findViewById(R.id.list); // Getting the list view
		listview.setAdapter(new FactAdapter(this, -1, facts)); // We use an adapter to generates list item views (it is in util package) 
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // Callback when a list item is clicked
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Log.d("Selected item", Integer.toString(position));
				MainActivity.this.showFact(position); // We call a method on the activity    			
			}
		});
	}

	/**
	 * Open another activity to display the fact
	 * @param index
	 */
	public void showFact(int index) {         
		Fact fact = facts.get(index); // Getting the corresponding fact in the list
		Toast.makeText(this, "Showing fact...", Toast.LENGTH_SHORT).show(); // A toast display a modal message
		Intent intent = new Intent(this, FactActivity.class); // Creating an itent to start another activity
		intent.putExtra(SELECTED_FACT, fact); // We put the fact as an extra for the target activity
		startActivityForResult(intent, DISPLAY_FACT_DETAILS_RETURN_CODE); // Starting the activity
	}

	/**
	 * This method show a modal message when user comes back from the fact activity
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == DISPLAY_FACT_DETAILS_RETURN_CODE) {
			Toast.makeText(this, "Returning...", Toast.LENGTH_SHORT).show();
		}
	}
}
