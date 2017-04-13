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

public class HomeIdeaes extends android.support.v4.app.Fragment {
    ArrayList<AdvertItem> data_poster;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        System.out.print("oncreateView methooooood");
        String ip = getContext().getString(R.string.ip);

        View rootView = inflater.inflate(R.layout.home_idea, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.home_idea_recyle);
        String url = "http://" + ip + "/BarbieBeautyWebService/retrieveAllTopics.php?";
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
                                System.out.println("after get object before get Strong    *******************************");
                                String img = jsonObject1.getString("img");
                                System.out.println("after get object before get Strong  2    *******************************");
                                String text = jsonObject1.getString("txt");
                                data_poster.add(new AdvertItem(img, text));
                            }

                            for (int i = 0; i < data_poster.size(); i++) {
                                System.out.println("  txt  " + data_poster.get(i).getTxt() + "  img " + data_poster.get(i).getImg());
                            }
                            CustomAdapter customAdapter = new CustomAdapter(data_poster, getContext(), "افكار منزليه");
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
                map.put("topicNum", Integer.toString(2));
                return map;
            }
        };
        Singleton.getInstance(getContext()).addRequestQue(stringRequest);


        return rootView;
    }




   /* public class AsyncTopic extends AsyncTask<String, String, ArrayList<AdvertItem>> {

        HttpURLConnection httpURLConnection = null;
        URL url = null;
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        String line = "";
        String jsonString;
        String jsonstr = "";
        ArrayList<AdvertItem>data_poster;
        RecyclerView recyclerView;
        @Override
        protected void onPostExecute(ArrayList<AdvertItem> strings) {
            super.onPostExecute(strings);

        }


        @Override
        protected ArrayList<AdvertItem> doInBackground(String... params) {
         String id_topic=params[0];
            try {

                url = new URL("http://localhost:8080/womanandbeauty/retrieveAllTopics.php?");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                inputStream = httpURLConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //       Log.e("buffer",buffer.toString());
                jsonstr = buffer.toString();
                Log.v("html : ", jsonstr);
                jsonString = jsonstr;
                JSONObject jsonObject = new JSONObject(jsonstr);
                JSONArray jsonArray = jsonObject.getJSONArray("message");
                data_poster=new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    String img=jsonObject1.getString("img");
                    Log.v("mmmmahmoud",jsonObject1.getString("img "));
                    String text=jsonObject1.getString("txt");
                    data_poster.add(new AdvertItem(id_topic,img,text));
                    Log.v("json", data_poster.get(i).getTxt());


                }


            } catch (Exception e) {
                //   Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println("mhmoud : " + e.getMessage());

            }

            return data_poster;

        }


    }*/


}

