package com.example.shoppie.viewmodel;

import android.util.Log;
import android.util.Patterns;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.shoppie.model.dto.MAuthentication;
import com.example.shoppie.model.dto.MUser;
import com.example.shoppie.model.usecase.implementation.CreateUserProfileUseCase;
import com.example.shoppie.model.usecase.implementation.SignUpUseCase;
import com.example.shoppie.model.usecase.interfaces.ICreateUserProfile;
import com.example.shoppie.model.usecase.interfaces.ISignUpUseCase;
import com.example.shoppie.presentation.once_event.OnceEvent;
import com.example.shoppie.presentation.view.fragment.AuthenticInformationFragment;
import com.example.shoppie.presentation.view.fragment.PersonalInformationFragment;

import java.util.ArrayList;

import kotlin.jvm.functions.Function1;

public class SignUpViewModel extends ViewModel
{
    // <editor-fold desc="Region: Data Fields">
    private MutableLiveData<String> fullName;
    private MutableLiveData<String> phoneNumber;
    private MutableLiveData<String> birthday;
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private LiveData<String> strPosition;
    private ArrayList<Fragment> fragments;
    private MutableLiveData<Integer> position;
    private MediatorLiveData<Boolean> isValidFullName;
    private MediatorLiveData<Boolean> isValidPhoneNumber;
    private MediatorLiveData<Boolean> isValidBirthday;
    private MediatorLiveData<Boolean> isValidEmail;
    private MediatorLiveData<Boolean> isValidPassword;
    private ISignUpUseCase signUpUseCase;
    private ICreateUserProfile createUserProfile;
    private MediatorLiveData <String> errorMessage;
    private MutableLiveData<OnceEvent<Boolean>> dismissDialogEvent;

    // </editor-fold>

    // <editor-fold desc="Region: Event Fields">
    private MutableLiveData<Boolean> isShowProcessBar;
    private MutableLiveData<OnceEvent<Boolean>> asBackSystemEvent;
    private MutableLiveData<OnceEvent<Boolean>> changeNextFragmentEvent;
    private MutableLiveData<OnceEvent<Boolean>> showDatePickerDialogEvent;
    private MutableLiveData<OnceEvent<Boolean>> showSignUpSuccessEvent;

    // </editor-fold>

    // <editor-fold desc="Region: Constructor">

