package com.petech.user_register_challenge.data.entity;

import android.util.Base64;

import java.io.Serializable;
import java.time.LocalDate;

public class UserEntity implements Serializable {
    public static final String ID_TAG = "_id";
    public static final String NAME_TAG = "name";
    public static final String PASSWORD_TAG = "password";
    public static final String USER_IMAGE_TAG = "user_image";
    public static final String ADDRESS_TAG = "address";
    public static final String EMAIL_TAG = "email";
    public static final String BORN_DATE_TAG = "born_date";
    public static final String GENDER_TAG = "gender";
    public static final String CPF_CNJP_TAG = "cpfcnpj";


    private int _id;
    private String name;
    private int password; //TODO: Lembre-se, isso é um hash
    private String userImage;
    private String address;
    private String email;
    private LocalDate bornDate;
    private boolean gender;
    private String cpfCnpj;

    public UserType userType() {
        if (cpfCnpj.length() > 11) {
            return UserType.CNPJ;
        } else {
            return UserType.CPF;
        }
    }

    public UserEntity(int _id, String name, int password, String userImage, String address, String email, LocalDate bornDate, boolean gender, String cpfCnpj) {
        this._id = _id;
        this.name = name;
        this.password = password;
        this.userImage = userImage;
        this.address = address;
        this.email = email;
        this.bornDate = bornDate;
        this.gender = gender;
        this.cpfCnpj = cpfCnpj;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public static class Builder {
        private int _id;
        private String name;
        private int password; //TODO: Lembre-se, isso é um hash
        private String userImage;
        private String address;
        private String email;
        private LocalDate bornDate;
        private boolean gender;
        private String cpfCnpj;

        public Builder id(int _id) {
            this._id = _id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(int password) {
            this.password = password;
            return this;
        }

        public Builder userImage(String userImage) {
            this.userImage = userImage;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder bornDate(LocalDate bornDate) {
            this.bornDate = bornDate;
            return this;
        }

        public Builder gender(boolean gender) {
            this.gender = gender;
            return this;
        }

        public Builder cpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(
                    this._id,
                    this.name,
                    this.password,
                    this.userImage,
                    this.address,
                    this.email,
                    this.bornDate,
                    this.gender,
                    this.cpfCnpj
            );
        }
    }
}
