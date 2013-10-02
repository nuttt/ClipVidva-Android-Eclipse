package com.example.clipvidva;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SubjectListActivity extends Activity {

	private SubjectsDataSource subjectsDataSource;
	private SubjectListAdapter subjectsListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subject_item_list);
		
		Intent intent = getIntent();
		int categoryId = Integer.parseInt(intent.getStringExtra("CATEGORY_ID"));
		String categoryName = intent.getStringExtra("CATEGORY_NAME");
		
		getActionBar().setTitle(categoryName);
		
		subjectsDataSource = new SubjectsDataSource(getApplicationContext());
		subjectsDataSource.open();
		subjectsListAdapter = new SubjectListAdapter();
		
		ListView listView = (ListView) findViewById(R.id.subject_list_view);
        listView.setAdapter(subjectsListAdapter);
        listView.setOnItemClickListener(new SubjectItemClickListener());
		
        ArrayList<Subject> subjects = subjectsDataSource.getAllSubjectsIn(categoryId);
        for(int i = 0; i < subjects.size(); i++){
        	subjectsListAdapter.addItem(subjects.get(i));
        }
        
        subjectsListAdapter.notifyDataSetChanged();
        
	}
	
	private class SubjectItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Subject clicked = (Subject)subjectsListAdapter.getItem(arg2);
			int subjectId = clicked.getId();
			String subjectName = clicked.getName();
			VideosDataSource videoDataSource = new VideosDataSource(getApplicationContext());
			int videoNum = videoDataSource.getVideosNum(subjectId);
			if(videoNum == 1){
				ArrayList<Video> videos = videoDataSource.getAllVideosIn(subjectId);
				Video video = videos.get(0); 
				Intent intent = new Intent(arg0.getContext(), VideoViewer.class);
				intent.putExtra("VIDEO_FILENAME", video.getFile());
				startActivity(intent);
			} else if(videoNum > 1){
				Intent intent = new Intent(arg0.getContext(), VideoListActivity.class);
				intent.putExtra("SUBJECT_ID", subjectId+"");
				intent.putExtra("SUBJECT_NAME", subjectName);
				startActivity(intent);
			} else {
				Toast noVideo = Toast.makeText(getApplicationContext(), "No videos available.", Toast.LENGTH_SHORT);
				noVideo.show();
			}
		}
	}
	

}
