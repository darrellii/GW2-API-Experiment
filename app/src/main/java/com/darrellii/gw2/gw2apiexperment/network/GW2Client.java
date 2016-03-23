package com.darrellii.gw2.gw2apiexperment.network;

import com.darrellii.gw2.gw2apiexperment.app.GW2Application;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.Currency;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.TokenInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import retrofit.Callback;
import retrofit.Retrofit;
import retrofit.GsonConverterFactory;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class GW2Client {

    private static OkHttpClient mOkHttpClient;
    private static final String HOST = "https://api.guildwars2.com";
    public static GW2Service mService = getServiceInstance();

    //<editor-fold desc="API CALLS">
    public static void verifyAPIKey(String key, Callback<TokenInfo> callback){
        mService.getTokenPerms(key).enqueue(callback);
    }

    public static void getAccount(Callback<User> callback){
        String apikey = GW2Application.getAPIKey();
        mService.getAccount(apikey).enqueue(callback);
    }

    public static void getGuildDetails(String guildId, Callback<GuildInfo> callback){
        mService.getGuildDetails(guildId).enqueue(callback);
    }

    public static void getWallet(Callback<List<Currency>> callback){
        mService.getWallet(GW2Application.getAPIKey())
                .enqueue(callback);
    }


    //</editor-fold>







    private static GW2Service getServiceInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(getOkInstance())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GW2Service.class);
    }
    private static OkHttpClient getOkInstance() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient.interceptors().add(interceptor);
        }
        return mOkHttpClient;
    }
}
