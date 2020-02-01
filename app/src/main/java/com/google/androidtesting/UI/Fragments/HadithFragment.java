package com.google.androidtesting.UI.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.androidtesting.Modell.Entities.Hadith;
import com.google.androidtesting.R;
import com.google.androidtesting.Repositories.DatabaseHelper;
import com.google.androidtesting.UI.HadithView.HadithActivity;

import java.util.List;


public class HadithFragment extends Fragment {


    private TextView textView;
    private WebView webView;

    private String Html="<html>\n" + "<head>\n" + "     <meta name=\"viewport\" content=\"width=device-width, initial-scale=1," +
            "maximum-scale=2, user-scalable=yes\">\n" + "</head>\n" + "</html>";

    public HadithFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView= LayoutInflater.from(getContext()).inflate(R.layout.fragment_hadith, container, false);


        textView=rootView.findViewById(R.id.text_hadith);
        webView=rootView.findViewById(R.id.web_view);

      return rootView;
    }



    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            String AR_Text = getArguments().getString("HADITH_TEXT");
            textView.setText(AR_Text);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().getDisplayZoomControls();

            webView.getSettings().setBuiltInZoomControls(true);
            webView.setInitialScale(100);
            webView.getSettings().setSupportZoom(true);

            webView.loadUrl("file:///android_asset/www/html");
        }
    }
}
