package com.demo_admob.pulkit.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo_admob.pulkit.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;

public class NativeBannerAdActivity extends AppCompatActivity {

    private NativeExpressAdView native_banner_ad_iew;
    private VideoController videoController;
    private String TAG = "NativeBannerAdActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_banner_ad);

        findIds();
        init();

    }

    private void findIds() {

        native_banner_ad_iew = findViewById(R.id.native_banner_ad_iew);
    }

    private void init() {

        native_banner_ad_iew.setVideoOptions(new VideoOptions.Builder()
                .setStartMuted(true)
                .build());

        videoController = native_banner_ad_iew.getVideoController();
        videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                Log.i(TAG, "onVideoEnd: Video playback has finished");
                super.onVideoEnd();
            }
        });

        native_banner_ad_iew.setAdListener(new AdListener(){

            @Override
            public void onAdLoaded() {

                if(videoController.hasVideoContent()){
                    Log.i(TAG, "onAdLoaded: Received an ad with a video asset");
                }
                else {
                    Log.i(TAG, "onAdLoaded: Received an ad without a video asset");
                }
                super.onAdLoaded();
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        native_banner_ad_iew.loadAd(adRequest);

    }


}
