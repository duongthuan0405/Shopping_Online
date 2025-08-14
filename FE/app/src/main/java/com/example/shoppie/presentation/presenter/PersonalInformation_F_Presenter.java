package com.example.shoppie.presentation.presenter;

import android.view.View;

import com.example.shoppie.presentation.contract_vp.PersonalInformation_F_Contract;

import java.util.List;

public class PersonalInformation_F_Presenter implements PersonalInformation_F_Contract.IPresenter
{
    PersonalInformation_F_Contract.IView view;
    public PersonalInformation_F_Presenter(PersonalInformation_F_Contract.IView view)
    {
        this.view = view;
    }

    @Override
    public void onClick_btnNext(String fullName, String phoneNumber, String birthday) {
        boolean isValid = true;
        if(fullName.isEmpty())
        {
            view.showError_FullnName();
            isValid = false;
        }
        if(phoneNumber.isEmpty())
        {
            view.showError_PhoneNumber();
            isValid = false;
        }
        if(birthday.isEmpty())
        {
            view.showError_Birthday();
            isValid = false;
        }
        if(isValid)
        {
            view.changeNextFragment();
        }
    }

    private boolean getInvalidPersonalInformation(String fullName, String phoneNumber, String birthday) {
        return !(fullName.isEmpty() || phoneNumber.isEmpty() || birthday.isEmpty());
    }

    @Override
    public void onClick_btnBack() {
        view.handleAsSystemBackPress();
    }
}
