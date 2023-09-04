package com.petech.user_register_challenge.data.dao;

import com.petech.user_register_challenge.data.entity.UserEntity;

import java.util.List;

public interface UserDao {
    long createUser(UserEntity user);
    void updateUser(UserEntity user);
    void deleteUser(UserEntity user);

    List<UserEntity> getAllUsers();
}
