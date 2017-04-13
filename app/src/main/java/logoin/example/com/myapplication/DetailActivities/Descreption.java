package logoin.example.com.myapplication.DetailActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import logoin.example.com.myapplication.R;

/**
 * Created by Google       Company on 25/03/2017.
 */

public class Descreption  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descrption_activity);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent=getIntent();
        String data=intent.getExtras().getString("txt");
        String caller = intent.getExtras().getString("caller");
        TextView textView=(TextView)findViewById(R.id.compleettxt);
        textView.setText(data);
        this.setTitle(caller);

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
