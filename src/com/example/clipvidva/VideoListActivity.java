package com.example.clipvidva;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class VideoListActivity extends Activity {

	private VideosDataSource videosDataSource;
	private VideoListAdapter videosListAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_item_list);
		
		Intent intent = getIntent();
		int subjectId = Integer.parseInt(intent.getStringExtra("SUBJECT_ID"));
		String subjectName = intent.getStringExtra("SUBJECT_NAME");
		
		getActionBar().setTitle(subjectName);
		
		videosDataSource = new VideosDataSource(getApplicationContext());
		videosDataSource.open();
		videosListAdapter = new VideoListAdapter();
		
		GridView listView = (GridView) findViewById(R.id.video_list_view);
        listView.setAdapter(videosListAdapter);
        listView.setOnItemClickListener(new VideoItemClickListener());
		
        ArrayList<Video> videos = videosDataSource.getAllVideosIn(subjectId);
        for(int i = 0; i < videos.size(); i++){
        	videosListAdapter.addItem(videos.get(i));
        }
        
        videosListAdapter.notifyDataSetChanged();
        
	}
	
	private class VideoItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			DownloadDialogFragment downloadDialogFragment = new DownloadDialogFragment();
			Video clicked = videosListAdapter.getItem(arg2);
			String videoFileName = clicked.getFile();
			
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
			
//			Intent intent = new Intent(arg0.getContext(), VideoViewer.class);
//			intent.putExtra("VIDEO_FILENAME", videoFileName);
//			startActivity(intent);
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
