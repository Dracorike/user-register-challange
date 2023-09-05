package com.petech.user_register_challenge.ui.updatescreen.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.updatescreen.model.UpdateModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class UpdateViewModel extends ViewModel {
    private MutableLiveData<UserEntity> user = new MutableLiveData();
    private MutableLiveData<Boolean> updateSuccess = new MutableLiveData();
    private UpdateModel model;

    @Inject
    public UpdateViewModel(UpdateModel model) {
        this.model = model;
    }

    public void getUserById(int userId) {
        UserEntity findedUser = model.getUserById(userId);
        user.postValue(findedUser);
    }

    public void updateUser(UserEntity user) {
        int total = model.updateUser(user);
        updateSuccess.postValue(total > 0);
    }

    public LiveData<UserEntity> getUser() {
        return user;
    }

    public LiveData<Boolean> isSuccessUpdate() {
        return updateSuccess;
    }
}
