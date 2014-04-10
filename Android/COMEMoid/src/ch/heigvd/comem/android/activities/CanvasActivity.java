/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.comem.android.activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Date;

/**
 *
 * @author oliechti
 *
 * This is a simple activity that demonstrates how to do 2D graphics on android. I have defined a custom
 * component (a subclass of View) and overriden the onDraw() method. What I do there is very similar to
 * what I would do with a "Graphics" context on Java SE.
 *
 */
public class CanvasActivity extends Activity {

  private final static int BACKGROUND_COLOR = Color.rgb(40, 40, 40);
  private final static int FOREGROUND_COLOR = Color.rgb(240, 240, 40);

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle icicle) {
    super.onCreate(icicle);
    
    // I create the view
    CanvasView canvas = new CanvasView(this, null);

    // I add it as the content view for this activity; it will fill up the entire space
    addContentView(canvas, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
  }

  
  private class CanvasView extends View {

    public CanvasView(Context context, AttributeSet attrs) {
      super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      canvas.drawColor(BACKGROUND_COLOR);
      Paint p = new Paint();
      p.setColor(FOREGROUND_COLOR);
      p.setTextSize(18);
      canvas.drawRect(10, 10, 100, 100, p);
      canvas.drawText(new Date().toString(), 10,140, p);
    }

    
  }

}
