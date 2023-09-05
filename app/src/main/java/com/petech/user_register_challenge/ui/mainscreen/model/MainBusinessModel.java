package com.petech.user_register_challenge.ui.mainscreen.model;

import com.petech.user_register_challenge.data.entity.UserEntity;

import java.util.List;

public interface MainBusinessModel {
    long createNewUser(UserEntity newUser);
    int updateUser(UserEntity user);
    int deleteUser(UserEntity user);
    List<UserEntity> getAllUsers();
    void closeDatabase();
}
