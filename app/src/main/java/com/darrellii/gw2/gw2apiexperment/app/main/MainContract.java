package com.darrellii.gw2.gw2apiexperment.app.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public interface MainContract {

    enum Fragments {
        WALLET("Wallet");




        public String mTitle;
        Fragments(String title){
            mTitle = title;
        }

    }

    interface View extends NavigationView.OnNavigationItemSelectedListener {

        void loadUserInfo();

        void loadGuildInfo(String guildId);

        void setUserName(@NonNull String userName);

        void setGuild(@NonNull String guild);

        void logout();

        void showFeatureInProgress();

        void showFailedToLoad(@NonNull String message);

        void openAbout();

        void changeFragment(Fragments fragment, String... args);

        void createShareIntent();
    }

    interface UserActionsListener  {

        void onUserInfoLoaded(@NonNull User user);

        void onNavWallet();

        void onNavLogout();

        void onNavShare();

        void onNameAbout();

        void onNavCharacters();

        void onNaveBank();

        void onUserFailedLoad(String message);
        
        void onGuildLoaded(GuildInfo guildInfo);

        void onGuildFailedLoad();
    }
}
