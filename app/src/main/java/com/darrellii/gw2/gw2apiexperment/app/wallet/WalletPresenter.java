package com.darrellii.gw2.gw2apiexperment.app.wallet;

import com.darrellii.gw2.gw2apiexperment.network.models.responses.Currency;

import java.util.List;

/**
 * Created by dj on 3/8/16.
 * For Use By GW2 API Experment
 */
public class WalletPresenter implements WalletContract.UserActionsListener {
    private WalletContract.View mView;

    public WalletPresenter(WalletContract.View view){
        mView = view;
    }
    @Override
    public void onReceivedData(List<Currency> wallet) {
        mView.setAdapter(wallet);
    }
}
