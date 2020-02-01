package com.google.androidtesting.UI.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.androidtesting.Modell.Entities.Book;
import com.google.androidtesting.R;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.viewHolder> {

    private Context context;
    private List<Book>mBooksList;
    public static ClickListner clickListner;

    public BooksAdapter(Context context , List<Book>mBooksList){

        this.context=context;
        this.mBooksList=mBooksList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(context).inflate(R.layout.book_list_item , viewGroup ,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {

        Book book=mBooksList.get(i);
        viewHolder.bookName.setText(book.getAr_Name());

    }

    @Override
    public int getItemCount() {
       return mBooksList.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bookName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            bookName = itemView.findViewById(R.id.book_name);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            clickListner.onClick(v, getAdapterPosition());
        }


    }
        public  interface ClickListner{
         void onClick(View view , int position);
        }

    public void setOnClickListner(ClickListner clickListner){
        BooksAdapter.clickListner=clickListner;
    }
}
