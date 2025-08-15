package com.example.shoppie.model.usecase.implementation;

import androidx.annotation.NonNull;

import com.example.shoppie.model.usecase.interfaces.ICreateUserProfile;
import com.example.shoppie.model.usecase.interfaces.ISignUpUseCase;
import com.example.shoppie.model.dto.MAuthentication;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpUseCase implements ISignUpUseCase {
    FirebaseAuth auth;
    ICreateUserProfile createUserPersonalInformationUseCse;
    public SignUpUseCase()
    {
        auth = FirebaseAuth.getInstance();
        createUserPersonalInformationUseCse = new CreateUserProfileUseCase();
    }
    @Override
    public void execute(MAuthentication authentication, Callback callback) {
        auth.createUserWithEmailAndPassword(authentication.getEmail(), authentication.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        MAuthentication mAuthentication = new MAuthentication(authResult.getUser().getUid(), authResult.getUser().getEmail(), "");
                        FirebaseUser currentUser = auth.getCurrentUser();
                        if(currentUser != null)
                        {
                            currentUser.sendEmailVerification()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            auth.signOut();
                                            callback.onSuccess(mAuthentication);
                                        }
                                    });
                        }
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
                        callback.onFailure("Can not complete sign up process");
                    }
                });
    }
}
