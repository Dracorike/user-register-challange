package com.petech.user_register_challenge.ui.createuser.model;

import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;

public interface RegisterUserModel {
    long createUser();
    void setUserPersonalInformation(UserPersonalInformation userInformation);
    void serUserAccountInformation(UserAccountInformation accountInformation);
}
