package net.duty.testing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import net.duty.R;


public class SplashActivity extends AppCompatActivity {

    private ProgressBar prgSplash;
    private ImageView btn_act;
    private int x;

    private TextView txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApp();
        setContentView(R.layout.activity_splash);

        Utilities.shdPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);

        initConnect();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        AdsManager.initAds(getApplicationContext());

        prgSplash = findViewById(R.id.prg_splash);
        btn_act = findViewById(R.id.btn_act);
        txt = findViewById(R.id.txt);
        Typeface face = Typeface.createFromAsset(getAssets(), "face.ttf");
        txt.setTypeface(face);


        btn_act.setVisibility(View.INVISIBLE);

        btn_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prgSplash.setVisibility(View.VISIBLE);
                txt.setVisibility(View.GONE);
                btn_act.setVisibility(View.GONE);
                initConnect();
            }
        });

    }

    private void initConnect() {

        StringRequest strRequest = new StringRequest(Request.Method.GET, "https://api.ipify.org/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                prgSplash.setVisibility(View.GONE);
                Utilities.crAdr = response;
                Utilities.setStats();
                startActivity(new Intent(SplashActivity.this, MenuActivity.class));
                finish();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt.setVisibility(View.VISIBLE);
                btn_act.setVisibility(View.VISIBLE);
                prgSplash.setVisibility(View.GONE);

            }
        });

        Singleton.getInstance(getApplicationContext()).addToRequestQueue(strRequest);
    }

    private void initApp() {

        String nm = "";
        Character[] r = {'y', 't', 'u', 'd', '.', 'l', 'l', 'a', 'c'};
        for (int n = r.length - 1; n >= 0; n--) {
            nm += r[n].toString();
        }
        if (getPackageName().compareTo(nm) != 0) {
            String err = null;
            err.getBytes();
        }
    }

}