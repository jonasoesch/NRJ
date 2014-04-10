/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import ch.heigvd.comem.android.R;
import ch.heigvd.comem.android.model.Fact;
import java.util.ArrayList;

/**
 *
 * @author oliechti
 */
public class FactAdapter extends ArrayAdapter<Fact> {

  private ArrayList<Fact> items;

  public FactAdapter(Context context, int textViewResourceId, ArrayList<Fact> items) {
    super(context, textViewResourceId, items);
    this.items = items;
  }

  public View getView(int position, View convertView, ViewGroup parent) {
    Log.d("FactAdapter", "Preparing view for list view");
    View v = convertView;
    if (v == null) {
      LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      v = vi.inflate(R.layout.fact, null);
    }
    Fact fact = items.get(position);
    if (fact != null) {
      TextView tt = (TextView) v.findViewById(R.id.line1);
      TextView bt = (TextView) v.findViewById(R.id.line2);
      if (tt != null) {
        tt.setText(fact.getName());
      }
      if (bt != null) {
        bt.setText(fact.getText());
      }
    }
    return v;
  }
}

