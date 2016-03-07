package com.darrellii.gw2.gw2apiexperment.app.about;

/**
 * Created by dj on 3/7/16.
 * For Use By GW2 API Experment
 */
public interface AboutContract {
    interface View {
        void openURL(String url);
    }

    interface UserActionsListener {
        void onTwitterClicked();
        void onMediumClicked();
    }
}
