package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.SignInActivityContract;

public class SignInActivityPresenter implements SignInActivityContract.IPresenter
{
    SignInActivityContract.IView view;
    public SignInActivityPresenter(SignInActivityContract.IView view)
    {
        this.view = view;
    }
}
