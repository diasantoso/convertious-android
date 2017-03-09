package com.dias.convertious.Fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.facebook.share.model.AppInviteContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.AppInviteDialog;
import com.facebook.share.widget.ShareDialog;

import dias_plbtw.com.convertme.R;

/**
 * Created by Dias on 3/1/2017.
 */
public class ConnectFragment extends Fragment implements View.OnClickListener {

    private static Button btnPick;
    private static Button btnShare;
    ShareDialog shareDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (LinearLayout) inflater.inflate(R.layout.fragment_connect, container, false);
        if (container == null) {
            return null;
        }

        FacebookSdk.sdkInitialize(getActivity());

        btnPick = (Button)view.findViewById(R.id.btnPick);
        btnShare = (Button)view.findViewById(R.id.btnShare);
        btnPick.setOnClickListener(this);
        btnShare.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v == btnPick)
        {
            String appLinkUrl, previewImageUrl;

            appLinkUrl = "https://fb.me/254592188285445";
            //previewImageUrl = "http://hnwtvc.com/images/tutorial1.png";

            if (AppInviteDialog.canShow()) {
                AppInviteContent content = new AppInviteContent.Builder()
                        .setApplinkUrl(appLinkUrl)
                        //.setPreviewImageUrl(previewImageUrl)
                        .build();
                AppInviteDialog.show(this, content);
            }
        } else if(v == btnShare)
        {
            shareDialog = new ShareDialog(this);
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Convertious")
                    .setContentDescription(
                            "Convertious containt video youtube converter and downloader.")
                    .setContentUrl(Uri.parse("https://play.google.com/store/apps/details?id=com.dias.convertious"))
                    .build();

            shareDialog.show(linkContent);
        }
    }
}
