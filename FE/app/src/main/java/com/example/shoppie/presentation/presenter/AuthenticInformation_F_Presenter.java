package com.example.shoppie.presentation.presenter;

import android.util.Log;
import android.util.Patterns;

import androidx.fragment.app.Fragment;

import com.example.shoppie.model.dto.MUser;
import com.example.shoppie.model.usecase.implementation.CreateUserPersonalInformationUseCase;
import com.example.shoppie.model.usecase.implementation.SignUpUseCase;
import com.example.shoppie.model.usecase.interfaces.ICreateUserPersonalInformationUseCse;
import com.example.shoppie.model.usecase.interfaces.ISignUpUseCase;
import com.example.shoppie.presentation.contract_vp.AuthenticInformation_F_Contract;
import com.example.shoppie.model.dto.MAuthentication;
import com.example.shoppie.presentation.dto.User;

public class AuthenticInformation_F_Presenter implements AuthenticInformation_F_Contract.IPresenter
{
    AuthenticInformation_F_Contract.IView view;
    ISignUpUseCase signUpUseCase;
    ICreateUserPersonalInformationUseCse createUserPersonalInformationUseCse;
    public AuthenticInformation_F_Presenter(AuthenticInformation_F_Contract.IView view) {
        this.view = view;
        signUpUseCase = new SignUpUseCase();
        createUserPersonalInformationUseCse = new CreateUserPersonalInformationUseCase(((Fragment) view).requireActivity().getApplicationContext());
    }

    @Override
    public void onClick_txVwBack() {
        view.handleAsSystemBackPress();
    }

    @Override
    public void onClick_btnSignUp(String email, String password) {
        boolean isValid = true;
        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            view.showError_Email();
            isValid = false;
        }

        if (password.length() < 6)
        {
            view.showError_Password();
            isValid = false;
        }

        if(isValid)
        {
            MAuthentication mAuthentication = new MAuthentication(email, password);
            User user = view.getUser();
            MUser mUser = new MUser(user);

            signUpUseCase.execute(mAuthentication, new ISignUpUseCase.Callback()
            {
                @Override
                public void onSuccess(MAuthentication mAuthentication) {
                    mUser.setId(mAuthentication.getId());
                    createUserPersonalInformationUseCse.execute(mUser, new ICreateUserPersonalInformationUseCse.Callback() {
                        @Override
                        public void onSuccess() {
                            view.onSuccess();
                        }

                        @Override
                        public void onFailure(String message) {
                            view.showError(message);
                        }
                    });
                }

                @Override
                public void onFailure(String message) {
                    view.showError(message);
                }
            }
            );
        }
    }
}
