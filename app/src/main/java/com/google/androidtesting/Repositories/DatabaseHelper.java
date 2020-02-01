package com.google.androidtesting.Repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.androidtesting.Modell.Entities.Book;
import com.google.androidtesting.Modell.Entities.Chapter;
import com.google.androidtesting.Modell.Entities.Hadith;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper {
    private static DatabaseHelper databaseHelper;
    // to store big things in memory like context
    private WeakReference<Context> contextWeakReference;
    private SQLiteDatabase database;

    private DatabaseHelper(Context context) {
        contextWeakReference = new WeakReference<>(context);
    }

    public static synchronized DatabaseHelper instance(Context context) {
        if (databaseHelper == null)
            databaseHelper = new DatabaseHelper(context);

        return databaseHelper;
    }


    private SQLiteDatabase initDB() {
        if (database == null) {
// check if database exists or not
            if (!AssetDatabaseOpenHelper.iaDatabaseExists(contextWeakReference.get())) {
                // database doesn't exist . start copy database.
                try {
                    AssetDatabaseOpenHelper.copyDataBase(contextWeakReference.get());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            database = AssetDatabaseOpenHelper.newInstance(contextWeakReference.get()).getReadableDatabase();
        }
        return database;
    }


    public List<Book> getTitles() {
        SQLiteDatabase db = initDB();

        String query = "Select * from Books";
        List<Book> books = new LinkedList<>();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null) return null;
        cursor.moveToFirst();
        do {
            books.add(Book.get(cursor));
        } while (cursor.moveToNext());

        cursor.close();

        return books;
    }

    public List<Chapter>getIntros(){

        SQLiteDatabase db=initDB();
        String query=" Select * from Chapters";
        List<Chapter> chapters=new LinkedList<>();
        Cursor cursor=db.rawQuery(query , null);
        if (cursor==null) return null;
        cursor.moveToFirst();
        do{
            chapters.add(Chapter.getChapter(cursor));
        }while (cursor.moveToNext());

        return chapters;
    }

    public List<Hadith>getTexts(){

        SQLiteDatabase db=initDB();
        String query=" Select * from Ahadith";
        List<Hadith> hadiths=new LinkedList<>();
        Cursor cursor=db.rawQuery(query , null);
        Log.e("CURSOR_COUNT" , cursor.getCount()+ "");
        if (cursor==null) return null;
        cursor.moveToFirst();
        do{
            hadiths.add(Hadith.get(cursor));
        }while (cursor.moveToNext());

        return hadiths;
    }

    public int getCursorAhadithCount(){
        SQLiteDatabase db=initDB();
        String query=" Select * from Ahadith";
        Cursor cursor=db.rawQuery(query , null);
        Log.e("CURSOR_COUNT" , cursor.getCount()+ "");

        return cursor.getCount();
    }

    public void unbind() {
        database.close();
        database = null;
        databaseHelper = null;
    }


    public class Constants {


        public static final String COLUMN_BOOK_ID = "Book_ID";
        public static final String COLUMN_AR_NAME = "Ar_Name";
        public static final String COLUMN_EN_NAME = "En_Name";

        public static final String COLUMN_CHAPTER_ID = "Chapter_ID";
        public static final String COLUMN_CH_AR_NAME = "Ar_Name";
        public static final String COLUMN_CH_EN_NAME = "En_Name";
        public static final String COLUMN_CHAPTER_INTRO = "Chapter_Intro";
        public static final String COLUMN_CH_BOOK_ID = "Book_ID";

        public static final String COLUMN_HADITH_CHAPTER_ID = "Chapter_ID";
        public static final String COLUMN_HADITH_BOOK_ID = "Book_ID";
        public static final String COLUMN__AR_TEXT = "Ar_Text";
    }
}