package com.example.clipvidva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class CategoryListActivity extends FragmentActivity
        implements CategoryListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_item_list);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((CategoryListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.category_item_list))
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
            arguments.putString(SubjectListFragment.ARG_ITEM_ID, id);
            SubjectListFragment fragment = new SubjectListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();
            Log.v(this.getClass().getName(), "Clicked two pane!");

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Log.v(this.getClass().getName(), "Start the intent");
            Intent detailIntent = new Intent(this, SubjectListActivity.class);
            Log.v(this.getClass().getName(), "Put extra "+id);
            detailIntent.putExtra(SubjectListFragment.ARG_ITEM_ID, id);
            Log.v(this.getClass().getName(), "Start activity");
            startActivity(detailIntent);
            Log.v(this.getClass().getName(), "Clicked one pane!");
        }
    }
}
