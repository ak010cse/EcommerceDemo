package com.example.ecommerce.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.ecommerce.R;
import com.example.ecommerce.helper.Utils;
import com.example.ecommerce.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.ecommerce.activity.RegisterActivity.onResetPasswordFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {
    private View view;
    private TextView not_account_textView, forget_password;
    private Button signIn;
    private EditText email_editText, password_editText;
    private FrameLayout parentFrameLayout;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        not_account_textView = (TextView) view.findViewById(R.id.not_account_textView);
        forget_password = (TextView) view.findViewById(R.id.forget_password);
        email_editText = (EditText) view.findViewById(R.id.email);
        password_editText = (EditText) view.findViewById(R.id.password);
        signIn = (Button) view.findViewById(R.id.signIn);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        parentFrameLayout = (FrameLayout) getActivity().findViewById(R.id.frameLayout);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        not_account_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isOnline(getActivity())) {

                    String email = email_editText.getText().toString().trim();
                    String pass = password_editText.getText().toString().trim();
                    if (email.isEmpty()) {
                        Toast.makeText(getActivity(), "please enter email !", Toast.LENGTH_SHORT).show();
                        email_editText.requestFocus();
                        return;
                    }
                    if (!email.matches(emailPattern)) {
                        Toast.makeText(getActivity(), "Invalid email address !", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (pass.isEmpty()) {
                        Toast.makeText(getActivity(), "please enter password !", Toast.LENGTH_SHORT).show();
                        password_editText.requestFocus();

                    } else if (pass.length() < 6) {
                        Toast.makeText(getActivity(), "your password is less than 6 character !", Toast.LENGTH_SHORT).show();
                        password_editText.requestFocus();
                    } else {
                        progressBar.setVisibility(View.VISIBLE);

                        firebaseAuth.signInWithEmailAndPassword(email_editText.getText().toString(), password_editText.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.INVISIBLE);

                                        if (task.isSuccessful()) {
                                            String data = task.toString();
                                            Log.e("data_reg", data);

                                            Intent intent = new Intent(getActivity(), MainActivity.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        } else {
                                            progressBar.setVisibility(View.INVISIBLE);
                                            String error = task.getException().getMessage();
                                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                            Log.e("onComplete: ", error);
                                        }

                                    }
                                });
                    }
                } else {
                    Toast.makeText(getActivity(), " Please check your internet ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetPasswordFragment = true;
                setFragment(new ForgetPassword());
            }
        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_right, R.anim.slideout_from_left);
        transaction.replace(parentFrameLayout.getId(), fragment);
        transaction.commit();
    }

}
