package com.example.shoppie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.presentation.once_event.OnceEvent;

public class SignInViewModel extends ViewModel {
    // <editor-fold desc="Region: Fields">
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> getIsValidPassword;
    // </editor-fold>

    // <editor-fold desc="Region: Event Fields">
    MutableLiveData<OnceEvent<Boolean>> navigateToSignUpActEvent;
    // </editor-fold>

    // <editor-fold desc="Region: Constructor">
    public SignInViewModel()
    {
        email = new MutableLiveData<>();
        password = new MutableLiveData<>();
        navigateToSignUpActEvent = new MutableLiveData<>();
    }
    // </editor-fold>

    // <editor-fold desc="Region: Getter & Setter">
    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public LiveData<OnceEvent<Boolean>> getNavigateToSignUpActEvent() {
        return navigateToSignUpActEvent;
    }
    // </editor-fold>

    // <editor-fold desc="Region: Methods">
    public void onClick_txVwSignUp()
    {
        navigateToSignUpActEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_btnSignIn()
    {
        if
    }
    // </editor-fold>

}
