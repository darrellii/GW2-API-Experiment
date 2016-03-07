package com.darrellii.gw2.gw2apiexperment.network;

import com.darrellii.gw2.gw2apiexperment.network.models.requests.GuildRequest;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.TokenInfo;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.User;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public interface GW2Service {

    @GET("/v2/account")
    Call<User> getAccount(@Query("access_token") String apiKey);
    @GET("v2/tokeninfo")
    Call<TokenInfo> getTokenPerms(@Query("access_token") String apiKey);
    @GET("v1/guild_details")
    Call<GuildInfo> getGuildDetails(@Query("guild_id") String guildId);
}
