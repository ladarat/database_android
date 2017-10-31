package com.project.demorecord.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.project.demorecord.model.UserInfo;

import java.util.List;

@Dao
public interface UserInfoRoomDao {
    @Query("SELECT * FROM UserInfo")
    List<UserInfo> getAll();


    @Insert
    void insert(UserInfo userInfo);
}
