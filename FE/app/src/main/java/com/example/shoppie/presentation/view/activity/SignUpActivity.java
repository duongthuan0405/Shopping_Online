package com.example.shoppie.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivitySignUpBinding;
import com.example.shoppie.databinding.AlertDialogSignupSuccessBinding;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.example.shoppie.viewmodel.SignUpViewModel;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity{

    private ActivitySignUpBinding binding;
    private SignUpViewModel signUpViewModel;
    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.setVm(signUpViewModel);
        binding.setLifecycleOwner(this);
        re_HandleBackPress();

        signUpViewModel.getChangeNextFragmentEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean i = booleanOnceEvent.getContentIfNotHandle();
                if(i == null || !i)
                {
                    return;
                }
                Fragment currentFrag = signUpViewModel.getCurrentFragment();
                setFragment(currentFrag, signUpViewModel.getPosition());
            }
        });

        signUpViewModel.getAsBackSystemEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                if(booleanOnceEvent.getContentIfNotHandle() == null)
                {
                    return;
                }
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        signUpViewModel.getShowSignUpSuccessEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                showAlertDialogForSuccess();
            }
        });

        signUpViewModel.getDismissDialogEvent().observe(this, new Observer<OnceEvent<Boolean>>() {
            @Override
            public void onChanged(OnceEvent<Boolean> booleanOnceEvent) {
                Boolean content = booleanOnceEvent.getContentIfNotHandle();
                if(content == null)
                {
                    return;
                }
                alertDialog.dismiss();

                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

    private void showAlertDialogForSuccess() {
        AlertDialogSignupSuccessBinding alertDialogBinding = AlertDialogSignupSuccessBinding.inflate(getLayoutInflater());
        alertDialogBinding.setSignUpVM(signUpViewModel);
        alertDialogBinding.setLifecycleOwner(this);

        alertDialog = new AlertDialog.Builder(this)
                .setView(alertDialogBinding.getRoot()).create();

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();
    }

    private void re_HandleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                signUpViewModel.backToPreviousPosition();
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
                setEnabled(true);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    private void setFragment(Fragment f, int currentPositionFragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction = transaction.setCustomAnimations(
                        R.anim.slide_in_from_right,  // enter
                        R.anim.slide_out_to_left,  // exit
                        R.anim.slide_in_from_left,   // popEnter
                        R.anim.slide_out_to_right  // popExit
                );

        transaction = transaction.replace(binding.mainFrame.getId(), f);

        if(currentPositionFragment != 0) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

}