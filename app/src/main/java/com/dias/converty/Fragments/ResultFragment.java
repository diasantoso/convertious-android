package com.dias.converty.Fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dias.converty.APIs.APIList;
import com.dias.converty.APIs.RestClient;
import com.dias.converty.DataAdapter;
import com.dias.converty.Models.APIBaseResponse;
import com.dias.converty.Models.Data;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dias_plbtw.com.convertme.R;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Dias on 8/28/2016.
 */
public class ResultFragment extends Fragment{

    private Call<APIBaseResponse> call;
    private APIList service;

    private String APIKEY = "1705b8735e9457938da1c65495a272ce";
    private String URL = "";

    private TextView title;
    private ImageView img;
    private ListView lvData;
    private DataAdapter dataAdapter;
    private List<Data> dataItems = new ArrayList<Data>();

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_result, container, false);
        if (container == null) {
            return null;
        }

        title = (TextView) view.findViewById(R.id.txtTitle);
        img = (ImageView) view.findViewById(R.id.imgView);
        lvData = (ListView) view.findViewById(R.id.listViewData);
        dataAdapter = new DataAdapter(view.getContext(), R.layout.item_data, dataItems);
        lvData.setAdapter(dataAdapter);

        //parsing url
        URL = getArguments().getString("url");

        fetchData();

        return view;
    }

    public void fetchData()
    {
        final SweetAlertDialog mProgressDialog;
        mProgressDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        mProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#e2162a"));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitleText("Converting...");
        mProgressDialog.show();

        service = RestClient.getClient();
        call = service.getConvert(URL, APIKEY);
        call.enqueue(new Callback<APIBaseResponse>() {
            @Override
            public void onResponse(Response<APIBaseResponse> response) {
                Log.d("NewFragment", "Status Code = " + response.code());
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    APIBaseResponse result = response.body();
                    Log.d("NewFragment", "response = " + new Gson().toJson(result));
                    if (result != null) {

                        title.setText(result.getTitle());
                        title.setVisibility(View.VISIBLE);

                        Glide.with(getView().getContext()).load(result.getImg()).into(img);

                        dataItems.clear();
                        List<Data> dataResponseItems = result.getData();

                        for(Data data : dataResponseItems) {
                            dataItems.add(data);
                            dataAdapter.notifyDataSetChanged();
                        }

                        mProgressDialog.dismiss();
                    }

                } else {
                    // response received but request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(view.getContext(), "Koneksi Ke Internet Gagal", Toast.LENGTH_SHORT).show();
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(view.getContext(), "Koneksi Ke Internet Gagal", Toast.LENGTH_SHORT).show();
                mProgressDialog.dismiss();
            }
        });
    }
}
