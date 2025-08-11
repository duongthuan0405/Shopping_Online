package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.SignIn_A_Contract;

public class SignIn_A_Presenter implements SignIn_A_Contract.IPresenter
{
    SignIn_A_Contract.IView view;
    public SignIn_A_Presenter(SignIn_A_Contract.IView view)
    {
        this.view = view;
    }

    @Override
    public void onClick_txVwSignUp() {
        view.navigateToSignUpActivity();
    }
}
