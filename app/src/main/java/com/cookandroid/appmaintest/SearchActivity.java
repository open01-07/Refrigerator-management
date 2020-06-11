package com.cookandroid.appmaintest;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.cookandroid.appmaintest.Content1.EventActivity1;
import com.cookandroid.appmaintest.Content1.FoodActivity1;
import com.cookandroid.appmaintest.Content1.NewFoodActivity1;
import com.cookandroid.appmaintest.Content1.NewFoodActivity2;
import com.cookandroid.appmaintest.Content3.FoodRankActivity1;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class SearchActivity extends AppCompatActivity {
    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private ArrayList<String> checklistarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);
        checklistarray = new ArrayList<String>();
        checklistarray.addAll(list);
        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);


        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int index=0;

                for(int i=0; i<5; i++) {
                    if (list.get(position) == checklistarray.get(i)) index=i;
                }
                switch (index){
                    case 0:
                        Intent intent = new Intent(SearchActivity.this, EventActivity1.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(SearchActivity.this, FoodRankActivity1.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(SearchActivity.this, NewFoodActivity1.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(SearchActivity.this, NewFoodActivity2.class);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(SearchActivity.this, FoodActivity1.class);
                        startActivity(intent4);
                        break;
                }

                finish();
            }
        });

    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));

                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){

        list.add(getString(R.string.content3_1));
        list.add(getString(R.string.content3_2));
        list.add(getString(R.string.content3_3));
        list.add(getString(R.string.content3_4));
        list.add(getString(R.string.content3_5));
        list.add(getString(R.string.content3_6));
        list.add(getString(R.string.content3_7));
        list.add(getString(R.string.content3_8));
        list.add(getString(R.string.content3_9));
        list.add(getString(R.string.content3_10));

    }

    public void back(View view){
        finish();
    }

}
