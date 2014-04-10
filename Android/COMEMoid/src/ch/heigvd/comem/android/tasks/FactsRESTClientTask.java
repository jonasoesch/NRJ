/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import ch.heigvd.comem.android.activities.FactsActivity;
import ch.heigvd.comem.android.model.Fact;
import ch.heigvd.comem.android.util.Utils;
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

/**
 *
 * @author oliechti
 */
public class FactsRESTClientTask extends AsyncTask<String, Integer, String> {

  private FactsActivity factsActivity;
  private ArrayList<Fact> facts = new ArrayList<Fact>();
  private ProgressDialog progressDialog;

  public FactsRESTClientTask(FactsActivity factsActivity) {
    this.factsActivity = factsActivity;
  }

  @Override
  protected String doInBackground(String... keyword) {
    String url = "http://10.0.2.2:1337/api/facts?q=" + keyword[0];
    HttpClient client = new DefaultHttpClient();
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
        JSONArray json = new JSONArray(data);
        Log.d("json", json.toString(2));
        for (int i = 0; i < json.length(); i++) {
          JSONObject result = json.getJSONObject(i);
          Log.d("JSON", result.getString("text"));
          Fact fact = new Fact();
          fact.setName(result.getString("name"));
          fact.setText(result.getString("text"));
          fact.setTimestamp(new Date(result.getLong("timestamp")));
          facts.add(fact);
        }

      } catch (JSONException ex) {
        Logger.getLogger(FactsRESTClientTask.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(FactsRESTClientTask.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IllegalStateException ex) {
        Logger.getLogger(FactsRESTClientTask.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    return response.getEntity().toString();
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();
    progressDialog = new ProgressDialog(factsActivity);
    progressDialog.setTitle("Loading...");
    progressDialog.setMessage("Fetching facts...");
    progressDialog.setCancelable(false);
    progressDialog.show();
  }


  @Override
  protected void onPostExecute(String result) {
    Log.d("onPostExecute", result);
    progressDialog.dismiss();
    factsActivity.updateFacts(facts);
  }

}
