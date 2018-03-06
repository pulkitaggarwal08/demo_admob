package com.demo_admob.pulkit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo_admob.pulkit.activities.BannerAdActivity;
import com.demo_admob.pulkit.activities.InterstitialAdsActivity;
import com.demo_admob.pulkit.activities.NativeBannerAdActivity;
import com.demo_admob.pulkit.activities.WatchVideoAdsActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_banner_ad, btn_interstitial_ads, btn_watch_videos, btn_native_banner_ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIds();
        init();

    }

    private void findIds() {

        btn_banner_ad = findViewById(R.id.btn_banner_ad);
        btn_interstitial_ads = findViewById(R.id.btn_interstitial_ads);
        btn_watch_videos = findViewById(R.id.btn_watch_videos);
        btn_native_banner_ad = findViewById(R.id.btn_native_banner_ad);

    }

    private void init() {

        btn_banner_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BannerAdActivity.class);
                startActivity(intent);
            }
        });

        btn_interstitial_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InterstitialAdsActivity.class);
                startActivity(intent);
            }
        });

        btn_watch_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WatchVideoAdsActivity.class);
                startActivity(intent);
            }
        });

        btn_native_banner_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NativeBannerAdActivity.class);
                startActivity(intent);
            }
        });
    }

}
