package com.example.clipvidva;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SubjectListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_item_list);
		Intent i = getIntent();
		int categoryId = Integer.parseInt(i.getStringExtra("CATEGORY_ID"));
		
	}
	

}
