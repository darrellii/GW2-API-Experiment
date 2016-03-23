package com.darrellii.gw2.gw2apiexperment.app.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.app.about.AboutActivity;
import com.darrellii.gw2.gw2apiexperment.app.login.LogInActivity;
import com.darrellii.gw2.gw2apiexperment.app.main.MainContract.Fragments;
import com.darrellii.gw2.gw2apiexperment.app.wallet.WalletFragment;
import com.darrellii.gw2.gw2apiexperment.network.GW2Client;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements MainContract.View {

    private MainContract.UserActionsListener mListener;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListener = new MainPresenter(this);
        loadUserInfo();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_wallet) {
            mListener.onNavWallet();
        } else if (id == R.id.nav_bank) {
            mListener.onNaveBank();
        } else if (id == R.id.nav_characters) {
            mListener.onNavCharacters();
        } else if (id == R.id.nav_about) {
            mListener.onNameAbout();
        } else if (id == R.id.nav_share) {
            mListener.onNavShare();
        } else if (id == R.id.nav_logout) {
            mListener.onNavLogout();
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //<editor-fold desc="View Methods">
    @Override
    public void loadUserInfo() {
        GW2Client.getAccount(new Callback<User>() {
            @Override
            public void onResponse(Response<User> response, Retrofit retrofit) {
                User user = response.body();
                if (user != null)
                    mListener.onUserInfoLoaded(user);
                else
                    mListener.onUserFailedLoad("Failed to Retrieve User");
            }

            @Override
            public void onFailure(Throwable t) {
                mListener.onUserFailedLoad("Failed to Retrieve User");
            }
        });
    }

    @Override
    public void loadGuildInfo(String guildId) {
        GW2Client.getGuildDetails(guildId, new Callback<GuildInfo>() {
            @Override
            public void onResponse(Response<GuildInfo> response, Retrofit retrofit) {
                mListener.onGuildLoaded(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                mListener.onGuildFailedLoad();
            }
        });
    }

    @Override
    public void setUserName(String userName) {
        TextView textView = (TextView) mDrawer.findViewById(R.id.user_name);
        textView.setText(userName);
    }

    @Override
    public void setGuild(@NonNull String guild) {
        TextView textView = (TextView) mDrawer.findViewById(R.id.guilds);
        textView.setText(guild);
    }

    @Override
    public void logout() {
        SharedPreferences.Editor edit =
                getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE).edit();
        edit.remove(getString(R.string.prefs_api_key));
        edit.apply();
        startActivity(new Intent(this, LogInActivity.class));
        finish();
    }

    @Override
    public void showFeatureInProgress() {
        Toast.makeText(this, R.string.feature_in_progress, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFailedToLoad(@NonNull String message) {
        Toast.makeText(this, R.string.failed_to_load_account, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openAbout() {
        startActivity(new Intent(this , AboutActivity.class));
    }


    @Override
    public void changeFragment(Fragments fragment, String... args) {
        Fragment newFragment = null;
        switch (fragment){
            case WALLET:
            newFragment = new WalletFragment();
                break;
        }
        findViewById(R.id.under_construction).setVisibility(View.GONE);
        getSupportActionBar().setTitle(fragment.mTitle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, newFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void createShareIntent() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TITLE, getString(R.string.thanks_sharing));
        // TODO: Update the pre-text
        shareIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_string));
        startActivity(Intent.createChooser(shareIntent, "Share via.."));
    }
    //</editor-fold>
}
