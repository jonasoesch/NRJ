/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.activities;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import ch.heigvd.comem.android.R;

/**
 *
 * @author oliechti
 *
 * The MainActivity is responsible to setting up the tab container. It retrieves the layout defined
 * in tabcontainer.xml and adds 3 tabs.
 *
 */
public class MainActivity extends TabActivity {

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);

    setContentView(R.layout.tabcontainer);

    Resources res = getResources(); // Resource object to get Drawables
    TabHost tabHost = getTabHost();  // The activity TabHost
    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
    Intent intent;  // Reusable Intent for each tab

    // Creating tab 1
    // creating an intent makes it possible to launch the activity when selecting the tab
    intent = new Intent().setClass(this, FactsActivity.class);
    spec = tabHost.newTabSpec("factsTab").setIndicator("Facts", res.getDrawable(R.drawable.ic_tab_profile)).setContent(intent);
    tabHost.addTab(spec);

    // Creating tab 2
    // This artificial example shows that it is possible to trigger the same activity
    spec = tabHost.newTabSpec("factsTooTab").setIndicator("Facts Too", res.getDrawable(R.drawable.ic_tab_profile)).setContent(intent);
    tabHost.addTab(spec);

    intent = new Intent().setClass(this, CanvasActivity.class);
    spec = tabHost.newTabSpec("canvasTab").setIndicator("Canvas", res.getDrawable(R.drawable.ic_tab_profile)).setContent(intent);
    tabHost.addTab(spec);
  }
}
