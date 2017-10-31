package com.project.demorecord;

import com.project.demorecord.model.UserInfo;

import java.util.List;

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    private List<UserInfo> userInfoList;
}
