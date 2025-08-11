package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;

public class SignUp_A_Presenter implements SignUp_A_Contract.IPresenter
{
    SignUp_A_Contract.IView view;
    public SignUp_A_Presenter(SignUp_A_Contract.IView view)
    {
        this.view = view;
    }
}
