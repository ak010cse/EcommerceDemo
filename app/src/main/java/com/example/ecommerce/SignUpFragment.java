package com.example.ecommerce;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    private TextView alreadyAccount;
    private View view;
    private FrameLayout parentFrameLayout;
    private ProgressBar progressBar;
    private Button signUp;
    private EditText email, name, password, confirm_password;
    private ImageButton close;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        alreadyAccount = (TextView) view.findViewById(R.id.already_account_textView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        signUp = (Button) view.findViewById(R.id.signUp);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email);
        password = (EditText) view.findViewById(R.id.password);
        confirm_password = (EditText) view.findViewById(R.id.confirm_password);
        close = (ImageButton) view.findViewById(R.id.close);
        parentFrameLayout = getActivity().findViewById(R.id.frameLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirm_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                progressBar.setVisibility(View.VISIBLE);
                checkEmailAndPassword();
            }
        });

        alreadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void checkInputs() {
        if (!TextUtils.isEmpty(email.getText().toString())) {

            if (!TextUtils.isEmpty(name.getText().toString())) {

                if (!TextUtils.isEmpty(password.getText().toString()) && password.length() >= 6) {

                    if (!TextUtils.isEmpty(confirm_password.getText().toString())) {
                        signUp.setEnabled(true);
                        signUp.setTextColor(R.color.black);
                    } else {
                        signUp.setEnabled(false);
                        signUp.setTextColor(Color.argb(50, 255, 255, 255));
                    }
                } else {
                    signUp.setEnabled(false);
                    signUp.setTextColor(Color.argb(50, 255, 255, 255));
                }
            } else {
                signUp.setEnabled(false);
                signUp.setTextColor(Color.argb(50, 255, 255, 255));
            }
        } else {
            signUp.setEnabled(false);
            signUp.setTextColor(Color.argb(50, 255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        transaction.replace(parentFrameLayout.getId(), fragment);
        transaction.commit();
    }

    private void checkEmailAndPassword() {

        if (email.getText().toString().matches(emailPattern)) {
            if (password.getText().toString().equals(confirm_password.getText().toString())) {
                progressBar.setVisibility(View.VISIBLE);
                signUp.setEnabled(false);
                signUp.setTextColor(Color.argb(50, 255, 255, 255));

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            HashMap<Object,String>userData=new HashMap<>();
                            userData.put("fullName",name.getText().toString());
                            userData.put("email",email.getText().toString());

                            firebaseFirestore.collection("USERS")
                                    .add(userData)
                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {

                                            if (task.isSuccessful()){
                                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                                startActivity(intent);
                                                getActivity().finish();
                                            }else {
                                                progressBar.setVisibility(View.INVISIBLE);
                                                signUp.setEnabled(true);
                                                signUp.setTextColor(Color.argb(50, 255, 255, 255));
                                                String error=task.getException().getMessage();
                                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                Log.e( "onComplete: ",error );
                                            }
                                        }
                                    });



                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            signUp.setEnabled(true);
                            signUp.setTextColor(Color.argb(50, 255, 255, 255));
                            String error = task.getException().getMessage();
                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                            Log.e("error", error);
                        }

                    }
                });
            } else {
                confirm_password.setError("Password doesn't matched!");
                confirm_password.requestFocus();
                progressBar.setVisibility(View.INVISIBLE);
            }
        } else {
            email.setError("Invalid Email!");
            email.requestFocus();
        }

    }
}
