package com.cookandroid.appmaintest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cookandroid.appmaintest.Content2.ChildFragment1;
import com.cookandroid.appmaintest.Content2.ChildFragment2;
import com.cookandroid.appmaintest.Content2.ChildFragment3;
import com.cookandroid.appmaintest.Content2.ChildFragment4;
import com.cookandroid.appmaintest.Content2.ChildFragment5;

import androidx.fragment.app.Fragment;


public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveDInstanceState) {
        View v = inflater.inflate(R.layout.fragment2_layout, container, false);

        getFragmentManager().beginTransaction().add(R.id.child_fragment, new ChildFragment1()).commit();

        //하위버튼
        Button subButton1 = (Button) v.findViewById(R.id.subButton1);
        Button subButton2 = (Button) v.findViewById(R.id.subButton2);
        Button subButton3 = (Button) v.findViewById(R.id.subButton3);
        Button subButton4 = (Button) v.findViewById(R.id.subButton4);
        Button subButton5 = (Button) v.findViewById(R.id.subButton5);

        //클릭 이벤트
        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment1()).commit();
            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment2()).commit();

            }
        });
        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment3()).commit();

            }
        });
        subButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment4()).commit();

            }
        });
        subButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.child_fragment, new ChildFragment5()).commit();

            }
        });
        return v;
    }


   @Override
    public  void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       

    }

}
