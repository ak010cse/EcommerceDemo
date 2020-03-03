package com.example.ecommerce.fragment;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetPassword extends Fragment {
    private EditText forgetEmail;
    private TextView goBackTextView;
    private Button reset_password;
    private View view;
    private FrameLayout parentFrameLayout;
    private ProgressBar progressBar;
    private LinearLayout sentTextLayout;
    private ViewGroup emailIconContainer;
    private ImageView email_icon;
    private TextView sentEmailText;
    FirebaseAuth firebaseAuth;


    public ForgetPassword() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_forget_password, container, false);
        forgetEmail = view.findViewById(R.id.forget_email_editText);
        goBackTextView = view.findViewById(R.id.goBack);
        reset_password = view.findViewById(R.id.reset_password_button);
        progressBar = view.findViewById(R.id.progressBar2);
        emailIconContainer = view.findViewById(R.id.sentTextLayout);
        sentEmailText = view.findViewById(R.id.sent_email_textView);
        email_icon = view.findViewById(R.id.email_icon);

        parentFrameLayout = getActivity().findViewById(R.id.frameLayout);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        goBackTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignInFragment());
            }
        });

        forgetEmail.addTextChangedListener(new TextWatcher() {
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

        reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (forgetEmail.getText().toString().isEmpty()) {
                    forgetEmail.setError("Please enter email");
                    forgetEmail.requestFocus();
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(emailIconContainer);
                        sentEmailText.setVisibility(View.GONE);
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        TransitionManager.beginDelayedTransition(emailIconContainer);
                        email_icon.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    reset_password.setEnabled(false);
                    reset_password.setTextColor(Color.argb(50, 255, 255, 255));
                    firebaseAuth.sendPasswordResetEmail(forgetEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "email sent successfully! ", Toast.LENGTH_SHORT).show();
                                ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, sentEmailText.getWidth() / 2, sentEmailText.getHeight() / 2);
                                scaleAnimation.setDuration(100);
                                scaleAnimation.setInterpolator(new AccelerateInterpolator());
                                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                    @Override
                                    public void onAnimationEnd(Animation animation) {

                                        sentEmailText.setVisibility(View.VISIBLE);
                                        sentEmailText.setText("Recovery email sent successfully ! check your inbox");
                                        sentEmailText.setTextColor(getResources().getColor(R.color.colorGreen));

                                        TransitionManager.beginDelayedTransition(emailIconContainer);
                                        sentEmailText.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {
                                        email_icon.setImageResource((R.drawable.ic_mailsent));
                                    }
                                });
                                scaleAnimation.setRepeatMode(Animation.REVERSE);
                                scaleAnimation.setRepeatCount(1);
                                email_icon.startAnimation(scaleAnimation);
                            } else {
                                String error = task.getException().getMessage();
//                            Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                                reset_password.setEnabled(true);
                                reset_password.setTextColor(Color.rgb(255, 255, 255));

                                sentEmailText.setText(error);
                                sentEmailText.setTextColor(getResources().getColor(R.color.colorRed));
                                email_icon.setImageResource(R.drawable.ic_mail);

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                    sentEmailText.setVisibility(View.VISIBLE);
                                }


                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    TransitionManager.beginDelayedTransition(emailIconContainer);
                                }
                            }
                            progressBar.setVisibility(View.INVISIBLE);

                        }
                    });
                }


            }
        });

    }

    private void checkInputs() {
        if (TextUtils.isEmpty(forgetEmail.getText().toString())) {
            reset_password.setEnabled(false);
            reset_password.setTextColor(Color.argb(50, 255, 255, 255));
        } else {
            reset_password.setEnabled(true);
            reset_password.setTextColor(Color.rgb(255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        transaction.replace(parentFrameLayout.getId(), fragment);
        transaction.commit();
    }


}
