package com.petech.user_register_challenge.ui.mainscreen.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.petech.user_register_challenge.R;
import com.petech.user_register_challenge.ui.createuser.view.RegisterUserActivity;

public class CpfOrCnpjFragmentDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.cpf_or_cnpj_alert_dialog_body)
                .setPositiveButton(R.string.yes_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), RegisterUserActivity.class);
                        intent.putExtra(RegisterUserActivity.IS_CPF, true);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.no_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), RegisterUserActivity.class);
                        intent.putExtra(RegisterUserActivity.IS_CPF, false);
                        startActivity(intent);
                    }
                });
        return builder.create();
    }
}
