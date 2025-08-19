package com.example.shoppie.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.databinding.ActivityStartingBinding;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.example.shoppie.viewmodel.StartingViewModel;

public class StartingActivity extends AppCompatActivity{

    ActivityStartingBinding binding;
    StartingViewModel startingViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStartingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startingViewModel = new ViewModelProvider(this).get(StartingViewModel.class);
        binding.setStartingVM(startingViewModel);
        binding.setLifecycleOwner(this);

        startingViewModel.getNavigateToSignInActivityEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                if(booleanOnceEvent == null)
                {
                    return;
                }
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null || !content)
                {
                    return;
                }

                navigateToLoginActivity();
            }
        });

        startingViewModel.getNavigateToMainActivityEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
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
        Intent i = new Intent(StartingActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void navigateToLoginActivity() {
        Intent i = new Intent(StartingActivity.this, SignInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}