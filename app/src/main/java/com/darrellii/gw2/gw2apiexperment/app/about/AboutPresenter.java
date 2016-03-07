package com.darrellii.gw2.gw2apiexperment.app.about;

/**
 * Created by dj on 3/7/16.
 * For Use By GW2 API Experment
 */
public class AboutPresenter implements AboutContract.UserActionsListener {

    private final AboutContract.View mView;

    public AboutPresenter(AboutContract.View view){
        this.mView = view;
    }

    @Override
    public void onTwitterClicked() {
        mView.openURL("https://twitter.com/darrellii");
    }

    @Override
    public void onMediumClicked() {
        mView.openURL("https://medium.com/@darrellii");
    }
}
