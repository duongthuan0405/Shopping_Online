package com.example.shoppie.model.usecase.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shoppie.model.dto.MAuthentication;
import com.example.shoppie.model.usecase.interfaces.ISignInUseCase;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInUseCase implements ISignInUseCase
{
    FirebaseAuth auth;
    public SignInUseCase()
    {
        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void execute(MAuthentication authentication, Callback callback) {
        auth.signInWithEmailAndPassword(authentication.getEmail(), authentication.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = auth.getCurrentUser();
                        if(user != null && !user.isEmailVerified())
                        {
                            callback.onEmailNotVerified();
                        }
                        else
                        {
                            MAuthentication mAuthentication = new MAuthentication(authResult.getUser().getUid(), authResult.getUser().getEmail(), "");
                            callback.onSuccess(mAuthentication);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("THUAN", "On failure");
                        callback.onHaveError(e.getMessage());
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Log.d("THUAN", "On cancel");
                        callback.onHaveError("Sign in unsuccessfully");
                    }
                });
    }
}
