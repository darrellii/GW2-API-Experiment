package com.darrellii.gw2.gw2apiexperment.app.login;

import com.darrellii.gw2.gw2apiexperment.app.GW2Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class LogInPresenter implements LoginContract.UserActionsListener {

    public LoginContract.View mView;

    public LogInPresenter(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void onOpenGW2URLClicked() {
        mView.openGW2WebIntent();
    }

    @Override
    public void onLogInClicked() {
        String apikey = mView.getAPIKey();

        if (apikey != null && apikey.length() == 72)
            mView.logIn();
        else
            mView.showInvalidKey();
    }

    @Override
    public void onLogIn() {
        mView.checkToken(mView.getAPIKey());
    }

    @Override
    public void onVerifyToken(String[] tokenParams) {
        if (tokenParams == null)
            mView.showVerificationError(null);
        else {
            ArrayList<String> missing = new ArrayList<>(GW2Application.REQUIRED_PERMS.length);
            List<String> perams = Arrays.asList(tokenParams);
            for (String peram :
                    GW2Application.REQUIRED_PERMS) {
                if (!perams.contains(peram))
                    missing.add(peram);
            }
            if (missing.size() > 0) {
                mView.showVerificationError(missing);
            } else {
                mView.saveAPIKey(mView.getAPIKey());
                if (mView.isLoggedIn()) {
                    mView.openMainActivity();
                }
            }
        }
    }

    @Override
    public void onCreated() {
        if(mView.isLoggedIn())
            mView.openMainActivity();

        String text = "";
        for (int i = 0; i < GW2Application.REQUIRED_PERMS.length; i++) {
            String perm = GW2Application.REQUIRED_PERMS[i];
            text +=  "• " + perm + " ";
            if (i%2==1) {
                text += " •\n";
            }
        }

        mView.showRequiredPerms(text);
    }


}
