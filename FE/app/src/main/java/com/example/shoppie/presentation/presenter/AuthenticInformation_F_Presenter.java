package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.AuthenticInformation_F_Contract;

public class AuthenticInformation_F_Presenter implements AuthenticInformation_F_Contract.IPresenter
{
    AuthenticInformation_F_Contract.IView view;
    public AuthenticInformation_F_Presenter(AuthenticInformation_F_Contract.IView view)
    {
        this.view = view;
    }
}
