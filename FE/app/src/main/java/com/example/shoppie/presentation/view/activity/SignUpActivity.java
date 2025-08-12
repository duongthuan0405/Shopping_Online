package com.example.shoppie.presentation.view.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivitySignUpBinding;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.SignUp_A_Presenter;
import com.example.shoppie.presentation.view.fragment.AuthenticInformationFragment;
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
            Fragment firstFragment = new PersonalInformationFragment();
            setFragment(firstFragment, false);

        }
    }

    @Override
    public void changeTo_F_AuthenticInfo() {
        setFragment(new AuthenticInformationFragment(), true);
    }

    @Override
    public void handleAsBackPressSystem() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    private void setFragment(Fragment f, boolean isCanBack)
    {
        FragmentTransaction transaction = getSupportFragmentManager()
                                            .beginTransaction()
                                            .setCustomAnimations(
                                                    R.anim.slide_in_from_right,  // enter
                                                    R.anim.slide_out_to_left,  // exit
                                                    R.anim.slide_in_from_left,   // popEnter
                                                    R.anim.slide_out_to_right  // popExit
                                            )
                                            .replace(binding.mainFrame.getId(), f);

        if(isCanBack)
        {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
}