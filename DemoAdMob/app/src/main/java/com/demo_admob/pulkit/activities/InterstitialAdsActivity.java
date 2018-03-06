package com.demo_admob.pulkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo_admob.pulkit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class InterstitialAdsActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial_ads);

        findIds();
        init();

    }

    private void findIds() {

    }

    private void init() {

        AdRequest adRequest = new AdRequest.Builder().build();

        /*Prepare the Interestitial Ad*/
        interstitial = new InterstitialAd(InterstitialAdsActivity.this);
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest);

         /*Prepare an Interstitial Ad Listener*/
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                 /*Call displayInterstitial() function*/
                displayInterstitial();
            }
        });
    }

    public void displayInterstitial() {
     /*If Ads are loaded, show Interstitial else show nothing.*/
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }



}
