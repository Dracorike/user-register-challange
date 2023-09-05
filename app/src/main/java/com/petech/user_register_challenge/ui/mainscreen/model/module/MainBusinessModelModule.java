package com.petech.user_register_challenge.ui.mainscreen.model.module;

import com.petech.user_register_challenge.ui.mainscreen.model.MainBusinessModel;
import com.petech.user_register_challenge.ui.mainscreen.model.MainModelBusinessImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class MainBusinessModelModule {
    @Binds
    abstract MainBusinessModel bindMainBusinessModel(
            MainModelBusinessImpl bindModelImpl
    );
}
