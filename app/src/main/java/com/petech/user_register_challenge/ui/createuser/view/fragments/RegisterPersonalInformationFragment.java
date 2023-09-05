package com.petech.user_register_challenge.ui.createuser.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.petech.user_register_challenge.databinding.FragmentRegisterPersonalInformationBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterPersonalInformationFragment extends Fragment {
    private FragmentRegisterPersonalInformationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterPersonalInformationBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
