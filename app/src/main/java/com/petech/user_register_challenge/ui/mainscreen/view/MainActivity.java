package com.petech.user_register_challenge.ui.mainscreen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.dao.UserDaoImpl;
import com.petech.user_register_challenge.data.dao.UserDaoUtil;
import com.petech.user_register_challenge.data.entity.UserEntity;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}