package com.petech.user_register_challenge.ui.createuser.model;

import android.database.SQLException;

import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;

import java.util.List;

import javax.inject.Inject;

public class RegisterUserModelImpl implements RegisterUserModel {
    private static UserEntity.Builder userBuilder;
    private UserDao userDao;

    @Inject
    public RegisterUserModelImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long createUser() throws SQLException {
        return userDao.createUser(userBuilder.build());
    }

    @Override
    public void setUserPersonalInformation(UserPersonalInformation userInformation) {
        getUserSingleton()
                .userImage(userInformation.getUserImage())
                .name(userInformation.getName())
                .address(userInformation.getAddress())
                .email(userInformation.getEmail())
                .bornDate(userInformation.getBornDate())
                .gender(userInformation.isGender())
                .cpfCnpj(userInformation.getCpfCnpj());
    }

    @Override
    public void setUserAccountInformation(UserAccountInformation accountInformation) {
        getUserSingleton()
                .nickName(accountInformation.getNickName())
                .password(accountInformation.getPassword().hashCode());
    }

    @Override
    public List<UserEntity> findUserByNickName(String nickName) {
        return userDao.findUserByNickName(nickName);
    }

    public static UserEntity.Builder getUserSingleton() {
        if (userBuilder == null) {
            userBuilder = new UserEntity.Builder();
        }

        return userBuilder;
    }

}
