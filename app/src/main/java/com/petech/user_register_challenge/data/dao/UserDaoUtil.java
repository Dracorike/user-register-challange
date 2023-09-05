package com.petech.user_register_challenge.data.dao;

import android.content.ContentValues;

import com.petech.user_register_challenge.data.entity.UserEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDaoUtil {
    public static ContentValues getUserValues(UserEntity user) {
        ContentValues values = new ContentValues();
        values.put(UserEntity.NAME_TAG, user.getName());
        values.put(UserEntity.NICK_TAG, user.getNickName());
        values.put(UserEntity.PASSWORD_TAG, user.getPassword());
        values.put(UserEntity.USER_IMAGE_TAG, user.getUserImage());
        values.put(UserEntity.ADDRESS_TAG, user.getAddress());
        values.put(UserEntity.EMAIL_TAG, user.getEmail());
        values.put(UserEntity.BORN_DATE_TAG, UserDaoUtil.convertDateToStringIso8601(user.getBornDate()));
        values.put(UserEntity.GENDER_TAG, user.isGender());
        values.put(UserEntity.CPF_CNJP_TAG, user.getCpfCnpj());
        return values;
    }

    public static String convertDateToStringIso8601(LocalDate date) {
        return date.toString().concat("T00:00:01.000");
    }

    public static LocalDate convertStringIso8601ToLocalDate(String iso8601) {
        return LocalDateTime.parse(iso8601).toLocalDate();
    }
}
