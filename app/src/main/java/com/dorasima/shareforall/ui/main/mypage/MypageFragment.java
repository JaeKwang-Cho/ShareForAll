package com.dorasima.shareforall.ui.main.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.data.model.LoggedInUser;
import com.dorasima.shareforall.ui.main.MainActivity;

public class MypageFragment extends Fragment {

    private Button logoutBtn;
    private ImageView profileImage;
    private TextView userName;
    private TextView userEmail;

    private LoggedInUser loggedInUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            loggedInUser = ((MainActivity)getActivity()).getLoggedInUserInfo();
        }else{
            loggedInUser = null;
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {

        View view = inflater.inflate(R.layout.fragment_mypage, container, false);

        logoutBtn = view.findViewById(R.id.Mypage_logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        profileImage = view.findViewById(R.id.Mypage_image);
        userName = view.findViewById(R.id.Mypage_userName);
        userEmail = view.findViewById(R.id.Mypage_email);

        if(loggedInUser != null){
            userName.setText(loggedInUser.getNickName());
            userEmail.setText(loggedInUser.getEmail());
        }

        return view;
    }
}