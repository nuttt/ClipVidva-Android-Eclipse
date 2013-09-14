package com.example.clipvidva;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vee on 7/9/2556.
 */
public class CategoriesDataSource {
    private SQLiteDatabase database;
    private ClipVidvaDatabaseHelper dbHelper;
    private String[] allColumns = { ClipVidvaDatabaseHelper.CATEGORY_COL_ID,
            ClipVidvaDatabaseHelper.CATEGORY_COL_NAME,
            ClipVidvaDatabaseHelper.CATEGORY_COL_IMG};

    public CategoriesDataSource(Context context) {
        dbHelper = new ClipVidvaDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /* Unnecessary */
    public Category createCategory(String category, String img) {
        ContentValues values = new ContentValues();
        values.put(ClipVidvaDatabaseHelper.CATEGORY_COL_NAME, category);
        values.put(ClipVidvaDatabaseHelper.CATEGORY_COL_IMG, img);
        long insertId = database.insert(ClipVidvaDatabaseHelper.TABLE_CATEGORIES, null,
                values);
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_CATEGORIES,
                allColumns, ClipVidvaDatabaseHelper.CATEGORY_COL_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Category newCategory = cursorToCategory(cursor);
        cursor.close();
        return newCategory;
    }

    /* Unnecessary */
    public void deleteCategory(Category category) {
        long id = category.getId();
        System.out.println("Category deleted with id: " + id);
        database.delete(ClipVidvaDatabaseHelper.TABLE_CATEGORIES, ClipVidvaDatabaseHelper.CATEGORY_COL_ID
                + " = " + id, null);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<Category>();
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_CATEGORIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            categories.add(category);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return categories;
    }
    

    public List<Category> getAllQuizCategories() {
        List<Category> categories = new ArrayList<Category>();
        Cursor cursor = database.query(ClipVidvaDatabaseHelper.TABLE_CATEGORIES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Category category = cursorToCategory(cursor);
            if(!category.getImg().equals("askeng")){
            	Log.v(this.getClass().getName(), category.getImg());
            	categories.add(category);
            }
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return categories;
    }

    private Category cursorToCategory(Cursor cursor) {
        Category category = new Category();
        category.setId(cursor.getInt(0));
        category.setName(cursor.getString(1));
        category.setImg(cursor.getString(2));
        return category;
    }
}
