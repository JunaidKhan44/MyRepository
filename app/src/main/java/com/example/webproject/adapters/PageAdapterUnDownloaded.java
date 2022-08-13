package com.example.webproject.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.webproject.fragments.UnDownloadImage;
import com.example.webproject.fragments.UnDownloadVideo;

public class PageAdapterUnDownloaded extends FragmentPagerAdapter {


    private int numOfTabs;

    public PageAdapterUnDownloaded(@NonNull FragmentManager fm, int behavior, int numOfTabs) {
        super(fm, behavior);
        this.numOfTabs = numOfTabs;
    }

    public PageAdapterUnDownloaded(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UnDownloadImage();

            case 1:
                return new UnDownloadVideo();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}