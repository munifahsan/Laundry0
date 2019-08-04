package com.tiunida.laundry0.ActivityForgetPass;

public interface ForgetPassPresenterMvp {
    void sendPasswordResetEmail(String email);
    void onCreate();
    void onDestroy();
}
