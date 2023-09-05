package com.petech.user_register_challenge.ui.updatescreen.model;

import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.entity.UserEntity;

import javax.inject.Inject;

public class UpdateModelImpl implements UpdateModel {
    private UserDao userDao;

    @Inject
    public UpdateModelImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity getUserById(int userId) {
        return userDao.findUserById(userId);
    }

    @Override
    public int updateUser(UserEntity userToUpdate) {
        return userDao.updateUser(userToUpdate);
    }
}
