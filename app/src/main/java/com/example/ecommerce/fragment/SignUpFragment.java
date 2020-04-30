package com.example.ecommerce.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import com.example.ecommerce.helper.Utils;
import com.example.ecommerce.activity.MainActivity;
import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
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
    private EditText email_editText, name_editText, password_editText, confirm_password_editText;
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
        name_editText = (EditText) view.findViewById(R.id.name);
        email_editText = (EditText) view.findViewById(R.id.email);
        password_editText = (EditText) view.findViewById(R.id.password);
        confirm_password_editText = (EditText) view.findViewById(R.id.confirm_password);
        close = (ImageButton) view.findViewById(R.id.close);
        parentFrameLayout = getActivity().findViewById(R.id.frameLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Utils.isOnline(getActivity())) {

                    String fname = name_editText.getText().toString().trim();
                    String email = email_editText.getText().toString().trim();

                    if (fname.isEmpty()) {
                        Toast.makeText(getActivity(), "please enter name !", Toast.LENGTH_SHORT).show();
                        name_editText.requestFocus();
                        return;
                    }


                    if (email.isEmpty()) {
                        Toast.makeText(getActivity(), "please enter email !", Toast.LENGTH_SHORT).show();
                        email_editText.requestFocus();
                        return;
                    }
                    if (!email.matches(emailPattern)) {
                        Toast.makeText(getActivity(), "Invalid email address !", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String pass = password_editText.getText().toString().trim();
                    String cpass = confirm_password_editText.getText().toString().trim();


                    if (pass.isEmpty()) {
                        Toast.makeText(getActivity(), "please enter password !", Toast.LENGTH_SHORT).show();
                        password_editText.requestFocus();
                        return;
                    }
                    if (pass.length() < 6) {
                        Toast.makeText(getActivity(), "your password is less than 6 character !", Toast.LENGTH_SHORT).show();
                        password_editText.requestFocus();
                        return;
                    }
                    if (!cpass.equals(pass)) {
                        Toast.makeText(getActivity(), "Password Not matching !", Toast.LENGTH_SHORT).show();
                        password_editText.requestFocus();

                    }else {
                        progressBar.setVisibility(View.VISIBLE);

                        firebaseAuth.createUserWithEmailAndPassword(email_editText.getText().toString(), password_editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.INVISIBLE);

                                    HashMap<Object,String>userData=new HashMap<>();
                                    userData.put("fullName",name_editText.getText().toString());
                                    userData.put("email",email_editText.getText().toString());

                                    firebaseFirestore.collection("USERS")
                                            .add(userData)
                                            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                                    if (task.isSuccessful()){
                                                        progressBar.setVisibility(View.INVISIBLE);

                                                        String data = task.toString();
                                                        Log.e("data",data);
                                                        Intent intent = new Intent(getActivity(), MainActivity.class);
                                                        startActivity(intent);
                                                        getActivity().finish();
                                                    }else {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        String error=task.getException().getMessage();
                                                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                                        Log.e( "onComplete: ",error );
                                                    }
                                                }
                                            });



                                } else {
                                    progressBar.setVisibility(View.INVISIBLE);

                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                    Log.e("error", error);
                                }

                            }
                        });
                    }

                } else {
                    Toast.makeText(getActivity(), " Please check your internet !", Toast.LENGTH_SHORT).show();
                }
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

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        transaction.replace(parentFrameLayout.getId(), fragment);
        transaction.commit();
    }

}
