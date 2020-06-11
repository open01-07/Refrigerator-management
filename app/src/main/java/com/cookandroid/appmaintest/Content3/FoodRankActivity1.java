package com.cookandroid.appmaintest.Content3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cookandroid.appmaintest.R;

import androidx.appcompat.app.AppCompatActivity;


public class FoodRankActivity1 extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodranklayout1);

        imageView = findViewById(R.id.img_btn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
