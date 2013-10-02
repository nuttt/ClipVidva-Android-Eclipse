package com.example.clipvidva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Vee on 7/9/2556.
 */
public class ClipVidvaDatabaseHelper extends SQLiteOpenHelper {

    private String[] tables;
    private String[] creates;
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_SUBJECTS = "subjects";
    public static final String TABLE_VIDEOS = "videos";
    public static final String TABLE_QUIZZES = "quizzes";
    public static final String TABLE_USER_ANSWERS = "user_answers";

    public static final String CATEGORY_COL_ID = "_id";
    public static final String CATEGORY_COL_NAME = "name";
    public static final String CATEGORY_COL_IMG = "img";
    public static final String SUBJECT_COL_ID = "_id";
    public static final String SUBJECT_COL_NAME = "_name";
    public static final String SUBJECT_COL_CATEGORY = "category_id";
    public static final String VIDEO_COL_ID = "_id";
    public static final String VIDEO_COL_NAME = "name";
    public static final String VIDEO_COL_FILE = "file";
    public static final String VIDEO_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_SUBJECT = "subject_id";
    public static final String QUIZ_COL_ID = "_id";
    public static final String QUIZ_COL_QUESTION = "question";
    public static final String QUIZ_COL_TYPE = "type";
    public static final String QUIZ_COL_CHOICES = "choices";
    public static final String QUIZ_COL_HINT = "hint";
    public static final String QUIZ_COL_ANSWER = "answer";
    public static final String QUIZ_COL_DESCRIPTION = "description";
    public static final String USER_ANSWERS_COL_SUBJECT_ID = "subject_id";
    public static final String USER_ANSWERS_COL_QUESTION_ID = "question_id";
    public static final String USER_ANSWERS_COL_ANSWER = "answer";
    public static final String USER_ANSWERS_COL_RESULT = "result";

    private static final String DATABASE_NAME = "clipvidva.db";
    private static final int DATABASE_VERSION = 28;

    private Context context;

    // Database creation sql statement
    private static final String CATEGORIES_CREATE = "create table "
            + TABLE_CATEGORIES + "("
            + CATEGORY_COL_ID + " integer primary key, "
            + CATEGORY_COL_NAME + " text not null, "
            + CATEGORY_COL_IMG + " text);";
    private static final String SUBJECTS_CREATE = "create table "
            + TABLE_SUBJECTS + "("
            + SUBJECT_COL_ID + " integer primary key, "
            + SUBJECT_COL_NAME + " text not null, "
            + SUBJECT_COL_CATEGORY + " integer not null);";
    private static final String VIDEOS_CREATE = "create table "
            + TABLE_VIDEOS + "("
            + VIDEO_COL_ID + " integer primary key, "
            + VIDEO_COL_NAME + " text not null, "
            + VIDEO_COL_FILE + " text not null, "
            + VIDEO_COL_SUBJECT + " integer not null);";
    private static final String QUIZZES_CREATE = "create table "
            + TABLE_QUIZZES + "("
            + QUIZ_COL_SUBJECT + " integer not null, "
            + QUIZ_COL_ID + " integer not null, "
            + QUIZ_COL_QUESTION + " text not null, "
            + QUIZ_COL_TYPE + " text not null, "
            + QUIZ_COL_CHOICES + " text, "
            + QUIZ_COL_ANSWER + " text not null, "
            + QUIZ_COL_HINT + " text, "
            + QUIZ_COL_DESCRIPTION + " text, "
            + "primary key (" + QUIZ_COL_SUBJECT + ", " + QUIZ_COL_ID + "));";
    private static final String USER_ANSWERS_CREATE = "create table "
            + TABLE_USER_ANSWERS + "("
            + USER_ANSWERS_COL_SUBJECT_ID + " integer not null, "
            + USER_ANSWERS_COL_QUESTION_ID + " integer not null, "
            + USER_ANSWERS_COL_ANSWER + " text not null, "
            + USER_ANSWERS_COL_RESULT + " text not null, "
            + "primary key (" + USER_ANSWERS_COL_SUBJECT_ID + ", " + USER_ANSWERS_COL_QUESTION_ID + "));";

