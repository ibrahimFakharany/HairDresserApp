package logoin.example.com.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import logoin.example.com.myapplication.Adapter.CustomAdapter;
import logoin.example.com.myapplication.Models.AdvertItem;
import logoin.example.com.myapplication.Other.Singleton;
import logoin.example.com.myapplication.Tabs.Contact;
import logoin.example.com.myapplication.Tabs.DailyAdvice;
import logoin.example.com.myapplication.Tabs.HomeIdeaes;
import logoin.example.com.myapplication.Tabs.Misc;
import logoin.example.com.myapplication.Tabs.Offers;
import logoin.example.com.myapplication.Tabs.OurWork;
import logoin.example.com.myapplication.Tabs.WrongHabit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    final static String reg_token = "reg_token";
    String Token;
    ArrayList<AdvertItem> data_poster;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DailyAdvice(), "").commit();

        String ip = getString(R.string.ip);
        String url = new String("http://" + ip + "/BarbieBeautyWebService/firebaseNotification/fcm_insert.php");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Token = sharedPreferences.getString(reg_token, "");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("token_value", Token);
                return map;
            }
        };
        Singleton.getInstance(getApplicationContext()).addRequestQue(stringRequest);

      /*  recyclerView = (RecyclerView) findViewById(R.id.wrong_habit_recycle);
         String url2 = "http://"+ip+"/BarbieBeautyWebService/retrieveAllTopics.php?";
         StringRequest stringRequest2 = new StringRequest(Request.Method.POST, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.print(" on 0Response method");
                            if (response.equals(null)) {
                                System.out.print("Response is null");

                            }


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            JSONObject jsonObject1 = null;
                            data_poster = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject1 = jsonArray.getJSONObject(i);
                                String img = jsonObject1.getString("img");
                                String text = jsonObject1.getString("txt");
                                data_poster.add(new AdvertItem(img, text));
                            }

                            for (int i = 0; i < data_poster.size(); i++) {
                                System.out.println("  txt  " + data_poster.get(i).getTxt() + "  img " + data_poster.get(i).getImg());
                            }
                            CustomAdapter customAdapter = new CustomAdapter(data_poster, getBaseContext(),"منوعات");
                            recyclerView.setAdapter(customAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));

                        } catch (Exception e) {
                            System.out.print("exception in main activity");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("topicNum", Integer.toString(1));
                return map;
            }
        };
        Singleton.getInstance(this).addRequestQue(stringRequest2);*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if (id == R.id.advice) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new DailyAdvice(), "").commit();
            toolbar.setTitle("نصائح يومية");
        } else if (id == R.id.ideas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeIdeaes(), "").commit();
            toolbar.setTitle("افكار منزليه");
        } else if (id == R.id.traditional) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new WrongHabit(), "").commit();
            toolbar.setTitle("عادات خاطئة ابتعدى عنها");
        } else if (id == R.id.shows) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Offers(), "").commit();
            toolbar.setTitle("عروضنا");
        } else if (id == R.id.works) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new OurWork(), "").commit();
            toolbar.setTitle("اعمالنا");
        } else if (id == R.id.Misc) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Misc(), "").commit();
            toolbar.setTitle("منوعات");
        } else if (id == R.id.contact) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new Contact(), "").commit();
            toolbar.setTitle("اتصل بنا");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
