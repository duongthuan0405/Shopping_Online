package com.example.shoppie.model.usecase.interfaces;

import com.example.shoppie.model.dto.MAuthentication;
import com.google.firebase.auth.FirebaseUser;

public interface IGetRealTimeUser {
    public void execute(Callback callback);

    interface Callback
    {

        void onHaveTokenUser(MAuthentication currentUserAfterRefresh);

        void onNotHaveAnyTokenUser();
    }
}
