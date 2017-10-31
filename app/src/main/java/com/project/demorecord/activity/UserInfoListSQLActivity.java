package com.project.demorecord.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.project.demorecord.R;
import com.project.demorecord.adapter.MyAdapter;
import com.project.demorecord.model.UserInfo;
import com.project.demorecord.model.UserInfoList;
import com.project.demorecord.sqlite.UserInfoDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoListSQLActivity extends AppCompatActivity {
    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;
    private MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);

       // UserInfoDB userInfoDB = new UserInfoDB(this);
       // UserInfoList suggestSearchList = userInfoDB.findAll();

        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

//        if (suggestSearchList.getUserInfoList() != null) {
//            displaySuggestsList(suggestSearchList.getUserInfoList());
//        } else {
            displaySuggestsList(new ArrayList<UserInfo>());
//        }
    }

    public void displaySuggestsList(List<UserInfo> suggestsList) {
        if (suggestsList.size() <= 0) {
            textNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            textNotFound.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter.setData(suggestsList);
            adapter.notifyDataSetChanged();
        }

    }

}
