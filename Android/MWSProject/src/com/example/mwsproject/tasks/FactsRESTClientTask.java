/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mwsproject.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.mwsproject.activities.MainActivity;
import com.example.mwsproject.model.Fact;
import com.example.mwsproject.util.Utils;

public class FactsRESTClientTask extends AsyncTask<String, Integer, String> {

	private MainActivity mainActivity;
	private ArrayList<Fact> facts = new ArrayList<Fact>();
	private ProgressDialog progressDialog;

	public FactsRESTClientTask(MainActivity mainActivity) {
		this.mainActivity = mainActivity; // Recording the activity to return the result
	}
	
	/**
	 * This method is called before task execution
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog = new ProgressDialog(mainActivity); // Displaying a loading message
		progressDialog.setTitle("Loading...");
		progressDialog.setMessage("Fetching facts...");
		progressDialog.setCancelable(false);
		progressDialog.show();
	}

	/**
	 * Getting tasks using the server REST interface
	 */
	@Override
	protected String doInBackground(String... keyword) {
		String url = "http://10.0.2.2:1337/api/facts?q=" + keyword[0]; // The 10.0.2.2 IP Address refers to the computer running the emulator. If you use a real device, it should be on the same LAN as you computer and you should define here your computer IP address.
		System.out.println("**** FactsRESTClientTask **** Requesting.... : " + url);
		
		HttpClient client = new DefaultHttpClient(); // Creating an HTTP client
		HttpResponse response = null;
		try {
			response = client.execute(new HttpGet(url));
		} catch (IOException ex) {
			Logger.getLogger(FactsRESTClientTask.class.getName()).log(Level.SEVERE, null, ex);
		}
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			try {
				String data = Utils.convertStreamToString(entity.getContent());
				JSONArray json = new JSONArray(data); // Converting the result in a JSON array
				Log.d("json", json.toString(2));
				for (int i = 0; i < json.length(); i++) { // For each task
					JSONObject result = json.getJSONObject(i); // Getting th JSON object
					Fact fact = new Fact(); // Creating a fact
					fact.setName(result.getString("name"));
					fact.setText(result.getString("text"));
					fact.setTimestamp(new Date(result.getLong("timestamp")));
					facts.add(fact); // Adding the fact to the collection
				}

			} catch (JSONException ex) {
				Logger.getLogger(FactsRESTClientTask.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(FactsRESTClientTask.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (IllegalStateException ex) {
				Logger.getLogger(FactsRESTClientTask.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
		return response.getEntity().toString();
	}
	
	/**
	 * This method is called when the task has been processed
	 */
	@Override
	protected void onPostExecute(String result) {
		Log.d("onPostExecute", result);
		progressDialog.dismiss();
		mainActivity.updateFacts(facts); // Sending the facts list to the activity
	}

}
