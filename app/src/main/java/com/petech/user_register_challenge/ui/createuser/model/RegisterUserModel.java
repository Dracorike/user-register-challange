package com.petech.user_register_challenge.ui.createuser.model;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.network.dtos.UserDTO;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public interface RegisterUserModel {
    long createUser();

    UserEntity getUserBuilded();

    Call<Void> createNewUser(UserDTO userDTO);

    void setUserPersonalInformation(UserPersonalInformation userInformation);

    void setUserAccountInformation(UserAccountInformation accountInformation);

    List<UserEntity> findUserByNickName(String nickName);
}
