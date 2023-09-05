package com.petech.user_register_challenge.ui.createuser.viewmodel;

import static com.petech.user_register_challenge.utils.AppUtils.VALID_EMAIL_ADDRESS_REGEX;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.petech.user_register_challenge.ui.createuser.model.RegisterUserModel;
import com.petech.user_register_challenge.ui.createuser.model.beans.UserPersonalInformation;
import com.petech.user_register_challenge.utils.ErrorMessages;

import java.time.LocalDate;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterUserViewModel extends ViewModel {
    private MutableLiveData<ErrorMessages> error = new MutableLiveData();
    private MutableLiveData<Boolean> userPersonalInformationSuccess = new MutableLiveData();
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

    private boolean validateUserInformation(UserPersonalInformation information) {
        return validateEmail(information.getEmail()) || validateName(information.getName()) || validateBornDate(information.getBornDate());
    }

    private boolean validateName(String name) {
        boolean validation = name.length() >= 30;
        if (!validation) {
            error.postValue(ErrorMessages.NAME_VERY_SHORT);
        }
        return validation;
    }

    private boolean validateEmail(String email) {
        boolean validation = VALID_EMAIL_ADDRESS_REGEX.matcher(email).matches();
        if (!validation) {
            error.postValue(ErrorMessages.EMAIL_INVALID);
        }
        return validation;
    }

    private boolean validateBornDate(LocalDate bornDate) {
        LocalDate eighteenYearsBefore = LocalDate.now().minusYears(18);
        boolean validation = eighteenYearsBefore.isAfter(bornDate) || eighteenYearsBefore.isEqual(bornDate);
        if (!validation) {
            error.postValue(ErrorMessages.MINOR_EIGHTEEN_USER);
        }

        return validation;
    }

    public LiveData<ErrorMessages> getError() {
        return error;
    }

    public LiveData<Boolean> getUserPersonalInformationSuccess() {
        return userPersonalInformationSuccess;
    }


}
