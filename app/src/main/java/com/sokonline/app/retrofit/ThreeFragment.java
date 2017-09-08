package com.sokonline.app.retrofit;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ThreeFragment extends Fragment {

    public ThreeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        //Inflate the root view
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);
        return rootView;
    }

}
