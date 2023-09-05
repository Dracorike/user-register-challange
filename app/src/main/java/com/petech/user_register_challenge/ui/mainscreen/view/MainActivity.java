package com.petech.user_register_challenge.ui.mainscreen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.dao.UserDaoImpl;
import com.petech.user_register_challenge.data.dao.UserDaoUtil;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.databinding.ActivityMainBinding;
import com.petech.user_register_challenge.ui.createuser.view.RegisterUserActivity;
import com.petech.user_register_challenge.ui.mainscreen.viewmodel.MainViewModel;

import java.time.LocalDate;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.hasUserRegistered();
        setupComponents();
        setObservables();
    }

    private void setupComponents() {
        binding.floatingButtonAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CpfOrCnpjFragmentDialog dialog = new CpfOrCnpjFragmentDialog();
                dialog.show(getSupportFragmentManager(), TAG);
            }
        });
    }

    private void setObservables() {
        viewModel.getHasUserRegistered().observe(this, hasUsers -> {
            if (hasUsers) {
                selectFragment(new MainUserListFragment());
            } else {
                selectFragment(new MainEmptyListFragment());
            }
        });
    }

    private void selectFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_fragment_container, fragment);
        transaction.commit();
    }


}