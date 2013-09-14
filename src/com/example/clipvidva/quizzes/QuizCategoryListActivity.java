package com.example.clipvidva.quizzes;

import com.example.clipvidva.R;
import com.example.clipvidva.SubjectListActivity;
import com.example.clipvidva.SubjectListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class QuizCategoryListActivity extends FragmentActivity
        implements QuizCategoryListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(this.getClass().getName(), "Set content view");
        setContentView(R.layout.quiz_category_item_list);
        Log.v(this.getClass().getName(), "Set!");
        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((QuizCategoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.quiz_category_item_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link CategoryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(QuizSubjectListFragment.ARG_ITEM_ID, id);
            QuizSubjectListFragment fragment = new QuizSubjectListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
            Log.v(this.getClass().getName(), "Clicked two pane!");

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Log.v(this.getClass().getName(), "Start the intent");
            Intent detailIntent = new Intent(this, QuizSubjectListActivity.class);
            Log.v(this.getClass().getName(), "Put extra "+id);
            detailIntent.putExtra(QuizSubjectListFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
