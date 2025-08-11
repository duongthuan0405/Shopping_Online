package com.example.shoppie.presentation.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivityStartingBinding;
import com.example.shoppie.presentation.contract_vp.StartingActivityContract;

public class StartingActivity extends AppCompatActivity implements StartingActivityContract.IView {

    ActivityStartingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityStartingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}