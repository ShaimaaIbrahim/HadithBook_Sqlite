package com.google.androidtesting.UI.HadithView;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.androidtesting.Modell.Entities.Book;
import com.google.androidtesting.Modell.Entities.Hadith;
import com.google.androidtesting.R;
import com.google.androidtesting.Repositories.DatabaseHelper;
import com.google.androidtesting.UI.Adapters.ChaptersAdapter;
import com.google.androidtesting.UI.Adapters.HadithViewPagerAdapter;
import com.google.androidtesting.UI.BookAndChaptersView.BooksActivity;
import com.google.androidtesting.UI.Fragments.HadithFragment;
import java.util.ArrayList;
import java.util.List;


public class HadithActivity extends AppCompatActivity implements HadithView {

   //vars
    private ViewPager viewPager;
    private static final String HADITH_TEXT="HADITH_TEXT";

    //classes
    private HadithPresenter hadithPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadith);


        hadithPresenter=new HadithPresenter(this);
        hadithPresenter.setVars();
        hadithPresenter.setViewPager();
        hadithPresenter.setViewPagerPosition();

    }

    @Override
    public void setVars() {
        viewPager=findViewById(R.id.view_pager);

    }


    @Override
    public void setViewPager() {
        viewPagerAdapter viewPagerAdapter = new viewPagerAdapter(getSupportFragmentManager());
      int  countAhadith=hadithPresenter.getAllHadithsCount(this);
        for (int count =1; count<= countAhadith ; count++) {

            Log.e("COUNT" , count +"");
            viewPagerAdapter.AddFragment(new HadithFragment());

        }
        viewPager.setAdapter(viewPagerAdapter);
    }


    @Override
    public void setViewPagerPosition() {

        Intent intent=getIntent();

        int Chapter_ID=intent.getIntExtra(BooksActivity.CHAPTER_ID ,0);
        int Book_ID=   intent.getIntExtra(BooksActivity.BOOK_ID , 0);

        List<Hadith> mHadith = hadithPresenter.getAllHadithes(this);

        int i=0;
        for (Hadith hadith  : mHadith){

            if (hadith.getChapter_ID()==Chapter_ID && hadith.getBook_ID()==Book_ID){

                viewPager.setCurrentItem(i);
                break;
            }
            i++; }
    }




    class  viewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment>fragments;

        private List<Hadith>hadithList=hadithPresenter.getAllHadithes(getApplicationContext());

        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments=new ArrayList<>();

        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment=fragments.get(i);

            Bundle bundle=new Bundle();
            bundle.putString(HADITH_TEXT , hadithList.get(i).getAr_Text());

            fragment.setArguments(bundle);

            return fragment;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void AddFragment(Fragment fragment  ){
            fragments.add(fragment);

        }

    }
}
