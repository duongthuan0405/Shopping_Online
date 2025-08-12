package com.example.shoppie.presentation.contract_vp;

public interface PersonalInformation_F_Contract
{
    interface IView
    {

        void changeNextFragment();

        void handleAsSystemBackPress();
    }

    interface IPresenter
    {

        void onClick_btnNext();

        void onClick_btnBack();
    }
}
