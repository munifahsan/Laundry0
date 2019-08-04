package com.tiunida.laundry0.ActivityForgetPass;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.ActivityForgetPass.events.ForgetPassEvents;
import com.tiunida.laundry0.ActivityForgetPass.ui.ForgetPassActivity;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

public class ForgetPassRepository implements ForgetPassRepositoryMvp {

    FirebaseAuth firebaseAuth;

    public ForgetPassRepository() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void sendPasswordResetEmail(String email) {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    postEvent(ForgetPassEvents.onSendEmailSucces);
                } else {
                    String message = task.getException().getMessage();
                    postEvent(ForgetPassEvents.onSendEmailError, message);
                }
            }
        });
    }

    private void postEvent(int type, String errorMessage) {
        ForgetPassEvents loginEvent = new ForgetPassEvents();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
