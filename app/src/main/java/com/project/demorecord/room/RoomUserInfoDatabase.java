package com.project.demorecord.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.project.demorecord.model.UserInfo;

@Database(entities = {UserInfo.class}, version = 2)
public abstract class RoomUserInfoDatabase  extends RoomDatabase{
    public abstract UserInfoRoomDao userInfoRoomDao();
}
