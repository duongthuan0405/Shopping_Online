package com.example.shoppie.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppie.databinding.ActivityStartingBinding;
import com.example.shoppie.presentation.contract_vp.Starting_A_Contract;
import com.example.shoppie.presentation.presenter.Starting_A_Presenter;

public class StartingActivity extends AppCompatActivity implements Starting_A_Contract.IView {

    ActivityStartingBinding binding;
    Starting_A_Contract.IPresenter presenter = new Starting_A_Presenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStartingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGetStart.setOnClickListener(v -> onClick_btnGetStart(v));
    }

    private void onClick_btnGetStart(View v) {
        presenter.onCick_btnGetStart();
    }

    @Override
    public void navigateToLoginActivity() {
        Intent i = new Intent(StartingActivity.this, SignInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}