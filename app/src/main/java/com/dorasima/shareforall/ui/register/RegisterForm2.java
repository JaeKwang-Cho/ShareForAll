package com.dorasima.shareforall.ui.register;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.ui.login.LoginActivity;

public class RegisterForm2 extends Fragment {

    private RegisterFormViewModel mViewModel;

    private RadioButton selectedButton = null;
    boolean selected = false;
    private EditText phoneNumberForm;
    private RadioGroup ageForm;
    private ImageView profileView;
    private Button selectBtn;

    public static RegisterForm2 newInstance() {
        return new RegisterForm2();
    }
    private RadioButton elderlyForm;
    private RadioButton youngForm;

    private Bitmap profile;

    public void getImageBtn(View view){
        ((RegisterActivity)getActivity()).getImageBtn();
    }


    @Override
    public void onResume() {
        super.onResume();
        profile = ((RegisterActivity)getActivity()).getProfileImage();
        if(profile != null){
            profileView.setImageBitmap(profile);
            mViewModel.registerData2Changed(phoneNumberForm.getText().toString(), profileView.getDrawable());
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.register_form_fragment2, container, false);

        phoneNumberForm = view.findViewById(R.id.phone_number_form);
        ageForm = view.findViewById(R.id.age_group);
        elderlyForm = view.findViewById(R.id.elderlyButton);
        youngForm = view.findViewById(R.id.youngButton);
        profileView = view.findViewById(R.id.register_profile);
        selectBtn = view.findViewById(R.id.register_select);

        selectBtn.setOnClickListener(this::getImageBtn);

        mViewModel = new ViewModelProvider(requireActivity()).get(RegisterFormViewModel.class);

        ageForm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
                if(selectedButton.getText() == "Elderly"){
                    mViewModel.registerData2Changed2(1);
                }else{
                    mViewModel.registerData2Changed2(0);
                }
                selected = true;
            }
        });

        mViewModel.getForm2State().observe(getViewLifecycleOwner(), new Observer<RegisterForm2State>() {
            @Override
            public void onChanged(RegisterForm2State registerForm2State) {
                if (registerForm2State.getPhoneNumberError() != null) {
                    phoneNumberForm.setError(getString(registerForm2State.getPhoneNumberError()));
                }
                if(!selected){
                    elderlyForm.setError(getString(R.string.unselected1));
                    youngForm.setError(getString(R.string.unselected2));
                }
                if(registerForm2State.isDataValid() && selected){
                    elderlyForm.setError(null);
                    youngForm.setError(null);
                    getActivity().findViewById(R.id.register_next).setEnabled(registerForm2State.isDataValid());
                }
            }
        });

        phoneNumberForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                mViewModel.registerData2Changed(phoneNumberForm.getText().toString(), null);
            }
        });
        return view;
    }
}