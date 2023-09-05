package com.petech.user_register_challenge.ui.updatescreen.view;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.data.entity.UserType;
import com.petech.user_register_challenge.databinding.ActivityUpdateBinding;
import com.petech.user_register_challenge.ui.updatescreen.viewmodel.UpdateViewModel;
import com.petech.user_register_challenge.utils.MaskEditUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "EXTRA-USER-ID";
    private ActivityUpdateBinding binding;
    private UpdateViewModel viewModel;
    private ActivityResultLauncher<PickVisualMediaRequest> pickMedia;
    private int userId = 0;
    private int password = 0;
    private String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(UpdateViewModel.class);
        pickMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                binding.imageUserPhotoPickerUpdate.setImageURI(uri);
                imageUri = uri.toString();
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });
        getExtras();
        setObservables();
    }


    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int userId = extras.getInt(EXTRA_ID);
            this.userId = userId;
            viewModel.getUserById(userId);
        }
    }

    private void setObservables() {
        viewModel.getUser().observe(this, this::fillFields);
    }

    private void setupComponents(UserEntity user) {
        if (user.userType() == UserType.CPF) {
            binding.inputTextCpfCnpjFieldUpdate.addTextChangedListener(MaskEditUtil
                    .mask(binding.inputTextCpfCnpjFieldUpdate, MaskEditUtil.FORMAT_CPF));
        } else {
            binding.inputTextCpfCnpjFieldUpdate.addTextChangedListener(MaskEditUtil
                    .mask(binding.inputTextCpfCnpjFieldUpdate, MaskEditUtil.FORMAT_CNPJ));
        }

        binding.textInputBornDateFieldUpdate.addTextChangedListener(MaskEditUtil
                .mask(binding.textInputBornDateFieldUpdate, MaskEditUtil.FORMAT_DATE));

        binding.buttonUpdateUser.setEnabled(true);
        binding.buttonUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.updateUser(getFieldsValues(user));
            }
        });
    }

    private void fillFields(UserEntity user) {
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        binding.imageUserPhotoPickerUpdate.setImageURI(Uri.parse(user.getUserImage()));
        binding.inputTextNameFieldUpdate.setText(user.getName());
        binding.inputTextNickNameFieldUpdate.setText(user.getNickName());
        binding.inputTextAddressFieldUpdate.setText(user.getAddress());
        binding.inputTextEmailAddressFieldUpdate.setText(user.getEmail());
        binding.textInputBornDateFieldUpdate.setText(user.getBornDate().format(formatters));
        if (user.isGender()) {
            binding.radioButtonManUpdate.toggle();
        } else {
            binding.radioButtonWomanUpdate.toggle();
        }

        password = user.getPassword();

        setupComponents(user);
    }

    private UserEntity getFieldsValues(UserEntity user) {
        if (imageUri == null) {
            imageUri = user.getUserImage();
        }

        return new UserEntity.Builder()
                .id(userId)
                .password(password)
                .userImage(imageUri)
                .name(binding.inputTextNameFieldUpdate.getText().toString())
                .nickName(binding.inputTextNickNameFieldUpdate.getText().toString())
                .address(binding.inputTextAddressFieldUpdate.getText().toString())
                .email(binding.inputTextEmailAddressFieldUpdate.getText().toString())
                .bornDate(parseDate(binding.textInputBornDateFieldUpdate.getText().toString()))
                .gender(getGender())
                .build();
    }

    private LocalDate parseDate(String date) {
        if (date.equals("")) {
            return LocalDate.now();
        }
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatters);
    }

    private boolean getGender() {
        int radioButtonId = binding.radioGroupGenderUpdate.getCheckedRadioButtonId();
        Log.i("RADIOBUTTON", "IsMan?" + (radioButtonId == R.id.radio_button_man));
        return radioButtonId == R.id.radio_button_man;
    }
}