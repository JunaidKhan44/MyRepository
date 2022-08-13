package com.example.webproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.example.webproject.adapters.PageAdapterUnDownloaded;
import com.example.webproject.R;
import com.example.webproject.base.CommonUtils;
import com.google.android.material.tabs.TabLayout;

public class ImageAndVideo extends AppCompatActivity  implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapterUnDownloaded pageAdapter1;
    private ImageView goback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageandvideo);

        //initialize
        initializeView();
        setAdapterViewPager();
        goback.setOnClickListener(this::onClick);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 1) {

                    tabLayout.setBackgroundColor(ContextCompat.getColor(ImageAndVideo.this,
                            R.color.Teal));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(ImageAndVideo.this,
                                R.color.teal_200));
                    }

                } else {

                    tabLayout.setBackgroundColor(ContextCompat.getColor(ImageAndVideo.this,
                            R.color.Teal));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(ImageAndVideo.this,
                                R.color.teal_200));
                    }

                    }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }
        });

    }

    private void setAdapterViewPager() {

        pageAdapter1 = new PageAdapterUnDownloaded(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mdirect:
                startActivity(new Intent(getApplicationContext(), DirectChat.class));
                return true;
            case R.id.mstatus:
                return true;
            case R.id.mscanner:
                startActivity(new Intent(getApplicationContext(), Scanner.class));
                return true;
            case R.id.exit:
                CommonUtils.showDialog(getApplicationContext());
                return true;

        }
        return false;

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
        finish();

    }



    private void initializeView(){
        tabLayout = findViewById(R.id.tablayoutdownloadstatus);
        viewPager = findViewById(R.id.viewPager1);
        goback = findViewById(R.id.goback);
    }

    @Override
    public void onClick(View view) {
        OnViewClick(view);
    }

    public void OnViewClick(View v){
       if(v==goback) {
        startActivity(new Intent(ImageAndVideo.this, HomeScreen.class));
        finish();
    }

    }
}