package com.petech.user_register_challenge.ui.mainscreen.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.petech.user_register_challenge.databinding.FragmentMainUserListBinding;

public class MainUserListFragment extends Fragment {
    private FragmentMainUserListBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainUserListBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