    public ClipVidvaDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        String[] all_tables = {TABLE_CATEGORIES, TABLE_SUBJECTS, TABLE_VIDEOS, TABLE_QUIZZES, TABLE_USER_ANSWERS};
        String[] all_creates = {CATEGORIES_CREATE, SUBJECTS_CREATE, VIDEOS_CREATE, QUIZZES_CREATE, USER_ANSWERS_CREATE};
        this.tables = all_tables;
        this.creates = all_creates;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        for(String create_query: this.creates){
            database.execSQL(create_query);
        }
        // TODO: Change from hard-code to read from file
        database.execSQL("INSERT INTO categories VALUES(1,'" + context.getResources().getString(R.string.math_category) + "','hsmaths');");
        database.execSQL("INSERT INTO categories VALUES(2,'" + context.getResources().getString(R.string.askvidva_category) + "','askeng');");
        // Subjects for Maths category
        database.execSQL("INSERT INTO subjects VALUES(101,'" + context.getResources().getString(R.string.subject_real_number) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(102,'" + context.getResources().getString(R.string.subject_conic_section) + "', 1);");
        database.execSQL("INSERT INTO subjects VALUES(103,'" + context.getResources().getString(R.string.subject_functions) + "', 1);");
        // Subjects for Askeng category
        database.execSQL("INSERT INTO subjects VALUES(201,'" + context.getResources().getString(R.string.subject_askeng_ce) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(202,'" + context.getResources().getString(R.string.subject_askeng_che) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(203,'" + context.getResources().getString(R.string.subject_askeng_cp) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(204,'" + context.getResources().getString(R.string.subject_askeng_ee) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(205,'" + context.getResources().getString(R.string.subject_askeng_eng) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(206,'" + context.getResources().getString(R.string.subject_askeng_env) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(207,'" + context.getResources().getString(R.string.subject_askeng_me) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(208,'" + context.getResources().getString(R.string.subject_askeng_mn) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(209,'" + context.getResources().getString(R.string.subject_askeng_mt) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(210,'" + context.getResources().getString(R.string.subject_askeng_pe) + "', 2);");
        database.execSQL("INSERT INTO subjects VALUES(211,'" + context.getResources().getString(R.string.subject_askeng_sv) + "', 2);");
        
        // Videos for Real Number
        database.execSQL("INSERT INTO videos VALUES(10101,'1','real_1', 101);");
        database.execSQL("INSERT INTO videos VALUES(10102,'2','real_2', 101);");
        database.execSQL("INSERT INTO videos VALUES(10103,'3','real_3', 101);");
        database.execSQL("INSERT INTO videos VALUES(10104,'4','real_4', 101);");
        database.execSQL("INSERT INTO videos VALUES(10105,'5','real_5', 101);");
        database.execSQL("INSERT INTO videos VALUES(10106,'6','real_6', 101);");
        database.execSQL("INSERT INTO videos VALUES(10107,'7','real_7', 101);");
        // Videos for Conic Section
        database.execSQL("INSERT INTO videos VALUES(10201,'1','conic123', 102);");
        database.execSQL("INSERT INTO videos VALUES(10202,'2','conic223', 102);");
        // Videos for Askeng
        database.execSQL("INSERT INTO videos VALUES(20101,'" + context.getResources().getString(R.string.subject_askeng_ce) + "','askeng_ce', 201);");
        database.execSQL("INSERT INTO videos VALUES(20201,'" + context.getResources().getString(R.string.subject_askeng_che) + "','askeng_che', 202);");
        database.execSQL("INSERT INTO videos VALUES(20301,'" + context.getResources().getString(R.string.subject_askeng_cp) + "','askeng_cp', 203);");
        database.execSQL("INSERT INTO videos VALUES(20401,'" + context.getResources().getString(R.string.subject_askeng_ee) + "','askeng_ee', 204);");
        database.execSQL("INSERT INTO videos VALUES(20501,'" + context.getResources().getString(R.string.subject_askeng_eng) + "','askeng_eng', 205);");
        database.execSQL("INSERT INTO videos VALUES(20601,'" + context.getResources().getString(R.string.subject_askeng_env) + "','askeng_env', 206);");
        database.execSQL("INSERT INTO videos VALUES(20701,'" + context.getResources().getString(R.string.subject_askeng_me) + "','askeng_me', 207);");
        database.execSQL("INSERT INTO videos VALUES(20801,'" + context.getResources().getString(R.string.subject_askeng_mn) + "','askeng_mn', 208);");
        database.execSQL("INSERT INTO videos VALUES(20901,'" + context.getResources().getString(R.string.subject_askeng_mt) + "','askeng_mt', 209);");
        database.execSQL("INSERT INTO videos VALUES(21001,'" + context.getResources().getString(R.string.subject_askeng_pe) + "','askeng_pe', 210);");
        database.execSQL("INSERT INTO videos VALUES(21101,'" + context.getResources().getString(R.string.subject_askeng_sv) + "','askeng_sv', 211);");
        
        
        // Question
        database.execSQL("INSERT INTO quizzes VALUES(101, 1, 'Real Question', 'choices', '15a + b|a*b*c + 2|3d + 4|a', '1', 'this is the hint', 'description goes here');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 2, 'Real Question q2', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 3, 'Real Question q3', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 4, 'Real Question q4', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 5, 'Real Question q5', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 6, 'Real Question q6', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        database.execSQL("INSERT INTO quizzes VALUES(101, 7, 'Real Question q7', 'choices', 'c1|cc2|ccc3|cccc4', '1', 'this is the hint', 'description goes here again');");
        //database.execSQL("INSERT INTO quizzes VALUES(1, 3, 'Real Question', 'text', '', '1', '', 'description goes here');");
        database.execSQL("INSERT INTO quizzes VALUES(102, 1, 'Conic 1', 'choices', 'c1|c2|c3|c4', '2', 'hint hint hint!', 'description goes here 22');");
        database.execSQL("INSERT INTO quizzes VALUES(103, 1, 'Function No 1', 'choices', 'ffff1|f 2|f f  3|ff 4', '4', '', 'description goes here for function');");
        // User Answers
        database.execSQL("INSERT INTO user_answers VALUES(101, 1, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 2, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 3, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 4, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 5, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 6, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(101, 7, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(102, 1, '', '');");
        database.execSQL("INSERT INTO user_answers VALUES(103, 1, '', '');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(ClipVidvaDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        for(String table_name: this.tables){
            db.execSQL("DROP TABLE IF EXISTS " + table_name);
        }
        onCreate(db);
    }

}