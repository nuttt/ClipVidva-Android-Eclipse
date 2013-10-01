package com.example.clipvidva;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewer extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.video_viewer);
		
		Intent intent = getIntent();
		String filename = intent.getStringExtra("VIDEO_FILENAME");
		VideoView video = (VideoView)findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.setVideoURI(Uri.parse("http://www.withlovee.com/clipvidva-vid/" + filename + ".mp4"));
        video.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.video_viewer, menu);
		return true;
	}

}
