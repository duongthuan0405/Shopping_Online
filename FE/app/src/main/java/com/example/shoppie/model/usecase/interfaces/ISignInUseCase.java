package com.example.shoppie.model.usecase.interfaces;

import com.example.shoppie.model.dto.MAuthentication;

public interface ISignInUseCase {
    void execute(MAuthentication authentication, Callback callback);

    interface Callback
    {

        void onHaveError(String message);

        void onEmailNotVerified();

        void onSuccess(MAuthentication mAuthentication);
    }
}
