package com.example.shoppie.model.usecase.implementation;

import androidx.annotation.NonNull;

import com.example.shoppie.model.dto.MAuthentication;
import com.example.shoppie.model.usecase.interfaces.IGetRealTimeUser;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GetRealTimeCurrenrUser implements IGetRealTimeUser {
    private FirebaseAuth auth;
    public GetRealTimeCurrenrUser()
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
                        FirebaseUser user = auth.getCurrentUser();
                        MAuthentication currentUserAfterRefresh = new MAuthentication(user.getUid(), user.getEmail(), "");
                        callback.onHaveTokenUser(currentUserAfterRefresh);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        auth.signOut();
                        callback.onNotHaveAnyTokenUser();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        auth.signOut();
                        callback.onNotHaveAnyTokenUser();
                    }
                });
    }
}
