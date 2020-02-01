package com.google.androidtesting.UI.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.androidtesting.R;
import com.google.androidtesting.Repositories.DatabaseHelper;

import java.util.List;

public class HadithViewPagerAdapter extends PagerAdapter {

    private Context context;
    private List<String>mHadithList;

    public HadithViewPagerAdapter(Context context , List<String>mHadithList){
        this.context=context;
        this.mHadithList=mHadithList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(context).inflate(R.layout.hadith_list_item , container ,false);

      /*  WebView webView=view.findViewById(R.id.webview);
        webView.loadUrl("https://www.prothomalo.com/");
        WebSettings webSettings=webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);*/

        TextView hadith=view.findViewById(R.id.hadith);

           hadith.setText(mHadithList.get(position));


        container.addView(view ,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mHadithList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }
}
