package com.darrellii.gw2.gw2apiexperment.app.main;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View mView;

    public MainPresenter(MainContract.View view){
        mView = view;
    }


    @Override
    public void onUserInfoLoaded(User user) {
        mView.setUserName(user.name);
        if(user.guilds != null && user.guilds.length > 0){
            mView.loadGuildInfo(user.guilds[0]);
        }
    }

    @Override
    public void onNavWallet() {
        mView.showFeatureInProgress();
    }

    @Override
    public void onNavLogout() {
        mView.logout();
    }

    @Override
    public void onNavShare() {
        mView.showFeatureInProgress();
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
    public void onNaveBank() {
        mView.showFeatureInProgress();
    }

    @Override
    public void onUserFailedLoad(String message) {
        mView.showFailedToLoad(message);
    }

    @Override
    public void onGuildLoaded(GuildInfo guildInfo) {
        mView.setGuild("["+guildInfo.tag+"]"+guildInfo.guild_name);
    }

    @Override
    public void onGuildFailedLoad() {
        mView.showFailedToLoad("Failed to Load Guild Info");
    }
}
