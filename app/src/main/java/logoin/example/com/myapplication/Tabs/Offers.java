package logoin.example.com.myapplication.Tabs;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logoin.example.com.myapplication.Adapter.OfferAdapter;
import logoin.example.com.myapplication.Models.OfferItem;
import logoin.example.com.myapplication.Other.OnClickListener;
import logoin.example.com.myapplication.R;
import logoin.example.com.myapplication.Other.Singleton;
import logoin.example.com.myapplication.Other.Utiles;
import logoin.example.com.myapplication.Adapter.offerHeaderAdapter;

/**
 * Created by Google Company on 24/03/2017.
 */

public class Offers extends android.support.v4.app.Fragment {
    RecyclerView headerRecyclerView;
    List<String> btnsNames;
    RecyclerView bodyRecyclerView;
    OfferAdapter bodyAdapter;
    StringRequest stringRequest;
    String url = "";
    String ip;
    List<OfferItem> item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.offers, container, false);
        ip = getContext().getString(R.string.ip);
        url = "http://" + ip + "/BarbieBeautyWebService/getOffers.php";
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), Utiles.getNoOfColumns(getContext()));

        btnsNames = new ArrayList<>();
        btnsNames.add("عيد الحب");
        btnsNames.add("عيد الفطر");
        btnsNames.add("عيد الاضحى");
        btnsNames.add("عيد الميلاد");
        btnsNames.add("عيد شم النسيم");
        btnsNames.add("عيد الصيف");
        btnsNames.add("عيد الشتاء");
        bodyRecyclerView = (RecyclerView) rootView.findViewById(R.id.offers_recyle_body);
        bodyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        bodyRecyclerView.setLayoutManager(gridLayoutManager);
        // header adapter
        offerHeaderAdapter headerAdapter = new offerHeaderAdapter(btnsNames, getContext(), new OnClickListener() {
            @Override
            public void onClick(final int position) {
                // volley to get offers
                stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        item = new ArrayList<>();
                        try{
                            JSONObject root = new JSONObject(response);
                            int state = root.getInt("success");
                            if (state == 1) {
                                System.out.println("success = 1");
                                JSONArray array = root.getJSONArray("message");
                                JSONObject jsonObjectI = null;
                                for (int i = 0; i < array.length();i++){
                                    jsonObjectI = array.getJSONObject(i);
                                    item.add(new OfferItem(
                                            jsonObjectI.getString("txt"),
                                            jsonObjectI.getString("rate"),
                                            jsonObjectI.getString("img")));
                                }
                                for (int i = 0 ; i < item.size(); i++){
                                    System.out.println("image   "+item.get(i).getImageView());
                                    System.out.println("rate    "+item.get(i).getText());
                                    System.out.println("txt     "+item.get(i).getRate());
                                }
                               bodyAdapter = new OfferAdapter(item, getContext());
                               bodyRecyclerView.setAdapter(bodyAdapter);
                                if(bodyRecyclerView.getVisibility()== View.GONE){
                                    bodyRecyclerView.setVisibility(View.VISIBLE);
                                }

                            } else {
                                System.out.println("success = zero");
                                bodyAdapter = new OfferAdapter(item, getContext());
                                bodyRecyclerView.setAdapter(bodyAdapter);
                                bodyRecyclerView.setVisibility(View.GONE);
                            }

                        }catch (JSONException ex){}

                    }
                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("topicNum", Integer.toString(position));
                        return map;
                    }
                };
                Singleton.getInstance(getContext()).addRequestQue(stringRequest);



            }
        });
        headerRecyclerView = (RecyclerView) rootView.findViewById(R.id.offers_recyle_btns);
        headerRecyclerView.setAdapter(headerAdapter);
        headerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));

        // body adapter


        return rootView;
    }


}



/*recyclerView = (RecyclerView) rootView.findViewById(R.id.wrong_habit_recycle);



        String url = "http://192.168.1.2/BarbieBeautyWebService/SelectPromotion.php?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            System.out.print(" on 0Response method");
                            if (response.equals(null)) {
                                System.out.print("Response is null");

                            }
                            System.out.print(response + " Response ******************************* ");


                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("message");
                            JSONObject jsonObject1 = null;
                            data_poster = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                System.out.println("json loop line one    *******************************");
                                jsonObject1 = jsonArray.getJSONObject(i);
                                System.out.println("after get object before get Strong  2    *******************************");
                                String text = jsonObject1.getString("txt");
                                data_poster.add(new OfferItem(text));
                            }

                            for (int i = 0; i < data_poster.size(); i++) {
                                System.out.println("  txt  " + data_poster.get(i).getText() );
                            }
                            OfferAdapter customAdapter = new OfferAdapter(data_poster, getContext());
                            recyclerView.setAdapter(customAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                        } catch (Exception e) {
                            System.out.print("exception     *******************************");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("onresponse error   *******************************");
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("topicNum", Integer.toString(3));
                return map;
            }
        };
        Singleton.getInstance(getContext()).addRequestQue(stringRequest);

*/