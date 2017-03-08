package com.dias.convertious.Fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dias_plbtw.com.convertme.R;

/**
 * Created by Dias on 8/28/2016.
 */
public class BrowseFragment extends Fragment implements View.OnClickListener {

    private EditText urltxt;
    private Button gobtn;
    private Button convbtn;

    private WebView webview;
    private SweetAlertDialog mProgressDialog;

    private String url = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browse, container, false);
        if (container == null) {
            return null;
        }

        urltxt = (EditText) view.findViewById(R.id.urlTxt);
        gobtn = (Button) view.findViewById(R.id.goBtn);
        webview = (WebView) view.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);

        //setting webview (agar bisa buka youtube)
        webview.getSettings().setAllowContentAccess(true);
        WebSettings webSettings = webview.getSettings();
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webview.canGoBack();
        webview.setWebChromeClient(new WebChromeClient() {});

        convbtn = (Button) view.findViewById(R.id.btnConvert);

        gobtn.setOnClickListener(this);
        convbtn.setOnClickListener(this);

        mProgressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        mProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#e2162a"));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitleText("Loading Page");

        webview.setWebViewClient(new MyWebViewClient());

        openURL();

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == gobtn) {
            url = urltxt.getText().toString();
            if (!url.startsWith("www.") && !url.startsWith("https://")) {
                url = "www." + url;
            }
            if (!url.startsWith("https://")) {
                url = "http://" + url;
            }
            urltxt.setText(url);
            openURL();
        } else if (v == convbtn) {
            if(urltxt.getText().toString().equalsIgnoreCase("")) {
                SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Alert");
                dialog.setContentText("Please choose URL first");
                dialog.show();
            } else if (!urltxt.getText().toString().startsWith("https://m.youtube.com/watch?v=")) {
                SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Alert");
                dialog.setContentText("Only youtube video which can convert");
                dialog.show();
            } else {
                SweetAlertDialog dialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.NORMAL_TYPE);
                dialog.setTitleText("Confirmation");
                dialog.setContentText("Are you sure to convert this video?");
                dialog.setCancelText("No, Go Back");
                dialog.setConfirmText("Yes, Of course");
                dialog.showCancelButton(true);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();

                        //parsing url + pindah fragment
                        Bundle i = new Bundle();
                        i.putString("url", urltxt.getText().toString());

                        ResultFragment frag = new ResultFragment();
                        frag.setArguments(i);

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.framelay, frag)
                                .commit();
                    }
                });
                dialog.show();
            }
        }
    }

    private void openURL() {
        webview.loadUrl(url);
        webview.requestFocus();
    }

    //prosedur web view
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO show you progress image
            mProgressDialog.show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO hide your progress image
            mProgressDialog.dismiss();
            urltxt.setText(view.getOriginalUrl());
            super.onPageFinished(view, url);
        }
    }
}