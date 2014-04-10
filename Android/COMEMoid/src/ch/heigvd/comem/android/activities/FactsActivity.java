/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import ch.heigvd.comem.android.R;
import ch.heigvd.comem.android.model.Fact;
import ch.heigvd.comem.android.tasks.FactsRESTClientTask;
import java.util.ArrayList;

/**
 *
 * @author oliechti
 */
public class FactsActivity extends ListActivity {

  public static final String SELECTED_FACT = "SELECTED_FACT";

  public static final int DISPLAY_FACT_DETAILS_RETURN_CODE = 2205;

  private ArrayList<Fact> facts;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    setContentView(R.layout.factslist);

    final TextView tvDemo = (TextView) findViewById(R.id.entry);
    tvDemo.setText("first");

    Button bOk = (Button) findViewById(R.id.ok);

    bOk.setOnClickListener(new OnClickListener() {

      public void onClick(View view) {
        Log.d("EventListener", view.toString());
        String[] params = new String[1];
        params[0] = tvDemo.getText().toString();
        new FactsRESTClientTask(FactsActivity.this).execute(params);
      }
    });
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    Toast.makeText(this, "Showing facts...", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(this, SimpleActivity.class);
    intent.putExtra(SELECTED_FACT, (Fact)l.getItemAtPosition(position));
    startActivityForResult(intent, DISPLAY_FACT_DETAILS_RETURN_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == DISPLAY_FACT_DETAILS_RETURN_CODE) {
      Toast.makeText(this, "Returning...", Toast.LENGTH_SHORT).show();
    }
  }

  public void updateFacts(ArrayList<Fact> facts) {
    this.facts = facts;
    setListAdapter(new FactAdapter(this, -1, facts));
  }

}
