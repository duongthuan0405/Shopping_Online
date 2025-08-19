package com.example.shoppie.model.usecase.interfaces;

import com.google.firebase.auth.FirebaseUser;

public interface IGetRealTimeUser {
    public void execute(Callback callback);

    interface Callback
    {

        void onHaveTokenUser(FirebaseUser currentUserAfterRefresh);

        void onNotHaveAnyTokenUser();
    }
}
