package com.darrellii.gw2.gw2apiexperment.network.models.responses;

/**
 * Created by dj on 3/8/16.
 * For Use By GW2 API Experment
 */
public class Currency {
    public int id;
    public int value;


    private final static String[] NAMES = {"Coin","Karma","Laurel","Gem","Ascalonian Tear",
            "Shard of Zhaitan","Fractal Relic","Seal of Beetletun","Manifesto of the Moletariate",
            "Deadly Bloom","Symbol of Koda","Flame Legion Charr Carving","Knowledge Crystal",
            "Badge of Honor","Guild Commendation","Transmutation Charge","Airship Part",
            "Ley Line Crystal","Lump of Aurillium","Spirit Shard","Pristine Fractal Relic",
            "Geode","WvW Tournament Claim Ticket","Bandit Crest","Magnetite Shard",
            "Provisioner Token","PvP League Ticket"};

    public String getName(){
        if(id - 1 < NAMES.length )
        return NAMES[id -1];
        return "I Don't Know yet";
    }

}
