package com.petech.user_register_challenge.data.dao;

import com.petech.user_register_challenge.data.entity.UserEntity;

import java.util.List;

public interface UserDao {
    long createUser(UserEntity user);
    int updateUser(UserEntity user);
    int deleteUser(int userId);
    List<UserEntity> getAllUsers();
    void closeDatabase();
}
