package com.cookandroid.appmaintest;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.acl.Group;

import static com.cookandroid.appmaintest.R.id.navigation_item_attachment;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;


    private FirebaseAuth mAuth;
    private TextView nameText;

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.navi_ic);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //2xml 프래그먼트를 보여주기

        fragment1 = new Fragment1();

        fragment2 = new Fragment2();

        fragment3 = new Fragment3();

        fragment4 = new Fragment4();

        //프레그먼트를 메니져로 보여줌
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();


        //3탭기능 구성
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("음식 추천"));
        tabs.addTab(tabs.newTab().setText("분류"));
        tabs.addTab(tabs.newTab().setText("랭킹"));
        tabs.addTab(tabs.newTab().setText("냉장고 관리"));


        //탭버튼을 클릭했을 때 프레그먼트 동작
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                //선택된 탭 번호 반환
                int position = tab.getPosition();

                Fragment selected = null;

                if (position == 0) {

                    selected = fragment1;

                } else if (position == 1) {

                    selected = fragment2;

                } else if (position == 2) {

                    selected = fragment3;

                } else if (position == 3) {

                    selected = fragment4;
                }


                //선택된 프레그먼트로 바꿔줌
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        getHashKey();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();

        NavigationView navigationView;

        if (mAuth.getCurrentUser() == null) {

            navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();

                    int id = menuItem.getItemId();
                    switch (id) {

                        case navigation_item_attachment:
                            Intent intent = new Intent(getApplicationContext(), loginactivity.class);
                            startActivity(intent);
                            break;

                        case R.id.navigation_item_images:
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                            break;

                        case R.id.navigation_item_location:
                            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                            break;

                    }

                    return true;
                }
            });
        } else {

            navigationView = (NavigationView) findViewById(R.id.navigation_view);

            View view = navigationView.getHeaderView(0);
            nameText = (TextView) view.findViewById(R.id.texthead);
            nameText.setText(mAuth.getCurrentUser().getDisplayName());


            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();


                    int id = menuItem.getItemId();
                    switch (id) {
                        case R.id.navigation_item_attachment2:
                            mAuth.signOut();
                            LoginManager.getInstance().logOut();
                            finish();
                            Intent intent = getIntent();
                            startActivity(intent);
                            break;

                        case R.id.navigation_item_images:
                            Intent intent1 = new Intent(MainActivity.this, reciperegistrationActivity.class);
                            startActivity(intent1);
                            break;

                        case R.id.navigation_item_location:
                            Intent intent2 = new Intent(MainActivity.this, bookmarkrecipeActivity.class);
                            startActivity(intent2);
                            break;

                    }

                    return true;
                }
            });
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.menu, menu);
        menuInflater.inflate(R.menu.toolbar, menu);

        return true;
    }

    //상단바 search 문양 클릭 시 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){

            case R.id.toolbar_search_button:{
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        }

        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}