package net.duty.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import net.duty.R;

public class Next_prev extends AppCompatActivity {

    private TextView text;
    private ImageView cover, nn;
    private String[] txt;

    private int i;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.next_prev);
        mAdView = findViewById(R.id.adView);
        AdsManager.loadBanner(mAdView);
        AdsManager.loadInterstitial(getApplicationContext());
        AdsManager.loadVideoReward(getApplicationContext());

        Typeface face = Typeface.createFromAsset(getAssets(), "face.ttf");

        text = findViewById(R.id.txt);
        text.setMovementMethod(new ScrollingMovementMethod());
        text.setTypeface(face);
        cover = findViewById(R.id.cover);
        nn = findViewById(R.id.nn);

    }

    @Override
    protected void onStart() {
        super.onStart();

        i = 0;

        int ss = getIntent().getIntExtra("info", 1);

        if (ss == 1) {

            txt = getResources().getStringArray(R.array.cover_1);
            cover.setImageResource(R.drawable.callofduty);

        } else if (ss == 2) {

            txt = getResources().getStringArray(R.array.cover_2);
            cover.setImageResource(R.drawable.call3);

        } else if (ss == 3) {

            txt = getResources().getStringArray(R.array.cover_3);
            cover.setImageResource(R.drawable.call5);

        } else if (ss == 4) {

            txt = getResources().getStringArray(R.array.cover_4);
            cover.setImageResource(R.drawable.callof4);

        } else if (ss == 5) {

            txt = getResources().getStringArray(R.array.cover_5);
            cover.setImageResource(R.drawable.callofduty2);

        } else if (ss == 6) {

            txt = getResources().getStringArray(R.array.cover_6);
            cover.setImageResource(R.drawable.callof6);

        } else if (ss == 7) {

            txt = getResources().getStringArray(R.array.cover_7);
            cover.setImageResource(R.drawable.callofduty);

        } else if (ss == 8) {

            txt = getResources().getStringArray(R.array.cover_8);
            cover.setImageResource(R.drawable.call3);

        }

        text.setText(txt[0]);


    }

    public void Next(View view) {
        i++;
        if (i == txt.length - 1) { //3
            nn.setVisibility(View.INVISIBLE);
            nn.setEnabled(false);
            try {
                AdsManager.showVideoReward(this);
            }
            catch(Exception ex){
                Log.e("Error","");
            }

        }

        if (i == 1) {
            AdsManager.showInterstitial(getApplicationContext());
        }

        text.setText(txt[i]);

    }

    public void Prev(View view) {

        i--;

        if (i == -1) {
            i = txt.length - 1;
            onBackPressed();
        } else {
            text.setText(txt[i]);


            if (i == 2) {
                nn.setVisibility(View.VISIBLE);
                nn.setEnabled(true);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        AdsManager.loadBanner(mAdView);
    }
}
