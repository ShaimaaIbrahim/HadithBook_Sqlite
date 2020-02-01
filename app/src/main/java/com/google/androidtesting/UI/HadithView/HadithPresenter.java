package com.google.androidtesting.UI.HadithView;

import android.content.Context;

import com.google.androidtesting.Modell.Entities.Hadith;
import com.google.androidtesting.Repositories.DatabaseHelper;

import java.util.List;

public class HadithPresenter {


    private HadithView hadithView;

    public HadithPresenter(HadithView hadithView) {
        this.hadithView=hadithView;
    }

    public void setVars(){
        hadithView.setVars();
    }
    public int getAllHadithsCount(Context context){

      int countAhadith= DatabaseHelper.instance(context).getCursorAhadithCount();

      return countAhadith;
    }

    public void setViewPager(){
        hadithView.setViewPager();
    }


    public List<Hadith>getAllHadithes(Context context){
        List<Hadith>hadithList=DatabaseHelper.instance(context).getTexts();

        return hadithList;
    }

    public void setViewPagerPosition(){
        hadithView.setViewPagerPosition();
    }
}
