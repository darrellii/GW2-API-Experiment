package com.darrellii.gw2.gw2apiexperment.app.wallet;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.Currency;

import java.util.List;

/**
 * Created by dj on 3/8/16.
 * For Use By GW2 API Experiment
 */
public interface WalletContract {

    interface View {
        void setAdapter(List<Currency> wallet);
    }

    interface UserActionsListener {
        void onReceivedData(List<Currency> wallet);
    }


}
