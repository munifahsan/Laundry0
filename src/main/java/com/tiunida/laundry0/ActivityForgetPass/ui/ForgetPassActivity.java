package com.tiunida.laundry0.ActivityForgetPass.ui;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.ActivityForgetPass.ForgetPassPresenter;
import com.tiunida.laundry0.ActivityForgetPass.ForgetPassPresenterMvp;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends AppCompatActivity implements ForgetPassViewMvp{

    private ForgetPassPresenterMvp mForgetPassPresenterMvp;

    @BindView(R.id.forget_pass_progress)
    Button mForgetPassProgress;
    @BindView(R.id.send_email)
    Button mSendBtn;
    @BindView(R.id.forget_email)
    EditText mForgetEmailEdt;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        ButterKnife.bind(this);

        mForgetPassPresenterMvp = new ForgetPassPresenter(this);
        mForgetPassPresenterMvp.onCreate();
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        mSendBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                firebaseAuth.sendPasswordResetEmail(mForgetEmailEdt.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()) {
//
//                        } else {
//                            String message = task.getException().getMessage();
//                        }
//                    }
//                });
//            }
//        });
    }

    @OnClick(R.id.send_email)
    public void onSendBtnOnClick(){
        sendPasswordResetEmail();
    }

    public void showProgress(){
        mForgetPassProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgress(){
        mForgetPassProgress.setVisibility(View.INVISIBLE);
    }

    public void sendPasswordResetEmail(){
        mForgetPassPresenterMvp.sendPasswordResetEmail(mForgetEmailEdt.getText().toString());
    }

    public void showMessage(String message){
        Toast.makeText(ForgetPassActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void navigateToLogin(){
        Intent intent = new Intent(ForgetPassActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
