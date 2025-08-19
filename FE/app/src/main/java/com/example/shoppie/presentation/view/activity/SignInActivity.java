package com.example.shoppie.presentation.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivitySignInBinding;
import com.example.shoppie.databinding.AlertDialogAskToVerifyEmailBinding;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.example.shoppie.viewmodel.SignInViewModel;

import java.util.Objects;

public class SignInActivity extends AppCompatActivity  {

    ActivitySignInBinding binding;
    SignInViewModel signInVM;
    Dialog dialogAskToVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signInVM = new ViewModelProvider(this).get(SignInViewModel.class);
        binding.setSignInVM(signInVM);
        binding.setLifecycleOwner(this);

        signInVM.getNavigateToSignUpActEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if (content == null || !content) {
                    return;
                }

                navigateToSignUpActivity();
            }
        });

        signInVM.getIsValidEmail().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setBackgroundInputBox(binding.edTxInputEmail, aBoolean);
            }
        });

        signInVM.getIsValidPassword().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                setBackgroundInputBox(binding.edTxInputPassword, aBoolean);
            }
        });

        signInVM.getShowAlertDialogAskVerifyEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null || !content)
                {
                    return;
                }

                showAlertDialogAskVerify();
            }
        });

        signInVM.getOnClickCancelAlertDialogAskVerifyEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null || !content)
                {
                    return;
                }

                dialogAskToVerify.cancel();
            }
        });

        signInVM.getOnDismissDialog().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null || !content)
                {
                    return;
                }

                dialogAskToVerify.dismiss();
            }
        });

        signInVM.getShowToastEvent().observe(this, new Observer<OnceEvent<String>>() {
            @Override
            public void onChanged(OnceEvent<String> stringOnceEvent) {
                String content = stringOnceEvent.getContentIfNotHandle();
                if(content == null || content.isEmpty())
                {
                    return;
                }
                Toast.makeText(SignInActivity.this, content, Toast.LENGTH_LONG).show();
            }
        });

        signInVM.getNavigateToMainActivityEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null || !content)
                {
                    return;
                }

                navigateToMainActivity();
            }
        });

    }

    private void navigateToMainActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void showAlertDialogAskVerify() {
        AlertDialogAskToVerifyEmailBinding alertDialogAskToVerifyEmailBinding = AlertDialogAskToVerifyEmailBinding.inflate(getLayoutInflater());
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setView(alertDialogAskToVerifyEmailBinding.getRoot());

        alertDialogAskToVerifyEmailBinding.setSignInVM(signInVM);
        alertDialogAskToVerifyEmailBinding.setLifecycleOwner(this);

        dialogAskToVerify = builder.create();
        Objects.requireNonNull(dialogAskToVerify.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        dialogAskToVerify.show();
    }

    private void navigateToSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

    private void setBackgroundInputBox(View view, Boolean aBoolean) {
        Drawable background;
        if(aBoolean)
        {
            background = ContextCompat.getDrawable(this, R.drawable.input_box);
        }
        else
        {
            background = ContextCompat.getDrawable(this, R.drawable.input_box_error);
        }

        view.setBackground(background);
    }

}