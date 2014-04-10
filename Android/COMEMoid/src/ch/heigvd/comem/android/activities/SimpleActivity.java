/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import ch.heigvd.comem.android.model.Fact;

/**
 *
 * @author oliechti
 */
public class SimpleActivity extends Activity {

  private static final int MENU_GROUP_1 = 1;
  private static final int MENU_ITEM_DOSOMETHING = 1;
  private static final int MENU_ITEM_ORNOT = 2;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    TextView tv = new TextView(this);
    Bundle extras = getIntent().getExtras();
    Fact fact = (Fact) extras.get(FactsActivity.SELECTED_FACT);
    tv.setText(fact.getText());
    setContentView(tv);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    menu.add(MENU_GROUP_1, MENU_ITEM_DOSOMETHING, MENU_ITEM_DOSOMETHING, "Do something");
    menu.add(MENU_GROUP_1, MENU_ITEM_ORNOT, MENU_ITEM_ORNOT, "Or not");

    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch (id) {
      case (MENU_ITEM_DOSOMETHING):
        Toast.makeText(this, "Do something...", Toast.LENGTH_SHORT).show();
        return true;
      case (MENU_ITEM_ORNOT):
        Toast.makeText(this, "... or not.", Toast.LENGTH_SHORT).show();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
