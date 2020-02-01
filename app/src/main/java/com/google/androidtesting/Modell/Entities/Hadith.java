package com.google.androidtesting.Modell.Entities;

import android.database.Cursor;

import com.google.androidtesting.Repositories.DatabaseHelper;

import java.io.Serializable;

public class Hadith implements Serializable {

    private long Chapter_ID;
    private String Ar_Text;
    private long Book_ID;

    public Hadith(long Chapter_ID , String Ar_Text, long Book_ID) {
        this.Chapter_ID = Chapter_ID;
        this.Ar_Text = Ar_Text;
        this.Book_ID=Book_ID;

    }

    public static Hadith get(Cursor cursor) {
       Hadith hadith=new Hadith();

        hadith.setChapter_ID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_HADITH_CHAPTER_ID)));
        hadith.setAr_Text(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN__AR_TEXT)));
        hadith.setBook_ID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_HADITH_BOOK_ID)));

     return hadith;
    }

    public Hadith() {
    }

    public long getBook_ID() {
        return Book_ID;
    }

    public void setBook_ID(long book_ID) {
        Book_ID = book_ID;
    }

    public String getAr_Text() {
        return Ar_Text;
    }

    public void setAr_Text(String ar_Text) {
        Ar_Text = ar_Text;
    }

    public long getChapter_ID() {
        return Chapter_ID;
    }

    public void setChapter_ID(long chapter_ID) {
        Chapter_ID = chapter_ID;
    }
}

