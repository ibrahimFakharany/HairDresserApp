package logoin.example.com.myapplication.DetailActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import logoin.example.com.myapplication.R;

/**
 * Created by 450 G1 on 03/04/2017.
 */

public class OfferDescritption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_description);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String details = intent.getStringExtra("details");
        String imsg = intent.getStringExtra("image");
        TextView detailTxtView = (TextView) findViewById(R.id.detail_offer);
        detailTxtView.setText(details);

        ImageView  img = (ImageView) findViewById(R.id.img_offer_detail);
        String ip= getString(R.string.ip);
        System.out.println("#########image in image string in detail "+imsg);
        Picasso.with(this).load("http://"+ip+"/BarbieBeautyWebService/offferImages/"+imsg).into(img);
        this.setTitle("التفاصيل");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}