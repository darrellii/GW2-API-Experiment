package com.darrellii.gw2.gw2apiexperment.app.login;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public interface LoginContract {

    interface View {

        void openGW2WebIntent();

        void logIn();

        void showInvalidKey();

        String getAPIKey();

        void openMainActivity();

        boolean isLoggedIn();

        void checkToken(String apiKey);

        void showVerificationError(List<String> missingParams);

        void showRequiredPerms(String requiredPerms);

        void saveAPIKey(String apiKey);
    }

    interface UserActionsListener {

        void onOpenGW2URLClicked();

        void onLogInClicked();

        void onLogIn();

        void onVerifyToken(String[] tokenParams);

        void onCreated();
    }

}
