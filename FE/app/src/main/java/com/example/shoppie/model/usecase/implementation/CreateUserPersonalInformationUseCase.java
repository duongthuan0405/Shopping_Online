package com.example.shoppie.model.usecase.implementation;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.shoppie.model.dto.MUser;
import com.example.shoppie.model.usecase.interfaces.ICreateUserPersonalInformationUseCse;
import com.example.shoppie.staticclass.StaticClass;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CreateUserPersonalInformationUseCase implements ICreateUserPersonalInformationUseCse
{
    FirebaseFirestore db;
    Context context;
    public CreateUserPersonalInformationUseCase(Context context)
    {
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    @Override
    public void execute(MUser mUser, Callback callback) {
        Log.d("THUAN1", mUser.toString());
        Log.d("THUAN1", "THOI DAY");

        Log.d("THUAN", FirebaseApp.getApps(context).isEmpty() ? "EMPTY" : "OK");


    }
}
