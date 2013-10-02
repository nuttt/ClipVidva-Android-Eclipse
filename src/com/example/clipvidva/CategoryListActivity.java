package com.example.clipvidva;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategoryListActivity extends Activity {
	
	private CategoriesListAdapter categoriesListAdapter;
	private CategoriesDataSource categoriesDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.category_item_list);
		getActionBar().setTitle(R.string.category_activity_title);
		categoriesListAdapter = new CategoriesListAdapter();
		categoriesDataSource = new CategoriesDataSource(this);
		categoriesDataSource.open();
		ListView listView = (ListView)findViewById(R.id.categories_list_view);
		listView.setAdapter(categoriesListAdapter);
		listView.setOnItemClickListener(new CategoryItemClickListener());
		ArrayList<Category> categories = categoriesDataSource.getAllCategories();
		for(int i=0; i<categories.size();i++){
			Category category = categories.get(i);
			categoriesListAdapter.addItem(category);
		}
		categoriesListAdapter.notifyDataSetChanged();
	}
	
	private class CategoryItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Category clicked = (Category)categoriesListAdapter.getItem(arg2);
			int categoryId = clicked.getId();
			String categoryName = clicked.getName();
			Intent intent = new Intent(arg0.getContext(), SubjectListActivity.class);
			intent.putExtra("CATEGORY_ID", categoryId + "");
			intent.putExtra("CATEGORY_NAME", categoryName);
			startActivity(intent);
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate close for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.close_bar, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.close_activity:
	        	Log.v(this.getClass().getName(), "Close button clicked");
	        	finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}