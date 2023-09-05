package com.petech.user_register_challenge.ui.createuser.model.module;

import com.petech.user_register_challenge.ui.createuser.model.RegisterUserModel;
import com.petech.user_register_challenge.ui.createuser.model.RegisterUserModelImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class RegisterUserModelModule {
    @Binds
    abstract RegisterUserModel bindsRegisterUserModel(
          RegisterUserModelImpl registerUserModel
    );
}
