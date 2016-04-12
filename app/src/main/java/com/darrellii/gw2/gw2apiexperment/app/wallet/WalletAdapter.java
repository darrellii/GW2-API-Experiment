package com.darrellii.gw2.gw2apiexperment.app.wallet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.Currency;

import java.util.List;

/**
 * Created by dj on 3/17/16.
 * For Use By GW2 API Experment
 */
public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletHolder> {

    public void setWallet(List<Currency> wallet) {
        this.mWallet = wallet;

    }

    List<Currency> mWallet;

    WalletAdapter(List<Currency> wallet) {
        this.mWallet = wallet;
    }

    @Override
    public WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WalletHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_currency, parent, false));
    }

    @Override
    public void onBindViewHolder(WalletHolder holder, int position) {
        holder.amount.setText(Integer.toString(mWallet.get(position).value));
        holder.name.setText(mWallet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mWallet.size();
    }

    public void replaceData(List<Currency> wallet) {
        mWallet = wallet;
        notifyDataSetChanged();
    }

    class WalletHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView amount;

        public WalletHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            amount = (TextView) v.findViewById(R.id.amount);
        }
    }
}
