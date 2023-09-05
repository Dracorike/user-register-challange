package com.petech.user_register_challenge.ui.createuser.viewmodel;

import static com.petech.user_register_challenge.utils.AppUtils.VALID_CNPJ_REGEX;
import static com.petech.user_register_challenge.utils.AppUtils.VALID_CPF_REGEX;
import static com.petech.user_register_challenge.utils.AppUtils.VALID_EMAIL_ADDRESS_REGEX;
import static com.petech.user_register_challenge.utils.AppUtils.VALID_PASSWORD;

import android.database.SQLException;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.data.entity.UserType;
import com.petech.user_register_challenge.ui.createuser.model.RegisterUserModel;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserAccountInformation;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;
import com.petech.user_register_challenge.utils.ErrorMessages;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterUserViewModel extends ViewModel {
    private static final String TAG= "RegisterUserViewModel:";
    private MutableLiveData<ErrorMessages> error = new MutableLiveData();
    private MutableLiveData<Boolean> userPersonalInformationSuccess = new MutableLiveData();
    private MutableLiveData<Boolean> userCreationSuccess = new MutableLiveData();
    private RegisterUserModel model;

    @Inject
    public RegisterUserViewModel(RegisterUserModel model) {
        this.model = model;
    }

    public void setUserPersonalInformation(UserPersonalInformation userInformation) {
        if (!validateUserInformation(userInformation)) {
            return;
        }
        model.setUserPersonalInformation(userInformation);
        userPersonalInformationSuccess.postValue(true);
    }

    public void createNewUser(UserAccountInformation userAccountInformation) {
        if (!validateAccountInformation(userAccountInformation)) {
            return;
        }
        model.setUserAccountInformation(userAccountInformation);

        try {
            long result = model.createUser();

            if (result == -1) {
                error.postValue(ErrorMessages.CREATION_ERROR);
                userCreationSuccess.postValue(false);
                return;
            }

            userCreationSuccess.postValue(true);
        } catch (SQLException exception) {
            exception.printStackTrace();
            error.postValue(ErrorMessages.CREATION_ERROR);
        }
    }

    private boolean validateAccountInformation(UserAccountInformation userAccountInformation) {
        return !hasNickNameOnDataBank(userAccountInformation.getNickName()) &&
                validatePassword(userAccountInformation.getPassword(), userAccountInformation.getConfirmPassword());
    }

    private boolean hasNickNameOnDataBank(String nickName) {
        List<UserEntity> users = model.findUserByNickName(nickName);
        boolean validate = users.size() > 0;

        if (validate) {
            error.postValue(ErrorMessages.NICKNAME_ALREADY_EXISTS);
        }
        return validate;
    }

    private boolean validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            error.postValue(ErrorMessages.PASSWORD_NOT_SAME);
            return false;
        }
        boolean validation = VALID_PASSWORD.matcher(password).matches();

        if (!validation) {
            error.postValue(ErrorMessages.PASSWORD_INVALID_ERROR);
        }

        return validation;
    }

    private boolean validateUserInformation(UserPersonalInformation information) {
        return validateEmail(information.getEmail()) &&
                validateName(information.getName()) &&
                validateBornDate(information.getBornDate()) &&
                validateCpfCnpj(information.getCpfCnpj(), information.getUserType());
    }

    private boolean validateCpfCnpj(String data, UserType userType) {
        boolean validation;
        if (userType == UserType.CPF) {
            validation = VALID_CPF_REGEX.matcher(data).matches();
        } else if (userType == UserType.CNPJ) {
            validation = VALID_CNPJ_REGEX.matcher(data).matches();
        } else {
            validation = false;
        }

        if (!validation) {
            error.postValue(ErrorMessages.CPF_CNPJ_ERROR);
        }
        Log.i(TAG, "validateCpfCnpj: " + validation);

        return validation;
    }

    private boolean validateName(String name) {
        boolean validation = name.length() >= 30;
        if (!validation) {
            error.postValue(ErrorMessages.NAME_VERY_SHORT);
        }
        Log.i(TAG, "validateName: " + validation);
        return validation;
    }

    private boolean validateEmail(String email) {
        boolean validation = VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches();
        if (!validation) {
            error.postValue(ErrorMessages.EMAIL_INVALID);
        }
        Log.i(TAG, "validateEmail: " + validation);
        return validation;
    }

    private boolean validateBornDate(LocalDate bornDate) {
        LocalDate eighteenYearsBefore = LocalDate.now().minusYears(18);
        boolean validation = eighteenYearsBefore.isAfter(bornDate) || eighteenYearsBefore.isEqual(bornDate);
        if (!validation) {
            error.postValue(ErrorMessages.MINOR_EIGHTEEN_USER);
        }
        Log.i(TAG, "validateBornDate: " + validation);
        return validation;
    }

    public LiveData<ErrorMessages> getError() {
        return error;
    }

    public LiveData<Boolean> getUserPersonalInformationSuccess() {
        return userPersonalInformationSuccess;
    }

    public LiveData<Boolean> getUserCreationSuccess() {
        return userCreationSuccess;
    }

}
