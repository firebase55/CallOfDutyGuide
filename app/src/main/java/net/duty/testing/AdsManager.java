package net.duty.testing;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import net.duty.R;


public class AdsManager {
    //Attributes
    static InterstitialAd mInterstitial;
    static RewardedAd rewardedAd;

    static void initAds(Context ctxMain) {
        MobileAds.initialize(ctxMain, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    //Banner
    static void loadBanner(final AdView mAdView) {
        if (Utilities.crCnt < 5) {
            mAdView.loadAd(new AdRequest.Builder().build());
            mAdView.setVisibility(View.VISIBLE);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdOpened() {
                    Utilities.incCrCnt();
                    mAdView.setVisibility(View.GONE);
                }
            });
        }
    }

    static void loadInterstitial(final Context context) {
        if (Utilities.crCnt < 5) {
            mInterstitial = new InterstitialAd(context);
            mInterstitial.setAdUnitId(context.getResources().getString(R.string.m_interstitial));
            mInterstitial.loadAd(new AdRequest.Builder().build());
            mInterstitial.setAdListener(new AdListener() {

                @Override
                public void onAdClicked() {
                    Utilities.incCrCnt();
                }
            });
        }

    }

    static void showInterstitial(Context context) {
        if (Utilities.crCnt < 5 && mInterstitial.isLoaded()) {
            mInterstitial.show();
            loadInterstitial(context);
        }


    }

    static void loadVideoReward(Context context) {
        if(Utilities.crCnt < 5){
            rewardedAd = new RewardedAd(context, context.getString(R.string.m_rewarded));
            RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
                @Override
                public void onRewardedAdLoaded() {
                    // Ad successfully loaded.
                }

                @Override
                public void onRewardedAdFailedToLoad(int errorCode) {
                    // Ad failed to load.
                }
            };
            rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
        }

    }

    static void showVideoReward(Activity activity) {
        if (rewardedAd.isLoaded() && Utilities.crCnt < 5) {
            RewardedAdCallback adCallback = new RewardedAdCallback() {
                @Override
                public void onRewardedAdOpened() {
                    Utilities.incCrCnt();
                }

                @Override
                public void onRewardedAdClosed() {
                    // Ad closed.
                }

                @Override
                public void onUserEarnedReward(@NonNull RewardItem reward) {
                    // User earned reward.
                }

                @Override
                public void onRewardedAdFailedToShow(int errorCode) {
                    // Ad failed to display.
                }
            };
            rewardedAd.show(activity, adCallback);

        }

    }

}