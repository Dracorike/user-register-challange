package com.petech.user_register_challenge.ui.recyclerview.userlist;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.petech.user_register_challenge.data.entity.UserEntity;
import com.petech.user_register_challenge.databinding.UserRecyclerItemBinding;
import com.petech.user_register_challenge.ui.recyclerview.userlist.listener.DeleteOnClickListener;
import com.petech.user_register_challenge.ui.recyclerview.userlist.listener.MoreDetailsOnClickListener;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private UserRecyclerItemBinding binding;

    public UserViewHolder(View itemView) {
        super(itemView);
        binding = UserRecyclerItemBinding.bind(itemView);
    }

    public void bindView(UserEntity user, DeleteOnClickListener clickDelete, MoreDetailsOnClickListener detailsClick) {
        binding.textUserRealName.setText(user.getName());
        binding.textUserEmailAddress.setText(user.getEmail());
        binding.textUserNickName.setText(user.getNickName());

        binding.buttonDeleteUser.setOnClickListener(view -> clickDelete.onDeleteClickListener(user.get_id()));
        binding.cardItemLayout.setOnClickListener(view -> detailsClick.onClickMoreDetailsListener(user.get_id()));

    }
}
