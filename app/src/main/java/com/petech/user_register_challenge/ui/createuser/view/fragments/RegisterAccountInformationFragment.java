package com.petech.user_register_challenge.ui.createuser.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.petech.user_register_challenge.databinding.FragmentRegisterAccountInformationBinding;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.viewmodel.RegisterUserViewModel;

public class RegisterAccountInformationFragment extends Fragment {
    private FragmentRegisterAccountInformationBinding binding;
    private RegisterUserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterAccountInformationBinding.inflate(inflater, container, false);
        setupComponents();
        setupObservables();
        return binding.getRoot();
    }

    private void setupComponents() {
        binding.buttonCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.createNewUser(getFieldsValues());
            }
        });
    }

    private UserAccountInformation getFieldsValues() {
        UserAccountInformation accountInformation = new UserAccountInformation();
        accountInformation.setNickName(binding.inputTextNickNameField.getText().toString());
        accountInformation.setPassword(binding.inputTextPasswordField.getText().toString());
        accountInformation.setConfirmPassword(binding.inputTextConfirmPasswordField.getText().toString());
        return accountInformation;
    }

    private void setupObservables() {

    }
}
