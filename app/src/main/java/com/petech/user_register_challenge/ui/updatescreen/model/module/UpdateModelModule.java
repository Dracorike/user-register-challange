package com.petech.user_register_challenge.ui.updatescreen.model.module;

import com.petech.user_register_challenge.ui.updatescreen.model.UpdateModel;
import com.petech.user_register_challenge.ui.updatescreen.model.UpdateModelImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class UpdateModelModule {
    @Binds
    abstract UpdateModel bindsUpdateModel(UpdateModelImpl implementation);
}
