package com.petech.user_register_challenge.ui.createuser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.databinding.ActivityRegisterUserBinding;
import com.petech.user_register_challenge.ui.createuser.viewmodel.RegisterUserViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterUserActivity extends AppCompatActivity {
    public static final String IS_CPF = "is-cpf";
    private ActivityRegisterUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initHostNavFragment();
    }

    private void initHostNavFragment() {
        NavHostFragment navFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_navigation_host);

        NavController navController = navFragment.getNavController();
    }
}