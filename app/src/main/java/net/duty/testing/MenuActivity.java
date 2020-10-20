package net.duty.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import net.duty.R;

public class MenuActivity extends AppCompatActivity {


    private InterstitialAd mInterstitialAd;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        mAdView = findViewById(R.id.adView);
        AdsManager.loadBanner(mAdView);

        AdsManager.loadInterstitial(getApplicationContext());
    }


    public void Too1(View view) {
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
        AdsManager.showInterstitial(getApplicationContext());
    }

    public void Too2(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    public void Too3(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.privacy_policy_link)));
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdsManager.loadBanner(mAdView);
    }

}
