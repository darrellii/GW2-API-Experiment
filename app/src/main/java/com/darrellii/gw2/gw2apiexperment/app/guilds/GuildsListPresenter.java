package com.darrellii.gw2.gw2apiexperment.app.guilds;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;

/**
 * Created by dj on 3/24/16.
 * For Use By GW2 API Experment
 */
public class GuildsListPresenter implements GuildsContract.UserActionListener {

    private final GuildsContract.View mView;

    public GuildsListPresenter(GuildsContract.View view){
        mView = view;
    }

    @Override
    public void onGuildLoaded(GuildInfo info) {
        mView.addGuild(info);
    }

    @Override
    public void onGuildClicked(GuildInfo info) {
        mView.openGuild(info);
    }
}
