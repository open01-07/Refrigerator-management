package com.cookandroid.appmaintest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cookandroid.appmaintest.Content1.EventActivity1;
import com.cookandroid.appmaintest.Content3.FoodRankActivity1;

import androidx.fragment.app.Fragment;


public class Fragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveDInstanceState) {
        return inflater.inflate(R.layout.fragment3_layout, container, false);
    }
    ImageView imageButton;
    @Override
    public  void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Rank1 버튼
        imageButton = view.findViewById(R.id.content3_1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), FoodRankActivity1.class);
                startActivity(intent1);
            }
        });
    }
}
