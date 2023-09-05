package com.petech.user_register_challenge.ui.createuser.model.beans;

public class UserAccountInformation {
    private String nickName;
    private int password; //TODO: Lembre-se, isso é um hash

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
