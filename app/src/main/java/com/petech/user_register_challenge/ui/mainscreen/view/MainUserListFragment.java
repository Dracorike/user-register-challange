package com.petech.user_register_challenge.ui.mainscreen.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.databinding.FragmentMainUserListBinding;
import com.petech.user_register_challenge.ui.mainscreen.viewmodel.MainViewModel;
import com.petech.user_register_challenge.ui.recyclerview.userlist.UserListRecyclerAdapter;
import com.petech.user_register_challenge.ui.recyclerview.userlist.listener.DeleteOnClickListener;
import com.petech.user_register_challenge.ui.recyclerview.userlist.listener.MoreDetailsOnClickListener;
import com.petech.user_register_challenge.ui.updatescreen.view.UpdateActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainUserListFragment extends Fragment {
    private final List<UserEntity> usersList = new ArrayList();
    private final List<UserEntity> userListFiltered = new ArrayList();
    private FragmentMainUserListBinding binding;
    private UserListRecyclerAdapter adapter;
    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainUserListBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupComponents();
        setupObservables();
        viewModel.getAllUsers();

        return binding.getRoot();
    }

    private void setupComponents() {
        setupRecyclerView();
        binding.editTextQueryUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                userListFiltered.clear();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<UserEntity> filtering = usersList.stream()
                        .filter(user -> user.getName().contains(editable) || user.getNickName().contains(editable))
                        .collect(Collectors.toList());

                userListFiltered.clear();
                userListFiltered.addAll(filtering);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new UserListRecyclerAdapter(userListFiltered, deleteUserClick(), moreDetailsClick());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        binding.recyclerViewUserList.setLayoutManager(layoutManager);
        binding.recyclerViewUserList.setAdapter(adapter);
    }

    private void setupObservables() {
        viewModel.getUsersList().observe(getViewLifecycleOwner(), list -> {
            usersList.clear();
            usersList.addAll(list);
            userListFiltered.addAll(usersList);

            adapter.notifyDataSetChanged();
        });

        viewModel.getUserDeleted().observe(getViewLifecycleOwner(), isDeleted -> {
            if (!isDeleted) {
                showToast(getString(R.string.item_not_deleted_error_body));
            }

            adapter.notifyDataSetChanged();
        });
    }

    private DeleteOnClickListener deleteUserClick() {
        return new DeleteOnClickListener() {
            @Override
            public void onDeleteClickListener(int userId) {
                viewModel.deleteUser(userId);
                requireActivity().recreate();
            }
        };
    }

    private MoreDetailsOnClickListener moreDetailsClick() {
        return new MoreDetailsOnClickListener() {
            @Override
            public void onClickMoreDetailsListener(int userId) {
                Intent intent = new Intent(requireActivity(), UpdateActivity.class);
                intent.putExtra(UpdateActivity.EXTRA_ID, userId);
                startActivity(intent);
            }
        };
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
