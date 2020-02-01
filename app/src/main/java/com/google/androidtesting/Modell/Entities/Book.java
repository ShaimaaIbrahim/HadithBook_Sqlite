package com.google.androidtesting.Modell.Entities;

import android.database.Cursor;

import com.google.androidtesting.Repositories.DatabaseHelper;

import java.io.Serializable;

public class Book implements Serializable {



    private long Book_ID;
    private String Ar_Name;
    private String En_Name;


    public Book(long book_ID, String ar_Name, String en_Name) {
        Book_ID = book_ID;
        Ar_Name = ar_Name;
        En_Name = en_Name;
    }

    public Book() {
    }

    public static Book get(Cursor cursor) {
        Book book = new Book();

        book.setAr_Name(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_AR_NAME)));
        book.setEn_Name(cursor.getString(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_EN_NAME)));
        book.setBook_ID(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Constants.COLUMN_BOOK_ID)));
        return book;
    }

    public String getEn_Name() {
        return En_Name;
    }

    public void setEn_Name(String en_Name) {
        En_Name = en_Name;
    }

    public String getAr_Name() {
        return Ar_Name;
    }

    public void setAr_Name(String ar_Name) {
        Ar_Name = ar_Name;
    }

    public long getBook_ID() {
        return Book_ID;
    }

    public void setBook_ID(long book_ID) {
        Book_ID = book_ID;
    }
}