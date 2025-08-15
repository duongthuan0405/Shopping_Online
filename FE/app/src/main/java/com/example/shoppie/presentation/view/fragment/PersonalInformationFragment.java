package com.example.shoppie.presentation.view.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.shoppie.R;
import com.example.shoppie.databinding.FragmentPersonalInformationBinding;
import com.example.shoppie.staticclass.StaticClass;
import com.example.shoppie.presentation.contract_vp.PersonalInformation_F_Contract;
import com.example.shoppie.presentation.contract_vp.SignUp_A_Contract;
import com.example.shoppie.presentation.presenter.PersonalInformation_F_Presenter;
import com.example.shoppie.presentation.view.viewmodel_data.SignUpViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonalInformationFragment extends Fragment implements PersonalInformation_F_Contract.IView
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    PersonalInformation_F_Contract.IPresenter presenter = new PersonalInformation_F_Presenter(this);
    FragmentPersonalInformationBinding binding;
    SignUpViewModel signUpViewModel;
    private LocalDate selectedDate = LocalDate.now();

    public PersonalInformationFragment() {
        // Required empty public constructor
    }
    public static PersonalInformationFragment newInstance(String param1, String param2) {
        PersonalInformationFragment fragment = new PersonalInformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_personal_information, container, false);
        binding = FragmentPersonalInformationBinding.inflate(inflater, container, false);

        initViewModel();

        binding.btnNext.setOnClickListener(v -> onClick_btnNext(v));
        binding.txVwBack.setOnClickListener(v -> onClick_btnBack(v));
        binding.txVwYourBirthday.setOnClickListener(v -> onClick_txVwYourBirthday(v));
        binding.edTxYourFullName.setOnTouchListener((v, event) -> { setNormalBackground(v); return false;});
        binding.edTxYourPhoneNumber.setOnTouchListener((v, event) -> {setNormalBackground(v); return false;});
        binding.txVwYourBirthday.setOnTouchListener((v, event) -> {setNormalBackground(v); return false;});

        return binding.getRoot();
    }

    private void setNormalBackground(View view) {
        Drawable normalBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.input_box);
        view.setBackground(normalBackground);
    }

    private void onClick_txVwYourBirthday(View v) {
        DatePickerDialog dialog = new DatePickerDialog(
                this.requireActivity(),
                R.style.my_date_picker_dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth);
                        binding.txVwYourBirthday.setText(selectedDate.format(DateTimeFormatter.ofPattern(StaticClass.DATE_FORMAT)));
                    }
                },
                selectedDate.getYear(), selectedDate.getMonthValue() - 1, selectedDate.getDayOfMonth()
        );
        dialog.show();
    }

    private void initViewModel() {
        signUpViewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);
        binding.setVm(signUpViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

    }

    private void onClick_btnBack(View v) {
        presenter.onClick_btnBack();
    }

    private void onClick_btnNext(View v) {
        String fullName = binding.edTxYourFullName.getText().toString();
        String phoneNumber = binding.edTxYourPhoneNumber.getText().toString();
        String birthday = binding.txVwYourBirthday.getText().toString();
        presenter.onClick_btnNext(fullName, phoneNumber, birthday);
    }

    @Override
    public void changeNextFragment() {
        ((SignUp_A_Contract.IView)requireActivity()).changeNextFragment();
    }

    @Override
    public void handleAsSystemBackPress() {
        ((SignUp_A_Contract.IView)requireActivity()).handleAsBackPressSystem();
    }

    @Override
    public void showError_FullnName() {
        changeErrorBackground(binding.edTxYourFullName);
    }

    @Override
    public void showError_PhoneNumber() {
        changeErrorBackground(binding.edTxYourPhoneNumber);
    }

    @Override
    public void showError_Birthday() {
        changeErrorBackground(binding.txVwYourBirthday);
    }

    private void changeErrorBackground(View view)
    {
        Drawable errorBackground = ContextCompat.getDrawable(requireActivity(), R.drawable.input_box_error);
        view.setBackground(errorBackground);
    }

}