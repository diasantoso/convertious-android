package dias_plbtw.com.asikinaja;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import dias_plbtw.com.asikinaja.Models.Data;

/**
 * Created by Dias on 2/28/2017.
 */
public class DataAdapter extends ArrayAdapter<Data> implements View.OnClickListener {
    Context context;
    int resLayout;
    List<Data> listData;

    TextView tvFormat;
    TextView tvQuality;
    TextView tvSize;
    TextView tvLink;
    Button btnDownload;
    Data navListData;

    public DataAdapter(Context context, int resLayout, List<Data> listData) {
        super(context, resLayout, listData);
        this.context = context;
        this.resLayout = resLayout;
        this.listData = listData;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View v = View.inflate(context, resLayout, null);

        tvFormat = (TextView) v.findViewById(R.id.txtFormat);
        tvQuality = (TextView) v.findViewById(R.id.txtQuality);
        tvSize = (TextView) v.findViewById(R.id.txtSize);
        tvLink = (TextView) v.findViewById(R.id.txtLink);
        btnDownload = (Button) v.findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(this);

        navListData = listData.get(position);

        tvFormat.setText("Format : "+navListData.getFormat());
        tvQuality.setText("Quality : "+navListData.getQuality());
        tvSize.setText("Size : "+navListData.getSize());
        tvLink.setText("Link : "+navListData.getLink());

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v == btnDownload) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_BROWSABLE);
            i.setData(Uri.parse(tvLink.getText().toString()));
            v.getContext().startActivity(i);
        }
    }
}
