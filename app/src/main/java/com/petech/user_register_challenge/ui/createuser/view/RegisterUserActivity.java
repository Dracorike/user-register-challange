package com.petech.user_register_challenge.ui.createuser.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.petech.user_register_challenge.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }
}