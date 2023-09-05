package com.petech.user_register_challenge.ui.createuser.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.databinding.FragmentRegisterPersonalInformationBinding;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;
import com.petech.user_register_challenge.ui.createuser.viewmodel.RegisterUserViewModel;
import com.petech.user_register_challenge.utils.ErrorMessages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterPersonalInformationFragment extends Fragment {
    private FragmentRegisterPersonalInformationBinding binding;
    private RegisterUserViewModel viewModel;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private String imageUri = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                binding.imageUserPhotoPicker.setImageURI(uri);
                imageUri = uri.toString();
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterPersonalInformationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(RegisterUserViewModel.class);

        setupObservables();
        setupComponents();

        return binding.getRoot();
    }

    private void setupComponents() {
        binding.buttonNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setUserPersonalInformation(getFieldsValues());
            }
        });

        binding.imageUserPhotoPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });
    }

    private void setupObservables() {
        viewModel.getError().observe(getViewLifecycleOwner(), it -> handleError(it));

        viewModel.getUserPersonalInformationSuccess().observe(getViewLifecycleOwner(), it -> {

        });
    }

    private UserPersonalInformation getFieldsValues() {
        UserPersonalInformation information = new UserPersonalInformation();
        information.setUserImage(imageUri);
        information.setName(binding.inputTextNameField.getText().toString());
        information.setEmail(binding.inputTextEmailAddressField.getText().toString());
        information.setAddress(binding.inputTextAddressField.getText().toString());
        information.setCpfCnpj(binding.inputTextCpfCnpjField.getText().toString());
        information.setBornDate(parseDate(binding.inputTextBornDateField.getText().toString()));
        information.setGender(getGender());
        return information;
    }

    private boolean getGender(){
        int radioButtonId = binding.radioGroupGender.getCheckedRadioButtonId();
        Log.i("RADIOBUTTON", "IsMan?" + (radioButtonId == R.id.radio_button_man));
        return radioButtonId == R.id.radio_button_man;
    }
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatters);
    }

    private void handleError(ErrorMessages error) {
        switch (error) {
            case EMAIL_INVALID:
                showError(getString(R.string.email_invalid_error_message));
                break;
            case NAME_VERY_SHORT:
                showError(getString(R.string.name_very_shot_error_message));
                break;
            case MINOR_EIGHTEEN_USER:
                showError(getString(R.string.minor_eighteen_error_message));
        }
    }

    private void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
