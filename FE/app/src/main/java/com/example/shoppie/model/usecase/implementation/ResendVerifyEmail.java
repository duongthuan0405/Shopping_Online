package com.example.shoppie.model.usecase.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shoppie.model.usecase.interfaces.IResendVerifyEmail;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResendVerifyEmail implements IResendVerifyEmail {
    FirebaseAuth auth;
    public ResendVerifyEmail()
    {
        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void execute(Callback callback) {
        FirebaseUser user = auth.getCurrentUser();
        if(user == null)
            return;
        user.sendEmailVerification()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onHaveError(e.getMessage());
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        callback.onHaveError("Resend verified email unsuccessfully");
                    }
                });
    }
}
