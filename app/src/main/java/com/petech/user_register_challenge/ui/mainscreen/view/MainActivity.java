package com.petech.user_register_challenge.ui.mainscreen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.dao.UserDao;
import com.petech.user_register_challenge.data.dao.UserDaoImpl;
import com.petech.user_register_challenge.data.dao.UserDaoUtil;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.databinding.ActivityMainBinding;
import com.petech.user_register_challenge.ui.mainscreen.viewmodel.MainViewModel;

import java.time.LocalDate;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        selectFragment();

        viewModel.createNewUser();
    }

    private void selectFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.frame_fragment_container, new MainEmptyListFragment());
        transaction.commit();
    }


}