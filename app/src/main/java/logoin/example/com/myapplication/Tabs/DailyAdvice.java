package logoin.example.com.myapplication.Tabs;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import logoin.example.com.myapplication.Models.AdvertItem;
import logoin.example.com.myapplication.Adapter.CustomAdapter;
import logoin.example.com.myapplication.R;
import logoin.example.com.myapplication.Other.Singleton;

/**
 * Created by Google       Company on 24/03/2017.
 */

public class DailyAdvice extends android.support.v4.app.Fragment {
    ArrayList<AdvertItem>data_poster;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.daily_advice, container, false);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.daily_advice_recycle);
        String ip = getContext().getString(R.string.ip);
        String url = "http://"+ip+"/BarbieBeautyWebService/retrieveAllTopics.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            System.out.print(" on 0Response method");
                            if (response.equals(null)){
                                System.out.print("Response is null");

                            }



                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            JSONObject jsonObject1 = null;
                            data_poster=new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject1 =jsonArray.getJSONObject(i);
                                String img=jsonObject1.getString("img");
                                String text=jsonObject1.getString("txt");
                                data_poster.add(new AdvertItem(img,text));
                            }

                            for(int i = 0; i < data_poster.size(); i++){
                                System.out.println( "  txt  "  +data_poster.get(i).getTxt() + "  img "  +data_poster.get(i).getImg());
                            }
                            CustomAdapter customAdapter=new CustomAdapter(data_poster,getContext(), "نصائح يومية");
                            recyclerView.setAdapter(customAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        }
                        catch (Exception e){

                        }

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
                map.put("topicNum", Integer.toString(1));
                return map;
            }
        };
        Singleton.getInstance(getContext()).addRequestQue(stringRequest);
        return rootView;

    }
}

