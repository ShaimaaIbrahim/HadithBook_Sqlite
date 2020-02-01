package com.google.androidtesting.UI.BookAndChaptersView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.androidtesting.Modell.Entities.Book;
import com.google.androidtesting.Modell.Entities.Chapter;
import com.google.androidtesting.R;
import com.google.androidtesting.UI.Adapters.BooksAdapter;
import com.google.androidtesting.UI.Adapters.ChaptersAdapter;
import com.google.androidtesting.UI.HadithView.HadithActivity;

import java.util.ArrayList;
import java.util.List;

public class BooksActivity extends AppCompatActivity  implements BooksInterface{

    //vars
    public static final String BOOK_ID="BOOK_ID";
    public static final String CHAPTER_ID="CHAPTER_ID";
    private int Book_ID=0;

    //classes
    private BooksPresenter booksPresenter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         booksPresenter=new BooksPresenter(this);

         Intent intent=getIntent();
         Book_ID =intent.getIntExtra(BOOK_ID , 0);

         if (Book_ID ==0){
             booksPresenter.setAllBooks(this);
         }else {
            booksPresenter.setAllChapters(this);
         }


    }

    @Override
    public void setAllBooks() {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Book> books = booksPresenter.LoadingAllBooks(this);

        BooksAdapter booksAdapter = new BooksAdapter(this, books);

        booksPresenter.UnBind(this);

        recyclerView.setAdapter(booksAdapter);

        booksAdapter.setOnClickListner(new BooksAdapter.ClickListner() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(BooksActivity.this , BooksActivity.class);
                Book_ID=++position;
                intent.putExtra(BOOK_ID , Book_ID);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setAllChapters() {
        List<String>ChaptersNames=new ArrayList<>();

        List<Chapter> chaptersIntro = booksPresenter.loadingAllChapters(this);
        for (Chapter chapter : chaptersIntro) {
            if (chapter.getBook_ID() == Book_ID) {

                ChaptersNames.add(chapter.getAr_Name());
            }}

        recyclerView=findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ChaptersAdapter chaptersAdapter = new ChaptersAdapter(this, ChaptersNames);

        recyclerView.setAdapter(chaptersAdapter);

        chaptersAdapter.setOnCliclListner(new ChaptersAdapter.ClickListner() {
            @Override
            public void onClick(View v, int position) {

             Intent intent1=new Intent(BooksActivity.this , HadithActivity.class);

                intent1.putExtra(CHAPTER_ID , position+1);
                intent1.putExtra(BOOK_ID ,Book_ID );
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent1);
            }
        });
    }

}
