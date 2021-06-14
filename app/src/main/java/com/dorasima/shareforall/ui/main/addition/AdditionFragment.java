package com.dorasima.shareforall.ui.main.addition;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dorasima.shareforall.R;
import com.dorasima.shareforall.ui.main.comments.Comments.CommentsContent;
import com.dorasima.shareforall.ui.main.comments.CommentsActivity;

public class AdditionFragment extends Fragment {
    boolean isYes = false;

    ImageView imageView ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_addition, container, false);

        imageView = view.findViewById(R.id.adimage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Apply!!");
                builder.setIcon(android.R.drawable.btn_star);

                builder.setMessage("Would you like to join??");

                // 다이얼로그 리스너 세팅
                CustomDialogListener listener = new CustomDialogListener();

                builder.setPositiveButton("yes",listener);
                builder.setNegativeButton("not yet",null);


                builder.show();
            }
        });

        return view;
    }

    // 커스텀 다이얼로그 리스너
    class CustomDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Toast toast = Toast.makeText(getActivity(), "Ok!! we will contact you later~~",Toast.LENGTH_LONG);
            toast.show();
        }
    }
}