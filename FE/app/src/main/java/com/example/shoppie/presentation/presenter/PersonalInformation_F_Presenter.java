package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.PersonalInformation_F_Contract;

public class PersonalInformation_F_Presenter implements PersonalInformation_F_Contract.IPresenter
{
    PersonalInformation_F_Contract.IView view;
    public PersonalInformation_F_Presenter(PersonalInformation_F_Contract.IView view)
    {
        this.view = view;
    }
}
