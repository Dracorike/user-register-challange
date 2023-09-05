package com.petech.user_register_challenge.ui.createuser.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.entity.UserType;
import com.petech.user_register_challenge.databinding.FragmentRegisterPersonalInformationBinding;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;
import com.petech.user_register_challenge.ui.createuser.view.RegisterUserActivity;
import com.petech.user_register_challenge.ui.createuser.viewmodel.RegisterUserViewModel;
import com.petech.user_register_challenge.utils.ErrorMessages;
import com.petech.user_register_challenge.utils.MaskEditUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterPersonalInformationFragment extends Fragment {
    private FragmentRegisterPersonalInformationBinding binding;
    private RegisterUserViewModel viewModel;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private String imageUri = "";
    private UserType userType = UserType.CPF;

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

        defineIfIsCPForCNPJ();
        setupObservables();
        setupComponents();

        return binding.getRoot();
    }

    private void defineIfIsCPForCNPJ() {
        Bundle extra = requireActivity().getIntent().getExtras();

        if (extra != null) {
            boolean isCpf = extra.getBoolean(RegisterUserActivity.IS_CPF);

            if (isCpf) {
                setupCpfField();
                userType = UserType.CPF;
            } else {
                setupCnjpField();
                userType = UserType.CNPJ;
            }
        }
    }

    private void setupCpfField() {
        binding.textUserCpfCnpjInputLabel.setText(getString(R.string.cpf_label));
        binding.inputTextCpfCnpjField.setHint(getString(R.string.cpf_example_hint));
        binding.inputTextCpfCnpjField.addTextChangedListener(
                MaskEditUtil.mask(binding.inputTextCpfCnpjField, MaskEditUtil.FORMAT_CPF));
    }

    private void setupCnjpField() {
        binding.textUserCpfCnpjInputLabel.setText(R.string.cnpj_label);
        binding.inputTextCpfCnpjField.setHint(R.string.cnpj_example_hint);
        binding.inputTextCpfCnpjField.addTextChangedListener(
                MaskEditUtil.mask(binding.inputTextCpfCnpjField, MaskEditUtil.FORMAT_CNPJ));
    }

    private void setupComponents() {
        binding.buttonNextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri.equals("")) {
                    showError(getString(R.string.select_a_picture_error));
                    return;
                }
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

        binding.inputTextBornDateField.addTextChangedListener(MaskEditUtil
                .mask(binding.inputTextBornDateField, MaskEditUtil.FORMAT_DATE));
    }

    private void setupObservables() {
        viewModel.getError().observe(getViewLifecycleOwner(), it -> handleError(it));

        viewModel.getUserPersonalInformationSuccess().observe(getViewLifecycleOwner(), it -> {
            Navigation.findNavController(requireView()).navigate(R.id.step_two_action);
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
        information.setUserType(userType);
        return information;
    }

    private boolean getGender() {
        int radioButtonId = binding.radioGroupGender.getCheckedRadioButtonId();
        Log.i("RADIOBUTTON", "IsMan?" + (radioButtonId == R.id.radio_button_man));
        return radioButtonId == R.id.radio_button_man;
    }

    private LocalDate parseDate(String date) {
        if (date.equals("")) {
            return LocalDate.now();
        }
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
