package com.example.shoppie.presentation.contract_vp;

public interface PersonalInformation_F_Contract
{
    interface IView
    {

        void changeNextFragment();

        void handleAsSystemBackPress();

        void showError_FullnName();

        void showError_PhoneNumber();

        void showError_Birthday();
    }

    interface IPresenter
    {

        void onClick_btnNext(String fullName, String phoneNumber, String birthday);
        void onClick_btnBack();
    }
}
