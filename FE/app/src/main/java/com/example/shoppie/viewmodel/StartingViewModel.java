package com.example.shoppie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.presentation.once_event.OnceEvent;

public class StartingViewModel extends ViewModel
{
    // <editor-fold desc="Region:Fields">
    private MutableLiveData<OnceEvent<Boolean>> navigateToSignInActivityEvent;
    // </editor-fold>

    // <editor-fold desc="Region:Constructor">
    public StartingViewModel()
    {
        this.navigateToSignInActivityEvent = new MutableLiveData<>();
    }
    // </editor-fold>

    // <editor-fold desc="Region:Getter & Setter">
    public LiveData<OnceEvent<Boolean>> getNavigateToSignInActivityEvent() {
        return navigateToSignInActivityEvent;
    }
    // </editor-fold>

    // <editor-fold desc="Region:Methods">
    public void onClick_btnStarting()
    {
        this.navigateToSignInActivityEvent.setValue(new OnceEvent<>(true));
    }
    // </editor-fold>
}
