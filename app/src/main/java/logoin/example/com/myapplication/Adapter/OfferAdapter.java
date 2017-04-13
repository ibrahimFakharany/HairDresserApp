package logoin.example.com.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import logoin.example.com.myapplication.Models.OfferItem;
import logoin.example.com.myapplication.DetailActivities.OfferDescritption;
import logoin.example.com.myapplication.R;

/**
 * Created by Google       Company on 25/03/2017.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.AdverListRowHolder> {
    List<OfferItem> data;
    Context c;

    String ip;

    public OfferAdapter(List<OfferItem> data, Context c) {
        this.data = data;
        this.c = c;
        System.out.println("offer Adapter");
        ip = c.getString(R.string.ip);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public OfferAdapter.AdverListRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_row, null);
        OfferAdapter.AdverListRowHolder ALRH = new OfferAdapter.AdverListRowHolder(v);
        return ALRH;
    }

    @Override
    public void onBindViewHolder(final OfferAdapter.AdverListRowHolder holder, int position) {
        Picasso.with(c)
                .load("http://"+ip+"/BarbieBeautyWebService/offferImages/" + data.get(position).getImageView())
                .resize(200,400)
                .into(holder.imgOffer);
        final int y = position;
        holder.rate.setText(data.get(position).getRate());
        System.out.println("rate from adapter"+data.get(position).getRate());
        System.out.println("text from adapter"+data.get(position).getText());
        holder.frameOfferImg.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =  new Intent(c, OfferDescritption.class);
                        intent.putExtra("details", data.get(y).getText());
                        intent.putExtra("image", data.get(y).getImageView());
                        c.startActivity(intent);
                    }
                }
        );
    }

    public class AdverListRowHolder extends RecyclerView.ViewHolder {
        ImageView imgOffer;
        TextView rate;
        FrameLayout frameOfferImg;

        public AdverListRowHolder(View itemView) {
            super(itemView);

            this.imgOffer = (ImageView) itemView.findViewById(R.id.img_offer);
            this.rate = (TextView) itemView.findViewById(R.id.txt_offer);
            this.frameOfferImg = (FrameLayout) itemView.findViewById(R.id.frameOfferImg);
        }

    }
}