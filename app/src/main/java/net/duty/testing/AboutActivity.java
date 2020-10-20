package net.duty.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import net.duty.R;

public class AboutActivity extends AppCompatActivity {


    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_about);
        Toast.makeText(getApplicationContext(),Utilities.getStats(),Toast.LENGTH_LONG).show();


        TextView txt = findViewById(R.id.txt);
        Typeface face = Typeface.createFromAsset(getAssets(), "face.ttf");
        txt.setTypeface(face);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void Prev(View view) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdsManager.loadBanner(mAdView);
    }

}
