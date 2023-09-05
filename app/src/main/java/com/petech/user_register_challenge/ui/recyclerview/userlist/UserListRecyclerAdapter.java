package com.petech.user_register_challenge.ui.recyclerview.userlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.ui.recyclerview.userlist.listener.DeleteOnClickListener;

import java.util.List;

public class UserListRecyclerAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private DeleteOnClickListener deleteOnClickListener;
    private List<UserEntity> usersList;

    public UserListRecyclerAdapter(List<UserEntity> usersList, DeleteOnClickListener deleteOnClickListener) {
        this.deleteOnClickListener = deleteOnClickListener;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_recycler_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bindView(usersList.get(position), deleteOnClickListener);
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
