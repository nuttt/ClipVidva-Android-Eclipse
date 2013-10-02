package com.example.clipvidva;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	
	public void onResume(){
		super.onResume();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
				
				DownloadDialogFragment downloadDialogFragment = new DownloadDialogFragment();
				String videoFileName = video.getFile();
				
				File direct = new File(Environment.getExternalStorageDirectory()
	                    + "/clipvidva/" + videoFileName + ".mp4");
				
				if (!direct.exists()) {
					downloadDialogFragment.setVideoFilename(videoFileName);
				
					downloadDialogFragment.show(getFragmentManager(), "donwloadDialogFragment");
				} else {
					Intent intent = new Intent(getApplicationContext() , VideoViewer.class);
       				intent.putExtra("VIDEO_FILENAME", videoFileName);
       				intent.putExtra("VIDEO_EXIST", "TRUE");
       				startActivity(intent);
				}
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
