package com.example.shoppie.presentation.view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivitySignInBinding;
import com.example.shoppie.presentation.contract_vp.SignInActivityContract;
import com.example.shoppie.presentation.presenter.SignInActivityPresenter;

public class SignInActivity extends AppCompatActivity implements SignInActivityContract.IView {

    ActivitySignInBinding binding;
    SignInActivityContract.IPresenter presenter = new SignInActivityPresenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}