package com.example.shoppie.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.model.dto.MAuthentication;
import com.example.shoppie.model.usecase.implementation.ResendVerifyEmail;
import com.example.shoppie.model.usecase.implementation.SignInUseCase;
import com.example.shoppie.model.usecase.implementation.SignOutUseCase;
import com.example.shoppie.model.usecase.interfaces.IResendVerifyEmail;
import com.example.shoppie.model.usecase.interfaces.ISignInUseCase;
import com.example.shoppie.model.usecase.interfaces.ISignOutUseCase;
import com.example.shoppie.presentation.once_event.OnceEvent;

public class SignInViewModel extends ViewModel {
    // <editor-fold desc="Region: Fields">
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MediatorLiveData<Boolean> isValidEmail;
    private MediatorLiveData<Boolean> isValidPassword;
    private ISignInUseCase signInUseCase;
    private IResendVerifyEmail resendVerifyEmail;
    private ISignOutUseCase signOutUseCase;
    private MediatorLiveData<String> errorMessage;
    private MutableLiveData<Boolean> isShowProcessBar;
    private MutableLiveData<OnceEvent<Boolean>> showAlertDialogAskVerifyEvent;
    private MutableLiveData<OnceEvent<Boolean>> onClickCancelAlertDialogAskVerifyEvent;
    private MutableLiveData<OnceEvent<Boolean>> onDismissDialog;
    private MutableLiveData<OnceEvent<String>> showToastEvent;
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

        isValidEmail = new MediatorLiveData<>();
        isValidEmail.addSource(email, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidEmail.setValue(true);
            }
        });

        isValidPassword = new MediatorLiveData<>();
        isValidPassword.addSource(password, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidPassword.setValue(true);
            }
        });

        errorMessage = new MediatorLiveData<>();
        errorMessage.addSource(email, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                errorMessage.setValue("");
            }
        });
        errorMessage.addSource(password, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                errorMessage.setValue("");
            }
        });

        signInUseCase = new SignInUseCase();
        isShowProcessBar = new MutableLiveData<>(false);
        showAlertDialogAskVerifyEvent = new MutableLiveData<>();
        onClickCancelAlertDialogAskVerifyEvent = new MutableLiveData<>();
        onDismissDialog = new MutableLiveData<>();
        resendVerifyEmail = new ResendVerifyEmail();
        showToastEvent = new MutableLiveData<>();
        signOutUseCase = new SignOutUseCase();

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

    public LiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public MediatorLiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsShowProcessBar() {
        return isShowProcessBar;
    }

    public LiveData<OnceEvent<Boolean>> getShowAlertDialogAskVerifyEvent() {
        return showAlertDialogAskVerifyEvent;
    }

    public LiveData<OnceEvent<Boolean>> getOnClickCancelAlertDialogAskVerifyEvent() {
        return onClickCancelAlertDialogAskVerifyEvent;
    }

    public MutableLiveData<OnceEvent<Boolean>> getOnDismissDialog() {
        return onDismissDialog;
    }

    public LiveData<OnceEvent<String>> getShowToastEvent() {
        return showToastEvent;
    }
    // </editor-fold>

    // <editor-fold desc="Region: Methods">
    public void onClick_txVwSignUp()
    {
        navigateToSignUpActEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_btnSignIn()
    {
        isValidPassword.setValue(true);
        isValidEmail.setValue(true);
        boolean isValid = true;
        if(email.getValue() == null || email.getValue().isEmpty())
        {
            isValidEmail.setValue(false);
            isValid = false;
        }
        if(password.getValue() == null || password.getValue().isEmpty())
        {
            isValidPassword.setValue(false);
            isValid = false;
        }

        if(!isValid)
        {
            return;
        }

        isShowProcessBar.setValue(true);
        MAuthentication authentication = new MAuthentication(email.getValue(), password.getValue());
        signInUseCase.execute(authentication, new ISignInUseCase.Callback() {
            @Override
            public void onHaveError(String message) {
                errorMessage.setValue(message);
                isValidPassword.setValue(false);
                isValidEmail.setValue(false);
                isShowProcessBar.setValue(false);
            }

            @Override
            public void onEmailNotVerified() {
                showAlertDialogAskVerifyEvent.setValue(new OnceEvent<>(true));
                isShowProcessBar.setValue(false);
            }

            @Override
            public void onSuccess(MAuthentication mAuthentication) {
                Log.d("THUAN", "Login Sucess");
                isShowProcessBar.setValue(false);
            }
        });
    }

    public void onClick_btnCancel_DialogAskToVerify()
    {
        onClickCancelAlertDialogAskVerifyEvent.setValue(new OnceEvent<>(true));
        signOutUseCase.execute();
    }

    public void onClick_btnResend_DialogAskToVerify()
    {
        isShowProcessBar.setValue(true);
        onDismissDialog.setValue(new OnceEvent<>(true));
        resendVerifyEmail.execute(new IResendVerifyEmail.Callback() {
            @Override
            public void onSuccess() {
                isShowProcessBar.setValue(false);
                showToastEvent.setValue(new OnceEvent<>("Resend verified email successfully"));
            }

            @Override
            public void onHaveError(String message) {
                isShowProcessBar.setValue(false);
                showToastEvent.setValue(new OnceEvent<>(message));
            }
        });
        signOutUseCase.execute();
    }
    // </editor-fold>

}
