package com.dias.converty.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dias_plbtw.com.convertme.R;

/**
 * Created by Dias on 3/1/2017.
 */
public class AboutFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        if (container == null) {
            return null;
        }

        return view;
    }
}
