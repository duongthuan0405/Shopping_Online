package com.example.shoppie.presentation.presenter;

import com.example.shoppie.presentation.contract_vp.StartingActivityContract;

public class StartingActivityPresenter implements StartingActivityContract.IPresenter
{
    StartingActivityContract.IView view;
    public StartingActivityPresenter(StartingActivityContract.IView view)
    {
        this.view = view;
    }
}
