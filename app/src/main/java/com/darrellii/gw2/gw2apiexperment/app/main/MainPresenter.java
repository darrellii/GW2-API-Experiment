package com.darrellii.gw2.gw2apiexperment.app.main;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View mView;
    private User mUser;

    public MainPresenter(MainContract.View view){
        mView = view;
    }


    @Override
    public void onUserInfoLoaded(User user) {
        mView.setUserName(user.name);
        mUser = user;

    }

    @Override
    public void onNavWallet() {
        mView.changeFragment(MainContract.Fragments.WALLET);
    }

    @Override
    public void onNavLogout() {
        mView.logout();
    }

    @Override
    public void onNavShare() {
        mView.createShareIntent();
    }

    @Override
    public void onNameAbout() {
        mView.openAbout();
    }

    @Override
    public void onNavCharacters() {
        mView.showFeatureInProgress();
    }

    @Override
    public void onNavBank() {
        mView.showFeatureInProgress();
    }

    @Override
    public void onNavGuilds() {
        mView.changeFragment(MainContract.Fragments.GUILDS,mUser.guilds);
    }

    @Override
    public void onUserFailedLoad(String message) {
        mView.showFailedToLoad(message);
    }


}
