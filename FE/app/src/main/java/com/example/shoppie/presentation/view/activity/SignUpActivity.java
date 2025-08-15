package com.example.shoppie.presentation.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoppie.R;
import com.example.shoppie.databinding.ActivitySignUpBinding;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.SignUp_A_Presenter;
import com.example.shoppie.presentation.view.fragment.AuthenticInformationFragment;
import com.example.shoppie.presentation.view.fragment.PersonalInformationFragment;
import com.example.shoppie.presentation.view.viewmodel_data.SignUpViewModel;

import java.util.ArrayList;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements SignUp_A_Contract.IView{

    ActivitySignUpBinding binding;
    SignUp_A_Contract.IPresenter presenter = new SignUp_A_Presenter(this);
    SignUpViewModel signUpViewModel;
    ArrayList<Fragment> fragments = new ArrayList<>();
    int currentPositionFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViewModel();
        initFragments();
        attachFirstFragment(savedInstanceState);
        re_HandleBackPress();

    }

    private void re_HandleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                decreaseCurrentFragmentPosition();
                signUpViewModel.setPosition(currentPositionFragment + 1);
                signUpViewModel.setShowProcessBar(false);

                // Default back action
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
                setEnabled(true);
            }
        };

        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void attachFirstFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Fragment firstFragment = fragments.get(currentPositionFragment);
            setFragment(firstFragment, currentPositionFragment);
        }

    }

    private void initViewModel() {
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        binding.setVm(signUpViewModel);
        binding.setLifecycleOwner(this);
    }

    private void initFragments() {
        fragments.add(new PersonalInformationFragment());
        fragments.add(new AuthenticInformationFragment());
    }

    @Override
    public void changeNextFragment() {
        increaseCurrentFragmentPosition();
        setFragment(fragments.get(currentPositionFragment), currentPositionFragment);
        signUpViewModel.setPosition(currentPositionFragment + 1);
    }

    private void increaseCurrentFragmentPosition() {
        if(currentPositionFragment == fragments.size() - 1)
            return;
        currentPositionFragment++;
    }

    @Override
    public void handleAsBackPressSystem() {
        getOnBackPressedDispatcher().onBackPressed();
    }


    private void decreaseCurrentFragmentPosition() {
        if(currentPositionFragment == 0)
            return;
        currentPositionFragment--;
    }

    private void setFragment(Fragment f, int currentPositionFragment)
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

        if(currentPositionFragment != 0)
        {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

}