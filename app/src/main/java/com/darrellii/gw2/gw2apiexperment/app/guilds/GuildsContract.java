package com.darrellii.gw2.gw2apiexperment.app.guilds;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;

/**
 * Created by dj on 3/23/16.
 * For Use By GW2 API Experment
 */
public interface GuildsContract {

    interface View {
        void loadGuilds(int index);
        void addGuild(GuildInfo info);
        void openGuild(GuildInfo info);
    }

    interface UserActionListener{
        void onGuildLoaded(GuildInfo info);
        void onGuildClicked(GuildInfo info);
    }
}