    public SignUpViewModel()
    {
        fullName = new MutableLiveData<>("");
        phoneNumber = new MutableLiveData<>("");
        birthday = new MutableLiveData<>("");
        email = new MutableLiveData<>("");
        password = new MutableLiveData<>("");
        position = new MutableLiveData<>(0);

        strPosition = (LiveData<String>)Transformations.map(position, new Function1<Integer, String>() {
            @Override
            public String invoke(Integer integer) {
                return String.format("%d of %d", integer + 1, fragments.size());
            }
        });

        isShowProcessBar = new MutableLiveData<>(false);

        fragments = new ArrayList<>();
        fragments.add(new PersonalInformationFragment());
        fragments.add(new AuthenticInformationFragment());
        asBackSystemEvent = new MutableLiveData<>();
        changeNextFragmentEvent = new MutableLiveData<>(new OnceEvent<>(true));

        isValidFullName = new MediatorLiveData<>(true);
        isValidFullName.addSource(fullName, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidFullName.setValue(true);
            }
        });

        isValidPassword = new MediatorLiveData<>(true);
        isValidPassword.addSource(password, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidPassword.setValue(true);
            }
        });

        isValidEmail = new MediatorLiveData<>(true);
        isValidEmail.addSource(email, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidEmail.setValue(true);
            }
        });

        isValidBirthday = new MediatorLiveData<>(true);
        isValidBirthday.addSource(birthday, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidBirthday.setValue(true);
            }
        });

        isValidPhoneNumber = new MediatorLiveData<>(true);
        isValidPhoneNumber.addSource(phoneNumber, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                isValidPhoneNumber.setValue(true);
            }
        });

        showDatePickerDialogEvent = new MutableLiveData<>();
        signUpUseCase = new SignUpUseCase();
        createUserProfile = new CreateUserProfileUseCase();
        showSignUpSuccessEvent = new MutableLiveData<>();

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

        dismissDialogEvent = new MutableLiveData<>();
    }
    // </editor-fold>

    // <editor-fold desc="Region: Getter & Setter">
    public LiveData<String> getStrPosition() {
        return strPosition;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public MutableLiveData<String> getBirthday() {
        return birthday;
    }

    public MutableLiveData<String> getPhoneNumber() {
        return phoneNumber;
    }

    public MutableLiveData<String> getFullName() {
        return fullName;
    }

    public LiveData<Boolean> getIsShowProcessBar() {
        return isShowProcessBar;
    }
    public Fragment getCurrentFragment()
    {
        return fragments.get(position.getValue());
    }

    public void setShowProcessBar(boolean isShow)
    {
        isShowProcessBar.setValue(isShow);
    }

    public LiveData<OnceEvent<Boolean>> getChangeNextFragmentEvent() {
        return changeNextFragmentEvent;
    }
    public LiveData<OnceEvent<Boolean>> getAsBackSystemEvent() {
        return asBackSystemEvent;
    }

    public int getPosition() {
        return position.getValue();
    }

    public LiveData<Boolean> getIsValidFullName() {
        return isValidFullName;
    }

    public LiveData<Boolean> getIsValidPhoneNumber() {
        return isValidPhoneNumber;
    }

    public LiveData<Boolean> getIsValidBirthday() {
        return isValidBirthday;
    }

    public LiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public LiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public LiveData<OnceEvent<Boolean>> getShowDatePickerDialogEvent() {
        return showDatePickerDialogEvent;
    }

    public LiveData<OnceEvent<Boolean>> getShowSignUpSuccessEvent() {
        return showSignUpSuccessEvent;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<OnceEvent<Boolean>> getDismissDialogEvent() {
        return dismissDialogEvent;
    }
    // </editor-fold>

    // <editor-fold desc="Region: Methods">
    public void onClick_BackFragment()
    {
        asBackSystemEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_Next_FPersonalInfo()
    {

        boolean isValid = true;
        if(fullName.getValue() == null || fullName.getValue().isEmpty())
        {
            isValid = false;
            isValidFullName.setValue(false);
        }
        if(phoneNumber.getValue() == null || phoneNumber.getValue().isEmpty())
        {
            isValid = false;
            isValidPhoneNumber.setValue(false);
        }
        if(birthday.getValue() == null || birthday.getValue().isEmpty())
        {
            isValid = false;
            isValidBirthday.setValue(false);
        }
        if(!isValid)
        {
            return;
        }

        moveToNextPosition();
        changeNextFragmentEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_btnSignUp()
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

        MAuthentication mAuthentication = new MAuthentication(email.getValue(), password.getValue());
        MUser mUser = new MUser(fullName.getValue(), phoneNumber.getValue(), birthday.getValue());
        isShowProcessBar.setValue(true);
        signUpUseCase.execute(mAuthentication, new ISignUpUseCase.Callback() {
            @Override
            public void onSuccess(MAuthentication mAuthentication) {
                mUser.setId(mAuthentication.getId());
                createUserProfile.execute(mUser, new ICreateUserProfile.Callback() {
                    @Override
                    public void onSuccess() {
                        showSignUpSuccessEvent.setValue(new OnceEvent<>(true));
                        isShowProcessBar.setValue(false);
                        showSignUpSuccessEvent.setValue(new OnceEvent<>(true));
                    }

                    @Override
                    public void onFailure(String message) {
                        errorMessage.setValue(message);
                        isValidEmail.setValue(false);
                        isValidPassword.setValue(false);
                        isShowProcessBar.setValue(false);
                    }
                });
            }

            @Override
            public void onFailure(String message) {
                errorMessage.setValue(message);
                isValidEmail.setValue(false);
                isValidPassword.setValue(false);
                isShowProcessBar.setValue(false);
            }
        });
    }

    public void moveToNextPosition()
    {
        int pos = position.getValue() + 1;
        if(pos >= fragments.size())
        {
            return;
        }

        position.setValue(pos);
    }

    public void backToPreviousPosition()
    {
        int pos = position.getValue() - 1;
        if(pos >= 0)
        {
            position.setValue(pos);
        }
    }

    public void onClick_txVwBirthday_FPersonalInfo()
    {
        showDatePickerDialogEvent.setValue(new OnceEvent<>(true));
    }

    public void onClick_btnOK_alDl()
    {
        dismissDialogEvent.setValue(new OnceEvent<>(true));
    }
    // </editor-fold>

}
