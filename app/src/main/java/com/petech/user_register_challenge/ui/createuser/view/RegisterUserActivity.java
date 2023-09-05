package com.petech.user_register_challenge.ui.createuser.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.databinding.ActivityRegisterUserBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterUserActivity extends AppCompatActivity {
    private ActivityRegisterUserBinding binding;
    private NavController navController;

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

        navController = navFragment.getNavController();
    }
}