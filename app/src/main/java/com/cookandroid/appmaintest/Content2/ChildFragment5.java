package com.cookandroid.appmaintest.Content2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cookandroid.appmaintest.R;

import androidx.fragment.app.Fragment;


public class ChildFragment5 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.child_fragment_activity5, container, false);

    }

}
