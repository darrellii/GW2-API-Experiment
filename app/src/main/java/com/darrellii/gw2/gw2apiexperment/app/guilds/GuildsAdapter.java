package com.darrellii.gw2.gw2apiexperment.app.guilds;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.darrellii.gw2.gw2apiexperment.R;
import com.darrellii.gw2.gw2apiexperment.network.models.responses.GuildInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dj on 3/23/16.
 * For Use By GW2 API Experment
 */
public class GuildsAdapter extends RecyclerView.Adapter<GuildsAdapter.GuildHolder> {



    private OnItemClickedListener mListener;
    private List<GuildInfo> mGuilds;

    GuildsAdapter(OnItemClickedListener listener) {
        this.mGuilds = new ArrayList<>(10);
        mListener = listener;
    }


    @Override
    public GuildHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GuildHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_guild, parent, false));
    }

    @Override
    public void onBindViewHolder( GuildHolder holder,  int position) {
        final GuildInfo info = mGuilds.get(position);
        holder.tag.setText(info.tag);
        holder.name.setText(info.guild_name);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(info);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGuilds.size();
    }
    public void addGuild(GuildInfo guild) {
        this.mGuilds.add(guild);
        notifyDataSetChanged();
    }


    class GuildHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView tag;
        public ImageView emblem;
        public View parent;

        public GuildHolder(View v) {
            super(v);
            parent = v;
            name = (TextView) v.findViewById(R.id.name);
            tag = (TextView) v.findViewById(R.id.tag);
            emblem = (ImageView) v.findViewById(R.id.emblem);
        }
    }

    interface OnItemClickedListener {
        void onItemClicked(GuildInfo guildInfo);
    }
}


