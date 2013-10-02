package com.example.clipvidva.quizzes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.clipvidva.CategoryListFragment;
import com.example.clipvidva.R;
import com.example.clipvidva.SubjectListFragment;

public class QuizSubjectListActivity extends FragmentActivity
        implements QuizSubjectListFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_subject_item_list);
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SubjectListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.quiz_subject_item_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link CategoryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id, String name) {
    	Log.v(this.getClass().getName(), "Selected on single pane");
        //Intent detailIntent = new Intent(this, QuizItemDetailActivity.class);
        Intent detailIntent = new Intent(this, QuizQuestionListActivity.class);
        detailIntent.putExtra(QuizQuestionListActivity.SUBJECT_ID, id);
        detailIntent.putExtra(QuizQuestionListActivity.SUBJECT_NAME, name);
        
        startActivity(detailIntent);
    }

	
    public void setActionBarTitle(String title) {
    	getActionBar().setTitle(title);
    	
    }
}
