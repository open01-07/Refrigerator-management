package com.cookandroid.appmaintest;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.Group;

import static com.cookandroid.appmaintest.R.id.navigation_item_attachment;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private FirebaseAuth mAuth;
    private TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("");
        //상단바
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.navi_ic);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mAuth = FirebaseAuth.getInstance();


        NavigationView navigationView;

            if (mAuth.getCurrentUser() ==null) {

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
                                Toast.makeText(MainActivity.this,"ERROR", Toast.LENGTH_LONG).show();
                                break;

                            case R.id.navigation_item_location:
                                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_LONG).show();
                                break;

                        }

                        return true;
                    }
                });
            }
        else {

                navigationView = (NavigationView) findViewById(R.id.navigation_view);

                View view = navigationView.getHeaderView(0);
                nameText=(TextView)view.findViewById(R.id.texthead);
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
                                finish();
                                Intent intent = getIntent();
                                startActivity(intent);
                                break;

                            case R.id.navigation_item_images:
                                Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                                break;

                            case R.id.navigation_item_location:
                                Toast.makeText(MainActivity.this, menuItem.getTitle(), Toast.LENGTH_LONG).show();
                                break;

                        }

                        return true;
                    }
                });
            }



        //탭
        TabHost tabHost1 = (TabHost) findViewById(R.id.tabHost1) ;
        tabHost1.setup() ;

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator("음식 추천") ;
        tabHost1.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        TabHost.TabSpec ts2 = tabHost1.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator("분류") ;
        tabHost1.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabHost1.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator("랭킹") ;
        tabHost1.addTab(ts3) ;

        // 네 번째 Tab. (탭 표시 텍스트:"TAB 4"), (페이지 뷰:"content4")
        TabHost.TabSpec ts4 = tabHost1.newTabSpec("Tab Spec 4") ;
        ts4.setContent(R.id.content4) ;
        ts4.setIndicator("냉장고 관리") ;
        tabHost1.addTab(ts4) ;
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
                Toast.makeText(this.getApplicationContext(),"search", Toast.LENGTH_SHORT).show();
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