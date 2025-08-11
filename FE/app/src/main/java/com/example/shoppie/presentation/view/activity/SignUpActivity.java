package com.example.shoppie.presentation.view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.shoppie.databinding.ActivitySignUpBinding;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.SignUp_A_Presenter;
import com.example.shoppie.presentation.view.fragment.PersonalInformationFragment;

public class SignUpActivity extends AppCompatActivity implements SignUp_A_Contract.IView{

    ActivitySignUpBinding binding;
    SignUp_A_Contract.IPresenter presenter = new SignUp_A_Presenter(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            // Tạo fragment mới, ví dụ FirstFragment
            Fragment firstFragment = new PersonalInformationFragment();

            // Thực hiện transaction gắn fragment vào FrameLayout fragment_container
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(binding.mainFrame.getId(), firstFragment)
                    .commit();
        }
    }
}