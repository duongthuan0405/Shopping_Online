package com.example.shoppie.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppie.databinding.ActivitySignInBinding;
import com.example.shoppie.presentation.contract_vp.SignIn_A_Contract;
import com.example.shoppie.presentation.presenter.SignIn_A_Presenter;

public class SignInActivity extends AppCompatActivity implements SignIn_A_Contract.IView {

    ActivitySignInBinding binding;
    SignIn_A_Contract.IPresenter presenter = new SignIn_A_Presenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.txVwSignUp.setOnClickListener(v -> onClick_txVwSignUp(v));
    }

    private void onClick_txVwSignUp(View v) {
        presenter.onClick_txVwSignUp();
    }

    @Override
    public void navigateToSignUpActivity() {
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);
    }
}