package com.google.androidtesting.Modell.Entities;

import android.database.Cursor;

import com.google.androidtesting.Repositories.DatabaseHelper;

import java.io.Serializable;

public class Chapter implements Serializable {

   private long Book_ID;
   private String Chapter_Intro;
   private String Ar_Name;

    public Chapter(long Book_ID , String Chapter_Intro, String Ar_Name) {

        this.Book_ID = Book_ID;
        this.Chapter_Intro=Chapter_Intro;
        this.Ar_Name=Ar_Name;
    }

    public Chapter() {
    }

    public static Chapter getChapter(Cursor cursor){

        Chapter chapter=new Chapter();

        chapter.setBook_ID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_CH_BOOK_ID)));
        chapter.setAr_Name(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_CH_AR_NAME)));
      //  chapter.setChapter_Intro(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_CHAPTER_INTRO)));

        return chapter;
    }

    public String getAr_Name() {
        return Ar_Name;
    }

    public void setAr_Name(String ar_Name) {
        Ar_Name = ar_Name;
    }

    public String getChapter_Intro() {
        return Chapter_Intro;
    }

    public void setChapter_Intro(String chapter_Intro) {
        Chapter_Intro = chapter_Intro;
    }

    public long getBook_ID() {
        return Book_ID;
    }

    public void setBook_ID(long book_ID) {
        Book_ID = book_ID;
    }
}
