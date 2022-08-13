package com.example.webproject.others;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.webproject.R;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class Advertise {
    public static InterstitialAd mInterstitialAd;
    public static String TAG = "FBAds";
    static AdView adView;
    public static boolean condition = false;
    public static boolean nativecondition = false;
    public static NativeBannerAd mNativeBannerAd;
    public static NativeAd nativeAd;

    public static void IntertitialAd(final Activity activity){
        try{
            AudienceNetworkAds.initialize(activity);
            mInterstitialAd = new InterstitialAd(activity, activity.getString(R.string.FbInterstitialAd));
            mInterstitialAd.loadAd();
            mInterstitialAd.setAdListener(new InterstitialAdListener(){
                @Override
                public void onInterstitialDisplayed(Ad ad) {}
                @Override
                public void onInterstitialDismissed(Ad ad) {
                    mInterstitialAd.loadAd();
                }
                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.d("Interstitialad", "onAdLoaded: FB Interstitial with error "+adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    Log.d("Interstitialad", "onAdLoaded: FB Interstitial");
                }
                @Override
                public void onAdClicked(Ad ad) { }
                @Override
                public void onLoggingImpression(Ad ad) { }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public static void loadbanner(final Activity activity) {
        try {
            mNativeBannerAd = new NativeBannerAd(activity, activity.getString(R.string.FbBannerAd));
            mNativeBannerAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.d("bannerad", "onAdLoaded: FB Banner with code " + adError.getErrorMessage());
                    return;
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    try {
                        Log.d("bannerad", "onAdLoaded: FB Banner");
                        condition = true;
                        View adView = NativeBannerAdView.render(activity, mNativeBannerAd, NativeBannerAdView.Type.HEIGHT_50);
                        LinearLayout nativeBannerAdContainer = (LinearLayout) activity.findViewById(R.id.banner_container);
                        nativeBannerAdContainer.addView(adView);
                    } catch (Exception e) {
                        Log.d(TAG, "onAdLoaded: " + e.toString());
                    }
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            });
            mNativeBannerAd.loadAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadNativeAd(final Activity activity) {
        try {
            AudienceNetworkAds.initialize(activity);
            nativeAd = new NativeAd(activity, activity.getString(R.string.FbNativeAd));
            nativeAd.setAdListener(new NativeAdListener() {

                @Override
                public void onMediaDownloaded(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {
                    nativecondition = true;
                    //Toast.makeText(activity, "load", Toast.LENGTH_SHORT).show();
                    Log.d("nativead", "onAdLoaded: FB Native ad");
                    if (nativeAd != ad) {
                        nativeAd.unregisterView();
                    }
                    NativeAdLayout nativeAdLayout = activity.findViewById(R.id.native_ad_container);
                    //   nativeAdLayout.setVisibility(View.VISIBLE);
                    CardView adView = (CardView) LayoutInflater.from(activity).inflate(R.layout.fb_adlayout, nativeAdLayout, false);
                    nativeAdLayout.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                    LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
                    AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);
                    inflateAd(nativeAd, adView, adChoicesContainer, adOptionsView);
                }

                @Override
                public void onAdClicked(Ad ad) {
                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            });

            nativeAd.loadAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //smalll  adv
    public static void loadNativeAd(final Activity activity, final Dialog dialog) {
        try {
            AudienceNetworkAds.initialize(activity);
            nativeAd = new NativeAd(activity, activity.getString(R.string.FbNativeAd));
            nativeAd.setAdListener(new NativeAdListener() {
                @Override
                public void onMediaDownloaded(Ad ad) {
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                    Log.e("nativead", "onError: FB Native with message " + adError.getErrorMessage());
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    nativecondition = true;
                    Log.d("nativead", "onAdLoaded: FB Native ad");
                    if (nativeAd != ad) {
                        nativeAd.unregisterView();
                    }
                    NativeAdLayout nativeAdLayout = activity.findViewById(R.id.native_small);
                    nativeAdLayout.setVisibility(View.VISIBLE);
                    CardView adView = (CardView) LayoutInflater.from(activity).inflate(R.layout.smalladlayout, nativeAdLayout, false);
                    nativeAdLayout.addView(adView, new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
                    LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choic_container);
                    AdOptionsView adOptionsView = new AdOptionsView(activity, nativeAd, nativeAdLayout);

                    inflateAdsmall(nativeAd, adView, adChoicesContainer, adOptionsView);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {
                }
            });
            nativeAd.loadAd();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
    public static void inflateAd(NativeAd nativeAd, CardView adView, LinearLayout adChoicesContainer, AdOptionsView adOptionsView) {
        try {
            nativeAd.unregisterView();

            // Add the Ad view into the ad container.

            // Inflate the Ad view.  The layout referenced should be the one you created in the last step.

            // Add the AdOptionsView
            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            // Create native UI using the ad metadata.
            AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
            com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

            // Set the Text.
            nativeAdTitle.setText(nativeAd.getAdvertiserName());
            nativeAdBody.setText(nativeAd.getAdBodyText());
            nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

            // Create a list of clickable views
            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            // Register the Title and CTA button to listen for clicks.
            nativeAd.registerViewForInteraction(
                    adView,
                    nativeAdMedia,
                    nativeAdIcon,
                    clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//
//
    public static void inflateAdsmall(NativeAd nativeAd, CardView adView, LinearLayout adChoicesContainer, AdOptionsView adOptionsView) {
        try {
            nativeAd.unregisterView();

            adChoicesContainer.removeAllViews();
            adChoicesContainer.addView(adOptionsView, 0);

            // Create native UI using the ad metadata.
            AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_ic);
            TextView nativeAdTitle = adView.findViewById(R.id.native_ad_ti);
            com.facebook.ads.MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_med);
            TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_cont);
            TextView nativeAdBody = adView.findViewById(R.id.native_ad_bo);
            TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_lab);
            Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_act);

            // Set the Text.
            nativeAdTitle.setText(nativeAd.getAdvertiserName());
            nativeAdBody.setText(nativeAd.getAdBodyText());
            nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
            nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
            nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
            sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

            List<View> clickableViews = new ArrayList<>();
            clickableViews.add(nativeAdTitle);
            clickableViews.add(nativeAdCallToAction);

            nativeAd.registerViewForInteraction(
                    adView,
                    nativeAdMedia,
                    nativeAdIcon,
                    clickableViews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
