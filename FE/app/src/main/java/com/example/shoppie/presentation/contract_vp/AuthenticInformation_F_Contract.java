package com.example.shoppie.presentation.contract_vp;

import android.content.Context;

import com.example.shoppie.presentation.dto.User;

public interface AuthenticInformation_F_Contract
{
    interface IView
    {

        void handleAsSystemBackPress();

        void showError_Email();

        void showError_Password();

        User getUser();

        void showError(String message);

        void onSuccess();
    }

    interface IPresenter
    {

        void onClick_txVwBack();

        void onClick_btnSignUp(String email, String password);
    }
}
