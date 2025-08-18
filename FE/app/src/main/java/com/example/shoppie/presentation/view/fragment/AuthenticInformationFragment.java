package com.example.shoppie.presentation.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoppie.R;
import com.example.shoppie.databinding.AlertDialogSignupSuccessBinding;
import com.example.shoppie.databinding.FragmentAuthenticInformationBinding;
import com.example.shoppie.presentation.view.activity.SignInActivity;
import com.example.shoppie.viewmodel.SignUpViewModel;

import java.util.Objects;

public class AuthenticInformationFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private FragmentAuthenticInformationBinding binding;
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


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_authentic_information, container, false);
        binding = FragmentAuthenticInformationBinding.inflate(inflater, container, false);

        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding.setSignUpVM(signUpViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        return binding.getRoot();
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