package logoin.example.com.myapplication.Models;

/**
 * Created by Google       Company on 24/03/2017.
 */

public class AdvertItem  {
    String txt;
    String img;

    public AdvertItem( String img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    public String getImg() {
        return img;
    }



    public String getTxt() {
        return txt;
    }
}
