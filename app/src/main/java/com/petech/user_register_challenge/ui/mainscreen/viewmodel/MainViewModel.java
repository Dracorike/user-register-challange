package com.petech.user_register_challenge.ui.mainscreen.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.mainscreen.model.MainBusinessModel;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> hasUserRegistered = new MutableLiveData();
    private MutableLiveData<List<UserEntity>> usersList = new MutableLiveData();
    private MutableLiveData<Boolean> userDeleted = new MutableLiveData();
    private MainBusinessModel model;

    @Inject
    public MainViewModel(MainBusinessModel model) {
        this.model = model;
    }

    public void hasUserRegistered() {
        new Thread(() -> {
            List<UserEntity> users = model.getAllUsers();
            hasUserRegistered.postValue(users.size() > 0);
        }).start();
    }

    public void getAllUsers() {
        new Thread(() -> {
            List<UserEntity> users = model.getAllUsers();
            usersList.postValue(users);
        }).start();
    }

    public void deleteUser(int userId) {
        new Thread(() -> {
            int deleted = model.deleteUser(userId);
            userDeleted.postValue(deleted > 0);
        }).start();
    }

    public LiveData<Boolean> getHasUserRegistered() {
        return hasUserRegistered;
    }

    public LiveData<List<UserEntity>> getUsersList() {
        return usersList;
    }

    public LiveData<Boolean> getUserDeleted() {
        return userDeleted;
    }
}
