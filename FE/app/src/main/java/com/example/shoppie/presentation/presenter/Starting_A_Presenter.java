package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.Starting_A_Contract;

public class Starting_A_Presenter implements Starting_A_Contract.IPresenter
{
    Starting_A_Contract.IView view;
    public Starting_A_Presenter(Starting_A_Contract.IView view)
    {
        this.view = view;
    }

    @Override
    public void onCick_btnGetStart() {
        view.navigateToLoginActivity();
    }
}
