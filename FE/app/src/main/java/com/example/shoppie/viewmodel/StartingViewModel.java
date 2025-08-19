package com.example.shoppie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.model.usecase.implementation.GetRealTimeUser;
import com.example.shoppie.model.usecase.interfaces.IGetRealTimeUser;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.google.firebase.auth.FirebaseUser;

public class StartingViewModel extends ViewModel
{
    // <editor-fold desc="Region:Fields">
    private MutableLiveData<OnceEvent<Boolean>> navigateToSignInActivityEvent;
    private MutableLiveData<OnceEvent<Boolean>> navigateToMainActivityEvent;
    IGetRealTimeUser getRealTimeUser;
    // </editor-fold>

    // <editor-fold desc="Region:Constructor">
    public StartingViewModel()
    {
        this.navigateToSignInActivityEvent = new MutableLiveData<>();
        navigateToMainActivityEvent = new MutableLiveData<>();
        getRealTimeUser = new GetRealTimeUser();
    }
    // </editor-fold>

    // <editor-fold desc="Region:Getter & Setter">
    public LiveData<OnceEvent<Boolean>> getNavigateToSignInActivityEvent() {
        return navigateToSignInActivityEvent;
    }

    public LiveData<OnceEvent<Boolean>> getNavigateToMainActivityEvent() {
        return navigateToMainActivityEvent;
    }
    // </editor-fold>

    // <editor-fold desc="Region:Methods">
    public void onClick_btnStarting()
    {
        getRealTimeUser.execute(new IGetRealTimeUser.Callback() {
            @Override
            public void onHaveTokenUser(FirebaseUser currentUserAfterRefresh) {
                navigateToMainActivityEvent.setValue(new OnceEvent<>(true));
            }

            @Override
            public void onNotHaveAnyTokenUser() {
                navigateToSignInActivityEvent.setValue(new OnceEvent<>(true));
            }
        });

    }
    // </editor-fold>
}
