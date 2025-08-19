package com.example.shoppie.model.usecase.implementation;

import com.example.shoppie.model.usecase.interfaces.ISignOutUseCase;
import com.google.firebase.auth.FirebaseAuth;

public class SignOutUseCase implements ISignOutUseCase {
    private FirebaseAuth auth;
    public SignOutUseCase()
    {
        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void execute() {
        auth.signOut();
    }
}
