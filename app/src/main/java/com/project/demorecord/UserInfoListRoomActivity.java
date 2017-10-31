package com.project.demorecord;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.project.demorecord.model.UserInfo;
import com.project.demorecord.room.RoomUserInfoDatabase;
import com.project.demorecord.sqlite.UserInfoDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoListRoomActivity extends AppCompatActivity {

    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;

    private MyAdapter adapter;
    private RoomUserInfoDatabase infoDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);
        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        infoDatabase = Room.databaseBuilder(getApplicationContext(),
                RoomUserInfoDatabase.class, "DEMOINFO").build();

        final List<UserInfo> suggestSearchList = new ArrayList<>();
        new AsyncTask<Void, Void, List<UserInfo>>() {
            @Override
            protected List<UserInfo> doInBackground(Void... params) {
                Log.d("fdg", "ROOmmmmmmmmmm: referrrrrrrrRoom : "+  infoDatabase.userInfoRoomDao().getAll().get(0).getName());
                return infoDatabase.userInfoRoomDao().getAll();
            }

            @Override
            protected void onPostExecute(List<UserInfo> userInfos) {
                suggestSearchList.addAll(userInfos);
                if (suggestSearchList != null &&  suggestSearchList.size() > 0) {
                    displaySuggestsList(suggestSearchList);
                } else {
                    displaySuggestsList(new ArrayList<UserInfo>());
                }
            }
        }.execute();
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
