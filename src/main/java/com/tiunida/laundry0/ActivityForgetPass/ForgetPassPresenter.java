package com.tiunida.laundry0.ActivityForgetPass;

import com.tiunida.laundry0.ActivityForgetPass.events.ForgetPassEvents;
import com.tiunida.laundry0.ActivityForgetPass.ui.ForgetPassViewMvp;
import com.tiunida.laundry0.ActivityLogin.events.LoginEvent;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import static com.tiunida.laundry0.ActivityForgetPass.events.ForgetPassEvents.onSendEmailError;
import static com.tiunida.laundry0.ActivityForgetPass.events.ForgetPassEvents.onSendEmailSucces;

public class ForgetPassPresenter implements ForgetPassPresenterMvp{
    private ForgetPassInteractorMvp mForgetPassInteractorMvp;
    private ForgetPassViewMvp mForgetPassViewMvp;

    EventBus mEventBus;

    public ForgetPassPresenter(ForgetPassViewMvp forgetPassViewMvp){
        mForgetPassInteractorMvp = new ForgetPassInteractor();
        mForgetPassViewMvp = forgetPassViewMvp;
        mEventBus = GreenRobotEventBus.getInstance();
    }

    public void sendPasswordResetEmail(String email){
        if (email!=null){
            mForgetPassViewMvp.showProgress();
            mForgetPassInteractorMvp.sendPasswordResetEmail(email);
        }
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mForgetPassViewMvp = null;
        mEventBus.unregister(this);
    }

    @Subscribe
    public void onEventMainThread(ForgetPassEvents event) {
        switch (event.getEventType()) {
            case onSendEmailSucces:
                onSendEmailSucces();
                break;
            case onSendEmailError:
                onSendEmailError(event.getErrorMessage());
                break;
        }
    }

    private void onSendEmailSucces() {
        if (mForgetPassViewMvp != null){
            mForgetPassViewMvp.hideProgress();
            mForgetPassViewMvp.navigateToLogin();
            mForgetPassViewMvp.showMessage("Link reset password telah terkirim ke alamat E-mail antum");
        }
    }

    private void onSendEmailError(String message) {
        if (mForgetPassViewMvp != null){
            mForgetPassViewMvp.showMessage(message);
        }
    }

}
