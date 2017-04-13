package logoin.example.com.myapplication.Firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by 450 G1 on 01/04/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private final static String reg_token = "reg_token";


    @Override
    public void onTokenRefresh() {
        String reg = FirebaseInstanceId.getInstance().getToken();
        System.out.println("reg  "+reg);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString(reg_token, reg);
        editor.commit();
        Log.d(reg_token, reg);
    }
}
