package com.example.shoppie.model.usecase.implementation;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shoppie.model.dto.MUser;
import com.example.shoppie.model.usecase.interfaces.ICreateUserProfile;
import com.example.shoppie.staticclass.StaticClass;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalDateTime;

public class CreateUserProfileUseCase implements ICreateUserProfile
{
    private FirebaseFirestore db;
    public CreateUserProfileUseCase()
    {
        db = FirebaseFirestore.getInstance();
    }
    @Override
    public void execute(MUser mUser, Callback callback) {
        db.collection(StaticClass.UserEntity)
                .document(mUser.getId())
                .set(mUser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        callback.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        callback.onFailure(e.getMessage());
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        callback.onFailure("Add the profile unsuccessfully");
                    }
                });
    }
}
