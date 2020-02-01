package com.google.androidtesting.UI.BookAndChaptersView;

import android.content.Context;

import com.google.androidtesting.Modell.Entities.Book;
import com.google.androidtesting.Modell.Entities.Chapter;
import com.google.androidtesting.Repositories.DatabaseHelper;

import java.util.List;

public class BooksPresenter  {

private BooksInterface booksInterface;

    public  BooksPresenter(BooksInterface booksInterface) {

        this.booksInterface=booksInterface;
    }


    public List<Book> LoadingAllBooks(Context context){

        List<Book> books;

        books = DatabaseHelper.instance(context).getTitles();

        return books;
    }

    public List<Chapter>loadingAllChapters(Context context){

        List<Chapter>chapters;

        chapters=DatabaseHelper.instance(context).getIntros();

        return chapters;
    }

    public void setAllBooks(Context context){
        booksInterface.setAllBooks();
    }

public void UnBind(Context context)
{
    DatabaseHelper.instance(context).unbind();
}
public void setAllChapters(Context context){

        booksInterface.setAllChapters();
}
}
