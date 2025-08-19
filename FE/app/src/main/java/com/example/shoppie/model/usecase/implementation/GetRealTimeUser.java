package com.example.shoppie.model.usecase.implementation;

import androidx.annotation.NonNull;

import com.example.shoppie.model.usecase.interfaces.IGetRealTimeUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GetRealTimeUser implements IGetRealTimeUser {
    FirebaseAuth auth;
    public GetRealTimeUser()
    {
        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void execute(Callback callback) {
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null)
        {
            callback.onNotHaveAnyTokenUser();
            return;
        }

        auth.getCurrentUser().reload()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        FirebaseUser currentUserAfterRefresh = auth.getCurrentUser();
                        callback.onHaveTokenUser(currentUserAfterRefresh);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onNotHaveAnyTokenUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onNotHaveAnyTokenUser();
                    }
                });
    }
}
