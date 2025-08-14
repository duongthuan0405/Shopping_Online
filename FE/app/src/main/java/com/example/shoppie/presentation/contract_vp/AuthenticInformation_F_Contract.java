package com.example.shoppie.presentation.contract_vp;

public interface AuthenticInformation_F_Contract
{
    interface IView
    {

        void handleAsSystemBackPress();

        void showError_Email();

        void showError_Password();
    }

    interface IPresenter
    {

        void onClick_txVwBack();

        void onClick_btnSignUp(String email, String password);
    }
}
