package com.example.clipvidva;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Vee on 8/9/2556.
 */
public class SubjectsDataSource {

    private SQLiteDatabase database;
    private ClipVidvaDatabaseHelper dbHelper;
    private String[] allColumns = { ClipVidvaDatabaseHelper.SUBJECT_COL_ID,
            ClipVidvaDatabaseHelper.SUBJECT_COL_NAME,
            ClipVidvaDatabaseHelper.SUBJECT_COL_CATEGORY};

    public SubjectsDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ArrayList<Subject> getAllSubjectsIn(String category_id) {
        return getAllSubjectsIn(Integer.parseInt(category_id));
    }

    public ArrayList<Subject> getAllSubjectsIn(int category_id) {
        ArrayList<Subject> subjects = new ArrayList<Subject>();
        String where_clause = ClipVidvaDatabaseHelper.SUBJECT_COL_CATEGORY + " = " + Integer.toString(category_id);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_SUBJECTS,
                allColumns, where_clause, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Subject subject = cursorToSubject(cursor);
            subjects.add(subject);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return subjects;
    }

    private Subject cursorToSubject(Cursor cursor) {
        Subject subject = new Subject();
        subject.setId(cursor.getInt(0));
        subject.setName(cursor.getString(1));
        subject.setCategory_id(cursor.getInt(2));
        return subject;
    }
}
