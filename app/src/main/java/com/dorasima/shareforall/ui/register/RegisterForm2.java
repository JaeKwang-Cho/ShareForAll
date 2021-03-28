package com.dorasima.shareforall.ui.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.dorasima.shareforall.R;

public class RegisterForm2 extends Fragment {

    private RegisterFormViewModel mViewModel;

    RadioButton selectedButton = null;
    boolean selected = false;
    private EditText phoneNumberForm;
    private RadioGroup ageForm;

    public static RegisterForm2 newInstance() {
        return new RegisterForm2();
    }
    private RadioButton elderlyForm;
    private RadioButton youngForm;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.register_form_fragment2, container, false);

        EditText phoneNumberForm = view.findViewById(R.id.phone_number_form);
        RadioGroup ageForm = view.findViewById(R.id.age_group);
        RadioButton elderlyButton = view.findViewById(R.id.elderlyButton);
        RadioButton youngButton = view.findViewById(R.id.youngButton);

        mViewModel = new ViewModelProvider(this).get(RegisterFormViewModel.class);

        ageForm.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
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
                    elderlyButton.setError(getString(R.string.unselected1));
                    youngButton.setError(getString(R.string.unselected2));
                }
                if(registerForm2State.isDataValid() && selected){
                    elderlyButton.setError(null);
                    youngButton.setError(null);
                    getActivity().findViewById(R.id.register_next).setEnabled(registerForm2State.isDataValid());
                }
            }
        });

        phoneNumberForm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mViewModel.registerData2Changed(phoneNumberForm.getText().toString());
            }
        });

        return view;
    }

}