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

import com.example.webproject.adapters.PageAdapterDownloaded;
import com.example.webproject.R;
import com.example.webproject.base.CommonUtils;
import com.google.android.material.tabs.TabLayout;

public class ViewStatus extends AppCompatActivity implements View.OnClickListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PageAdapterDownloaded pageAdapter0;
    private ImageView  goback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);


            initializeView();
            setAdapterViewPager();
            goback.setOnClickListener(this::onClick);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                try {
                    viewPager.setCurrentItem(tab.getPosition());
                    if (tab.getPosition() == 1) {

                        tabLayout.setBackgroundColor(ContextCompat.getColor(ViewStatus.this,
                                R.color.Teal));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(ViewStatus.this,
                                    R.color.teal_200));
                        }

                    } else {

                        tabLayout.setBackgroundColor(ContextCompat.getColor(ViewStatus.this,
                                R.color.Teal));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor( ViewStatus.this,
                                    R.color.teal_200));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
        pageAdapter0 = new PageAdapterDownloaded(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter0);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void initializeView() {
        //initialize
        tabLayout = findViewById(R.id.tablayoutdedication);
        viewPager = findViewById(R.id.viewPager2);
        goback=findViewById(R.id.goback);

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
                startActivity(new Intent(getApplicationContext(), ImageAndVideo.class));
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

    @Override
    public void onClick(View view) {
        onViewClick(view);
    }

    private void onViewClick(View view) {

        if(view==goback){
        startActivity(new Intent(ViewStatus.this, HomeScreen.class));
        finish();
        }
    }
}