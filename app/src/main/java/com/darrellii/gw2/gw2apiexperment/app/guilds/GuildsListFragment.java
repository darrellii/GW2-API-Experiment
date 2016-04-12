package com.darrellii.gw2.gw2apiexperment.app.guilds;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.network.GW2Client;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by dj on 3/23/16.
 * For Use By GW2 API Experment
 */
public class GuildsListFragment extends Fragment implements GuildsContract.View {

    public static final String GUILD_IDS = "guildIds";
    RecyclerView mRecyclerView;
    GuildsAdapter mAdapter;
    private final GuildsContract.UserActionListener mActionListener = new GuildsListPresenter(this);
    private String[] mGuildIds;

    public static GuildsListFragment newInstance(String[] guildIds) {
        GuildsListFragment fragment = new GuildsListFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(GUILD_IDS, guildIds);
        fragment.setArguments(bundle);
        return fragment;

    }

    public GuildsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuildIds = getArguments().getStringArray(GUILD_IDS);
        loadGuilds(0);
        mAdapter = new GuildsAdapter(new GuildsAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(GuildInfo guildInfo) {
                //TODO: Soon will launch an activity showing all available info for guild.
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }


    @Override
    public void loadGuilds(final int index) {

        //switching to a recursive call.
        if(index < mGuildIds.length){
            if(isDetached())
                return;
            GW2Client.getGuildDetails(mGuildIds[index], new Callback<GuildInfo>() {
                @Override
                public void onResponse(Response<GuildInfo> response, Retrofit retrofit) {
                    mActionListener.onGuildLoaded(response.body());
                    loadGuilds(index + 1);
                }
                @Override
                public void onFailure(Throwable t) {}
            });
        }
    }

    @Override
    public void addGuild(GuildInfo info) {
        //should technically be called thread safe and still attached.
        //but better safe.
        if (!isDetached())
            mAdapter.addGuild(info);
    }

    @Override
    public void openGuild(GuildInfo info) {
        Toast.makeText(getActivity(), R.string.feature_in_progress, Toast.LENGTH_LONG).show();
    }
}
