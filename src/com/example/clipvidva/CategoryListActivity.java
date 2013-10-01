package com.example.clipvidva;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryListActivity extends Activity {
	
	private CategoriesListAdapter categoriesListAdapter;
	private CategoriesDataSource categoriesDataSource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.category_item_list);
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
			Intent i = new Intent(arg0.getContext(), SubjectListActivity.class);
			i.putExtra("CATEGORY_ID", categoryId + "");
			startActivity(i);
		}
	}
	
}