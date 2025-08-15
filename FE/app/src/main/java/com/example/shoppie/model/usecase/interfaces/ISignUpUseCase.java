package com.example.shoppie.model.usecase.interfaces;


import com.example.shoppie.model.dto.MAuthentication;

public interface ISignUpUseCase {
    void execute(MAuthentication authentication, Callback callback);
    interface Callback
    {

        void onSuccess(MAuthentication mAuthentication);

        void onFailure(String message);
    }
}
