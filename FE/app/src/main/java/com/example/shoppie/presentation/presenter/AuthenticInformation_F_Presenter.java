package com.example.shoppie.presentation.presenter;

import android.util.Log;
import android.util.Patterns;

import com.example.shoppie.presentation.contract_vp.AuthenticInformation_F_Contract;

import java.util.regex.Pattern;

public class AuthenticInformation_F_Presenter implements AuthenticInformation_F_Contract.IPresenter
{
    AuthenticInformation_F_Contract.IView view;
    public AuthenticInformation_F_Presenter(AuthenticInformation_F_Contract.IView view)
    {
        this.view = view;
    }

    @Override
    public void onClick_txVwBack() {
        view.handleAsSystemBackPress();
    }

    @Override
    public void onClick_btnSignUp(String email, String password) {
        boolean isValid = true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            view.showError_Email();
            isValid = false;
        }

        if (password.length() < 6)
        {
            view.showError_Password();
            isValid = false;
        }

        Log.d("THUAN", password);
    }
}
