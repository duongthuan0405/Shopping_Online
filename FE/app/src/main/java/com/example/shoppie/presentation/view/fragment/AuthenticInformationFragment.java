package com.example.shoppie.presentation.view.fragment;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppie.R;
import com.example.shoppie.databinding.AlertDialogSignupSuccessBinding;
import com.example.shoppie.databinding.FragmentAuthenticInformationBinding;
import com.example.shoppie.presentation.contract_vp.AuthenticInformation_F_Contract;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.dto.User;
import com.example.shoppie.presentation.presenter.AuthenticInformation_F_Presenter;
import com.example.shoppie.presentation.view.activity.SignInActivity;
import com.example.shoppie.presentation.view.viewmodel_data.SignUpViewModel;

import java.util.Objects;

public class AuthenticInformationFragment extends Fragment implements AuthenticInformation_F_Contract.IView{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private AuthenticInformation_F_Contract.IPresenter presenter;
    private FragmentAuthenticInformationBinding binding;
    private SignUp_A_Contract.IView parentActivity;
    SignUpViewModel signUpViewModel;

    public AuthenticInformationFragment() {
        // Required empty public constructor
    }

    public static AuthenticInformationFragment newInstance(String param1, String param2) {
        AuthenticInformationFragment fragment = new AuthenticInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        presenter = new AuthenticInformation_F_Presenter(this);
        parentActivity = (SignUp_A_Contract.IView) requireActivity();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_authentic_information, container, false);
        binding = FragmentAuthenticInformationBinding.inflate(inflater, container, false);

        initViewModel();
        binding.txVwBack.setOnClickListener(v -> onClick_txVwBack());
        binding.btnSignUp.setOnClickListener(v -> onClick_btnSignUp(v));
        binding.edTxYourPassword.setOnTouchListener((v, event) -> {setNormalBackground(v); return false;});

        binding.edTxYourEmail.setOnTouchListener((v, event) -> {setNormalBackground(v); return false;});

        return binding.getRoot();
    }

    private void initViewModel() {
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding.setVm(signUpViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

    }

    private void onClick_btnSignUp(View v) {
        String email = binding.edTxYourEmail.getText().toString();
        String password = binding.edTxYourPassword.getText().toString();
        presenter.onClick_btnSignUp(email, password);
    }

    private void onClick_txVwBack() {
        presenter.onClick_txVwBack();
    }

    @Override
    public void handleAsSystemBackPress() {
        parentActivity.handleAsBackPressSystem();
    }

    @Override
    public void showError_Email() {
        changeErrorBackground(binding.edTxYourEmail);
    }

    @Override
    public void showError_Password() {
        changeErrorBackground(binding.edTxYourPassword);
    }

    @Override
    public User getUser() {
        String fullName = signUpViewModel.getFullName().getValue();
        String phoneNumber = signUpViewModel.getPhoneNumber().getValue();
        String birthday = signUpViewModel.getBirthday().getValue();
        User user = new User(fullName, phoneNumber, birthday);
        return user;
    }

    @Override
    public void showError(String message) {
        binding.txVwError.setText(message);
        changeErrorBackground(binding.edTxYourPassword);
        changeErrorBackground(binding.edTxYourEmail);
    }

    @Override
    public void onSuccess() {
        setNormalBackground(binding.edTxYourEmail);
        setNormalBackground(binding.edTxYourPassword);
        binding.txVwError.setText("");
        showAlertDialogForSuccess();
    }

    private void showAlertDialogForSuccess() {
        AlertDialogSignupSuccessBinding alertDialogBinding = AlertDialogSignupSuccessBinding.inflate(getLayoutInflater());
        AlertDialog alertDialog = new AlertDialog.Builder(requireActivity())
                .setView(alertDialogBinding.getRoot()).create();

        Objects.requireNonNull(alertDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        alertDialog.show();

        alertDialogBinding.btnOK.setOnClickListener(v -> onClick_btnOk(v));
    }

    private void onClick_btnOk(View v) {
        Intent i = new Intent(requireActivity(), SignInActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void changeErrorBackground(View view)
    {
        Drawable errorBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.input_box_error);
        view.setBackground(errorBackground);
    }

    private void setNormalBackground(View view) {
        Drawable normalBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.input_box);
        view.setBackground(normalBackground);
    }
}