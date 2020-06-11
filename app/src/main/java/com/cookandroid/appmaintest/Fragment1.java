package com.cookandroid.appmaintest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cookandroid.appmaintest.Content1.EventActivity1;
import com.cookandroid.appmaintest.Content1.FoodActivity1;
import com.cookandroid.appmaintest.Content1.NewFoodActivity1;
import com.cookandroid.appmaintest.Content1.NewFoodActivity2;

import java.util.Timer;
import java.util.TimerTask;

import androidx.fragment.app.Fragment;


public class Fragment1 extends Fragment {
    ImageView imageButton;
    ImageButton imageButton2, imageButton3, imageButton4;
    int i = 0;
    int[] imageArray = {R.drawable.event1_ic, R.drawable.event2_ic, R.drawable.event3_ic};
    Handler handler;
    Runnable runnable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveDInstanceState) {
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }

    @Override
    public  void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //event1 버튼
        imageButton = view.findViewById(R.id.event1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), EventActivity1.class);
                startActivity(intent1);
            }
        });
        imageButton2 = view.findViewById(R.id.img_food);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getActivity(), FoodActivity1.class);
                startActivity(intent2);
            }
        });
        imageButton3 = view.findViewById(R.id.content1_3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getActivity(), NewFoodActivity1.class);
                startActivity(intent3);
            }
        });
        imageButton4 = view.findViewById(R.id.content1_4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(getActivity(), NewFoodActivity2.class);
                startActivity(intent4);
            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        handler = new Handler(){
            public void handleMessage(Message msg){
                i++;
                if (i % 3 == 1) {
                    changeImage1();
                } else if (i % 3 == 2) {

                    changeImage2();
                } else if (i % 3 == 0) {
                    changeImage3();
                }
                Log.e("num", String.valueOf(i));
            }
        };

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);

            }
        };

        Timer timer = new Timer();
        timer.schedule(tt, 0, 3000);
    }
    public void changeImage1() {
        imageButton.setImageResource(R.drawable.event1_ic);
    }

    public void changeImage2() {
        imageButton.setImageResource(R.drawable.event2_ic);
    }

    public void changeImage3() {
        imageButton.setImageResource(R.drawable.event3_ic);
    }

}
