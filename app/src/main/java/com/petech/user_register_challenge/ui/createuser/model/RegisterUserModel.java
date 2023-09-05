package com.petech.user_register_challenge.ui.createuser.model;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;

import java.util.List;

public interface RegisterUserModel {
    long createUser();
    void setUserPersonalInformation(UserPersonalInformation userInformation);
    void setUserAccountInformation(UserAccountInformation accountInformation);
    List<UserEntity> findUserByNickName(String nickName);
}
