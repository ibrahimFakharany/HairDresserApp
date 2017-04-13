package logoin.example.com.myapplication.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import logoin.example.com.myapplication.Other.OnClickListener;
import logoin.example.com.myapplication.R;

/**
 * Created by 450 G1 on 03/04/2017.
 */

public class offerHeaderAdapter extends RecyclerView.Adapter<offerHeaderAdapter.OfferHeaderHolder> {
    List<String> btnNames;
    Context context;
    OnClickListener listener ;
    public offerHeaderAdapter(List<String> names, Context con, OnClickListener listener) {
        this.btnNames = names;
        this.context = con;
        this.listener = listener;
    }

    @Override
    public OfferHeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_offers_btns, null);
        OfferHeaderHolder ALRH = new OfferHeaderHolder(v);
        return ALRH;
    }

    @Override
    public void onBindViewHolder(OfferHeaderHolder holder, int position) {
        holder.button.setText(btnNames.get(position));
        holder.bind(listener, position);
    }

    @Override
    public int getItemCount() {
        return btnNames.size();
    }

    public class OfferHeaderHolder extends RecyclerView.ViewHolder {
        Button button;

        public OfferHeaderHolder(View itemView) {
            super(itemView);
            this.button = (Button) itemView.findViewById(R.id.btn_offer);
        }
        public void bind(final OnClickListener listener, final int position)
        {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(position);
                }
            });
        }
    }

}
