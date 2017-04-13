package logoin.example.com.myapplication.Models;

import android.widget.ImageView;

/**
 * Created by Google       Company on 25/03/2017.
 */

public class OfferItem {
    String imageView;
    String text;
    String rate;

    public OfferItem(String text, String rate, String imageView) {
        this.text = text;
        this.rate = rate;
        this.imageView = imageView;
    }

    public String getImageView() {
        return imageView;
    }

    public void setImageView(String imageView) {
        this.imageView = imageView;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
