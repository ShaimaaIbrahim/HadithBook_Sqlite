package com.google.androidtesting.UI.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.androidtesting.Modell.Entities.Chapter;
import com.google.androidtesting.R;

import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.viewHolder> {

    private Context context;
    private List<String>mChaptersIntro;
    public static ClickListner clickListner;


    public ChaptersAdapter(Context context , List<String>mChaptersIntro){
      this.context=context;
      this.mChaptersIntro=mChaptersIntro;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.book_list_item , viewGroup , false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
   String chapter_Intro=mChaptersIntro.get(i);
     viewHolder.chapterIntro.setText(chapter_Intro);
    }

    @Override
    public int getItemCount() {
        return mChaptersIntro.size();
    }

    class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView chapterIntro;

        public viewHolder(@NonNull View itemView)  {
            super(itemView);

               chapterIntro=itemView.findViewById(R.id.book_name);
                itemView.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            clickListner.onClick(v , getAdapterPosition());
        }
    }

    public void setOnCliclListner(ClickListner cliclListner){
        ChaptersAdapter.clickListner=cliclListner;
    }
    public interface ClickListner{
        void onClick(View v , int position);
    }
}
