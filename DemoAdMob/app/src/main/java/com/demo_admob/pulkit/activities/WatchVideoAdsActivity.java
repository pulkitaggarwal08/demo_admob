package com.demo_admob.pulkit.activities;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.demo_admob.pulkit.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class WatchVideoAdsActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private static final String TAG = "WatchVideoAds";

    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/5224354917";
    private static final String APP_ID = "ca-app-pub-3940256099942544~3347511713";

    private RewardedVideoAd mRewardedVideoAd;

    private Button retry_button, ad_watch_video;
    private TextView timer, coin_count_text;

    private static final long COUNTER_TIME = 10;
    private static final int GAME_OVER_REWARD = 1;

    private CountDownTimer mCountDownTimer;

    private int mCoinCount;
    private long mTimeRemaining;
    private boolean mGameOver, mGamePaused;
    private int seconds = 1000;     //10 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video_ads);

        findIds();
        init();

    }

    private void findIds() {

        timer = findViewById(R.id.timer);
        coin_count_text = findViewById(R.id.coin_count_text);

        retry_button = findViewById(R.id.retry_button);
        ad_watch_video = findViewById(R.id.ad_watch_video);

    }

    private void init() {

        MobileAds.initialize(this, APP_ID);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getApplicationContext());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        retry_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

         /*Create the show button, which shows a rewarded video if one is loaded.*/
        ad_watch_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startVideoAd();
            }
        });

        mCoinCount = 0;
        coin_count_text.setText("Coins: " + mCoinCount);

        startGame();
    }

    private void startGame() {

        retry_button.setVisibility(View.INVISIBLE);
        ad_watch_video.setVisibility(View.INVISIBLE);

        loadRewardedVideoAd();
        createTimer(COUNTER_TIME);

        mGamePaused = false;
        mGameOver = false;
    }

    private void createTimer(long counterTime) {

        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimer(counterTime * seconds, 50) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeRemaining = ((millisUntilFinished / 1000) + 1);

                timer.setText("seconds remaining: " + mTimeRemaining);
            }

            @Override
            public void onFinish() {
                if (mRewardedVideoAd.isLoaded()) {
                    ad_watch_video.setVisibility(View.VISIBLE);
                }
                timer.setText("You can play the video now");
                retry_button.setVisibility(View.VISIBLE);
                addCoins(GAME_OVER_REWARD);

                mGameOver = true;
            }
        };
        mCountDownTimer.start();

    }

    private void addCoins(int coins) {
        mCoinCount = mCoinCount + coins;
        coin_count_text.setText("Coins: " + mCoinCount);
    }

    private void loadRewardedVideoAd() {
        if (!mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.loadAd(AD_UNIT_ID, new AdRequest.Builder().build());
        }
    }

    private void startVideoAd() {
        ad_watch_video.setVisibility(View.INVISIBLE);
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {

        Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem reward) {

        Toast.makeText(this, "onRewarded! currency: " + reward.getType() + ", " + reward.getAmount(), Toast.LENGTH_SHORT).show();
        addCoins(reward.getAmount());
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        Toast.makeText(this, "onRewardedVideoAdFailedToLoad", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseGame();
        mRewardedVideoAd.pause(getApplicationContext());
    }

    private void pauseGame() {
        mCountDownTimer.cancel();
        mGamePaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!mGameOver && mGamePaused) {
            resumeGame();
        }
        mRewardedVideoAd.resume(getApplicationContext());
    }

    private void resumeGame() {
        createTimer(mTimeRemaining);
        mGamePaused = false;
    }

    @Override
    protected void onDestroy() {
        mRewardedVideoAd.destroy(getApplicationContext());
        super.onDestroy();
    }


}