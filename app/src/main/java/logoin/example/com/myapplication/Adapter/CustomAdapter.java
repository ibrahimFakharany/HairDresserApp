package logoin.example.com.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import logoin.example.com.myapplication.DetailActivities.Descreption;
import logoin.example.com.myapplication.Models.AdvertItem;
import logoin.example.com.myapplication.R;

/**
 * Created by Google       Company on 25/03/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.AdverListRowHolder> {

    ArrayList<AdvertItem> data;
    Context c;
    String caller ;
    String ip ;
    public CustomAdapter(ArrayList<AdvertItem> data, Context c ,String caller) {
        this.data = data;
        this.c = c;
        this.caller = caller;
        this.ip = c.getString(R.string.ip);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public AdverListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_info, null);
        AdverListRowHolder ALRH = new AdverListRowHolder(v);
        return ALRH;

    }

    @Override
    public void onBindViewHolder(final AdverListRowHolder holder, int position) {

        Picasso.with(c).load("http://"+ip+"/BarbieBeautyWebService/uploads/" + data.get(position).getImg())
                .into(holder.imageView);
        holder.txt.setText(data.get(position).getTxt());
        //see more
        if (holder.txt.getLineCount() > 1) {
            holder.seemore.setVisibility(View.GONE);
        } else {
            holder.seemore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(c, Descreption.class);
                    intent.putExtra("txt", holder.txt.getText());
                    intent.putExtra("caller", caller);
                    c.startActivity(intent);
                }
            });

        }

    }

    public class AdverListRowHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        CardView cv;
        TextView txt;
        TextView seemore;

        public AdverListRowHolder(View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.cv);
            this.imageView = (ImageView) itemView.findViewById(R.id.img);
            txt = (TextView) itemView.findViewById(R.id.txt);
            seemore = (TextView) itemView.findViewById(R.id.see);
        }
    }
}