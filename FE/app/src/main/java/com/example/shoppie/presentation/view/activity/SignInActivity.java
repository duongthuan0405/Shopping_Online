package com.example.shoppie.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.shoppie.databinding.ActivitySignInBinding;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.example.shoppie.viewmodel.SignInViewModel;

public class SignInActivity extends AppCompatActivity  {

    ActivitySignInBinding binding;
    SignInViewModel signInVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signInVM = new SignInViewModel();
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
    }

    private void navigateToSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }

}