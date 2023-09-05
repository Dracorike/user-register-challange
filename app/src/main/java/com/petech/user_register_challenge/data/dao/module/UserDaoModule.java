package com.petech.user_register_challenge.data.dao.module;

import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.dao.UserDaoImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;


@Module
@InstallIn(SingletonComponent.class)
public abstract class UserDaoModule {
    @Singleton
    @Binds
    abstract UserDao bindsUserDao(
            UserDaoImpl bindUserDao
    );
}
