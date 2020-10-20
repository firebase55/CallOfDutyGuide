package net.duty.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdView;

import net.duty.R;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdsManager.loadBanner(mAdView);

        AdsManager.loadInterstitial(getApplicationContext());

    }

    public void To_about(View view) {
        startActivity(new Intent(MainActivity.this, AboutActivity.class));
        AdsManager.showInterstitial(getApplicationContext());
    }

    public void To_next(View view) {

        switch (view.getId()) {

            case R.id.t1:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 1));
                break;

            case R.id.t2:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 2));
                break;

            case R.id.t3:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 3));
                break;

            case R.id.t4:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 4));
                break;

            case R.id.t5:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 5));
                break;

            case R.id.t6:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 6));
                break;

            case R.id.t7:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 7));
                break;

            case R.id.t8:
                startActivity(new Intent(MainActivity.this, Next_prev.class)
                        .putExtra("info", 8));
                break;

        }
        AdsManager.showInterstitial(getApplicationContext());

    }


    public void To_rate(View view) {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AdsManager.loadBanner(mAdView);
    }

}
