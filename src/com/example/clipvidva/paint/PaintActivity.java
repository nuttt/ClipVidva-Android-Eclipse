package com.example.clipvidva.paint;

import com.example.clipvidva.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PaintActivity extends Activity {
	private BrushView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new BrushView(this);
        setContentView(view);
    }
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate close for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.paint_bar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.hide_activity:
	        	Log.v(this.getClass().getName(), "Hide button clicked");
	        	finish();
	            return true;
	        case R.id.delete_screen:
	        	Log.v(this.getClass().getName(), "Delete button clicked");
	        	view.clearScreen();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
