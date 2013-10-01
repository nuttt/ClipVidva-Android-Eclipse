package com.example.clipvidva;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Vee on 8/9/2556.
 */
public class VideosDataSource {

    private SQLiteDatabase database;
    private ClipVidvaDatabaseHelper dbHelper;
    private String[] allColumns = { ClipVidvaDatabaseHelper.VIDEO_COL_ID,
            ClipVidvaDatabaseHelper.VIDEO_COL_NAME,
            ClipVidvaDatabaseHelper.VIDEO_COL_FILE,
            ClipVidvaDatabaseHelper.VIDEO_COL_SUBJECT};

    public VideosDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
    
    private Cursor dbConnect(int subject_id){
    	Log.v("nut", subject_id+"");
        String where_clause = ClipVidvaDatabaseHelper.VIDEO_COL_SUBJECT + " = " + Integer.toString(subject_id);
        open();
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_VIDEOS,
                allColumns, where_clause, null, null, null, null);
        return cursor;
    }

    public ArrayList<Video> getAllVideosIn(String subject_id) {
        return getAllVideosIn(Integer.parseInt(subject_id));
    }
    
    public int getVideosNum(String subject_id){
    	return getVideosNum(Integer.parseInt(subject_id));
    }
    
    public int getVideosNum(int subject_id) {
        Cursor cursor = dbConnect(subject_id);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public ArrayList<Video> getAllVideosIn(int subject_id) {
        ArrayList<Video> videos = new ArrayList<Video>();
        Cursor cursor = dbConnect(subject_id);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Video video = cursorToVideo(cursor);
            videos.add(video);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return videos;
    }

    private Video cursorToVideo(Cursor cursor) {
        Video video = new Video();
        video.setId(cursor.getInt(0));
        video.setName(cursor.getString(1));
        video.setFile(cursor.getString(2));
        video.setSubject_id(cursor.getInt(3));
        return video;
    }
}
