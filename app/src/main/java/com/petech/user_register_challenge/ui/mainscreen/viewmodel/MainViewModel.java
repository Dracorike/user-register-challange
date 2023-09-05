package com.petech.user_register_challenge.ui.mainscreen.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.petech.user_register_challenge.data.dao.UserDaoUtil;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.mainscreen.model.MainBusinessModel;

import java.time.LocalDate;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private MainBusinessModel model;

    @Inject
    public MainViewModel(MainBusinessModel model) {
        this.model = model;
    }

    public void createNewUser() {
        UserEntity userMock = new UserEntity.Builder()
                .name("Pedro Henrique")
                .password(12546847)
                .userImage("")
                .address("Endereço")
                .email("pedro@email.com")
                .bornDate(LocalDate.now())
                .gender(true)
                .cpfCnpj("12365478")
                .build();
        long result = model.createNewUser(userMock);

        Log.i("TAG", "Resultado: " + result);
    }
}
