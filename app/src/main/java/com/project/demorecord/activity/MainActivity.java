package com.project.demorecord.activity;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.project.demorecord.util.CommonSharePreference;
import com.project.demorecord.R;
import com.project.demorecord.model.UserInfoList;
import com.project.demorecord.model.UserInfo;
import com.project.demorecord.room.RoomUserInfoDatabase;
import com.project.demorecord.sqlite.UserInfoDB;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editTExtName)
    EditText editTExtName;

    @BindView(R.id.editTextAge)
    EditText editTextAge;
//    private UserInfoDB userInfoDB;
    private RoomUserInfoDatabase infoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        userInfoDB = new UserInfoDB(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);



        infoDatabase = Room.databaseBuilder(getApplicationContext(),
                RoomUserInfoDatabase.class, "DEMOINFO")
                .fallbackToDestructiveMigration()
                .build();

    }

    private void popupOK(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", null);
        builder.show();
    }

    @OnClick(R.id.buttonAdded)
    public void added() {
        String name = editTExtName.getText().toString();
        String age = editTextAge.getText().toString();

        if (name.isEmpty() || age.isEmpty()) {
            popupOK("Error", "Please Enter user info");
        } else {
            setUserInfo(name, age);
        }
    }

    @OnClick(R.id.buttonGotoList)
    public void gotoList() {
        Intent intent = new Intent(this, UserInfoListActivity.class);
        startActivity(intent);
    }

    private void setUserInfo(String name, String age) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(age);
        storeDataInMemory(userInfo);
    }

    private void storeDataInMemory(UserInfo userInfo) {
        savePreference(userInfo);
       // sqliteInsert(userInfo);
        roomInsert(userInfo);
    }

    private void savePreference(UserInfo userInfo) {
        CommonSharePreference preference = new CommonSharePreference(this);
        UserInfoList userInfoList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);
        if (userInfoList == null) {
            userInfoList = new UserInfoList();
            List<UserInfo> suggests = new ArrayList<>();
            suggests.add(userInfo);
            userInfoList.setUserInfoList(suggests);
        } else {
            userInfoList.getUserInfoList().add(userInfo);
        }
        preference.save(UserInfoListActivity.EXTTRA_LIST, userInfoList);
        //popupOK("Store data", "Save preference success");
    }

    private void sqliteInsert(UserInfo userInfo) {
    //    userInfoDB.insert(userInfo);
    }

    private void roomInsert(final UserInfo userInfo) {

        new AsyncTask<Void, Void, UserInfo>() {
            @Override
            protected UserInfo doInBackground(Void... params) {
                infoDatabase.userInfoRoomDao().insert(userInfo);
                return userInfo;
            }

            @Override
            protected void onPostExecute( UserInfo userInfo) {
               // popupOK("Store data", "Insert Room success");
            }
        }.execute();
    }

    @OnClick(R.id.buttonSQLite)
    public void gotoSQL() {
        Intent intent = new Intent(this, UserInfoListSQLActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.buttonRoom)
    public void gotoRoom() {
        Intent intent = new Intent(this, UserInfoListRoomActivity.class);
        startActivity(intent);
    }

}
