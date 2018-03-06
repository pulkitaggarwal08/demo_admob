package com.demo_admob.pulkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo_admob.pulkit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.formats.NativeAdView;

public class BannerAdActivity extends AppCompatActivity {

    private AdView banner_ad_iew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ad);

        findIds();
        init();

    }

    private void findIds() {

        banner_ad_iew = findViewById(R.id.banner_ad_iew);
    }

    private void init() {

        AdRequest adRequest = new AdRequest.Builder().build();
        banner_ad_iew.loadAd(adRequest);

    }


}
