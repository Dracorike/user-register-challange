package com.petech.user_register_challenge.ui.mainscreen.model;

import android.database.SQLException;

import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;

public class MainModelBusinessImpl implements MainBusinessModel {
    private UserDao userDao;

    @Inject
    public MainModelBusinessImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long createNewUser(UserEntity newUser) {
        try {
            return userDao.createUser(newUser);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return -1;
        }
    }

    @Override
    public int updateUser(UserEntity user) {
        return userDao.updateUser(user);
    }

    @Override
    public int deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void closeDatabase() {
        userDao.closeDatabase();
    }
}
