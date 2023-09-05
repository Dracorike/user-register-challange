package com.petech.user_register_challenge.ui.createuser.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.databinding.FragmentRegisterAccountInformationBinding;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.viewmodel.RegisterUserViewModel;
import com.petech.user_register_challenge.utils.ErrorMessages;

public class RegisterAccountInformationFragment extends Fragment {
    private FragmentRegisterAccountInformationBinding binding;
    private RegisterUserViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterAccountInformationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(RegisterUserViewModel.class);

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
        viewModel.getError().observe(getViewLifecycleOwner(), it -> {
            handleError(it);
        });

        viewModel.getUserCreationSuccess().observe(getViewLifecycleOwner(), it -> {
            if (it) {
                Toast.makeText(requireContext(), getString(R.string.user_created_with_successful), Toast.LENGTH_LONG).show();
                requireActivity().finish();
            }
        });
    }

    private void handleError(ErrorMessages error) {
        switch (error) {
            case CREATION_ERROR:
                showError(getString(R.string.something_wrong_when_you_cadastrate));
                break;
            case PASSWORD_INVALID_ERROR:
                showError(getString(R.string.invalid_password_message));
                break;
            case PASSWORD_NOT_SAME:
                showError(getString(R.string.password_is_not_same));
                break;
            case NICKNAME_ALREADY_EXISTS:
                showError(getString(R.string.nick_name_already_exists_error_message));
                break;
            case NETWORK_ERROR:
                showError(getString(R.string.network_error_message));
                break;
            default:
                showError(getString(R.string.unknown_error));
        }
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
