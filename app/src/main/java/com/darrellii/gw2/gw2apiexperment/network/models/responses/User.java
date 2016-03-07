package com.darrellii.gw2.gw2apiexperment.network.models.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dj on 3/6/16.
 * For Use By GW2 API Experment
 */
public class User extends GW2Response {

    public String id;
    @SerializedName("name")
    public String name;
    public int world;
    public boolean commander;
    public String[] guilds;
    public String created;
    public String access;
}
