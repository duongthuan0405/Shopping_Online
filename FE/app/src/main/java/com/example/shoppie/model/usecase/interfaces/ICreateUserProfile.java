package com.example.shoppie.model.usecase.interfaces;

import com.example.shoppie.model.dto.MUser;

public interface ICreateUserProfile {
    void execute(MUser mUser, Callback callback);

    interface Callback
    {

        void onSuccess();

        void onFailure(String message);
    }
}
