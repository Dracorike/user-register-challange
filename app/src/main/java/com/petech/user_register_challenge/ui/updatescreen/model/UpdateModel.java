package com.petech.user_register_challenge.ui.updatescreen.model;

import com.petech.user_register_challenge.data.entity.UserEntity;

public interface UpdateModel {
    UserEntity getUserById(int userId);
    int updateUser(UserEntity userToUpdate);
}
