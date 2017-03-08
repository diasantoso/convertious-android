package com.dias.convertious;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.dias.convertious.Models.Data;

import java.util.List;

import dias_plbtw.com.convertme.R;


/**
 * Created by Dias on 2/28/2017.
 */
public class DataAdapter extends ArrayAdapter<Data> {
    Context context;
    int resLayout;
    List<Data> listData;

    Data navListData;

    static class ViewHolderItem {
        TextView tvFormat;
        TextView tvQuality;
        TextView tvSize;
        TextView tvLink;
        Button btnDownload;
    }

    public DataAdapter(Context context, int resLayout, List<Data> listData) {
        super(context, resLayout, listData);
        this.context = context;
        this.resLayout = resLayout;
        this.listData = listData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolderItem viewHolder;

        if(convertView==null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resLayout, null, false);

            viewHolder = new ViewHolderItem();
            viewHolder.tvFormat = (TextView) convertView.findViewById(R.id.txtFormat);
            viewHolder.tvQuality = (TextView) convertView.findViewById(R.id.txtQuality);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.txtSize);
            viewHolder.tvLink = (TextView) convertView.findViewById(R.id.txtLink);
            viewHolder.btnDownload = (Button) convertView.findViewById(R.id.btnDownload);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        navListData = listData.get(position);

        viewHolder.tvFormat.setText("Format : "+navListData.getFormat());
        viewHolder.tvQuality.setText("Quality : "+navListData.getQuality());
        viewHolder.tvSize.setText("Size : "+navListData.getSize());
        viewHolder.tvLink.setText("Link : "+navListData.getLink());

        viewHolder.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = navListData.getLink();

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            }
        });

        return convertView;
    }
}
