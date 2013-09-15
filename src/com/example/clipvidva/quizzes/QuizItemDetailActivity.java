package com.example.clipvidva.quizzes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.clipvidva.R;
import com.example.clipvidva.Splash;

public class QuizItemDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_item_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(QuizItemDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(QuizItemDetailFragment.ARG_ITEM_ID));
            QuizItemDetailFragment fragment = new QuizItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpTo(this, new Intent(this, Splash.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    public void setActionBarTitle(String title) {
    	getActionBar().setTitle(title);
    }
    /*
    public boolean onSubmitButtonClicked(View view){
    	Log.v(this.getClass().getName(), "Choice entered: "+ Integer.toString(choice));
    	if(isCorrectChoice()){
    		Dialog dialog = new Dialog(this);
    		dialog.setContentView(R.layout.popupview);
    		TextView txt = (TextView)dialog.findViewById(R.id.textbox);
    		txt.setText(getString(R.string.message));
    		dialog.show();
    	}
    	return true;
    }
    
    private boolean isCorrectChoice(){
    	return true;
    }*/
}
