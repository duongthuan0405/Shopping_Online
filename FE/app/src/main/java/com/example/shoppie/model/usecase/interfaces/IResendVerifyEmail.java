package com.example.shoppie.model.usecase.interfaces;

public interface IResendVerifyEmail {
    public void execute (Callback callback);
    
    interface Callback
    {

        void onSuccess();

        void onHaveError(String message);
    }
}
