package com.darrellii.gw2.gw2apiexperment.app.wallet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.network.GW2Client;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.Currency;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends Fragment implements WalletContract.View{

    RecyclerView mRecyclerView;
    WalletAdapter mAdapter;
    private final WalletContract.UserActionsListener mActionListener = new WalletPresenter(this);

    public WalletFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new WalletAdapter(new ArrayList<Currency>(0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager( new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        GW2Client.getWallet(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Response<List<Currency>> response, Retrofit retrofit) {
                mActionListener.onReceivedData(response.body());
            }
            @Override
            public void onFailure(Throwable t) {}
        });

        return view;
    }

    @Override
    public void setAdapter(List<Currency> wallet) {
        mAdapter.replaceData(wallet);
        mAdapter.notifyDataSetChanged();
    }
}
