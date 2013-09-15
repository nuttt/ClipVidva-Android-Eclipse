package com.example.clipvidva;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

    public List<Video> getAllVideosIn(String subject_id) {
        return getAllVideosIn(Integer.parseInt(subject_id));
    }

    public List<Video> getAllVideosIn(int subject_id) {
        List<Video> videos = new ArrayList<Video>();
        String where_clause = ClipVidvaDatabaseHelper.VIDEO_COL_SUBJECT + " = " + Integer.toString(subject_id);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_VIDEOS,
                allColumns, where_clause, null, null, null, null);

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
