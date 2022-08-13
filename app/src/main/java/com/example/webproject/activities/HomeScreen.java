package com.example.webproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.webproject.R;

public class HomeScreen extends AppCompatActivity  implements View.OnClickListener {



    private CardView dchat, ssaver, qrscan, cmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);



        initializeView();

        Log.d("mytag", "oncreate is called");
        dchat.setOnClickListener(this::onClick);
        ssaver.setOnClickListener(this::onClick);
        qrscan.setOnClickListener(this::onClick);
        cmessage.setOnClickListener(this::onClick);

    }


    private void initializeView(){
        dchat = findViewById(R.id.linearLayout5);
        ssaver = findViewById(R.id.linearLayout7);
        qrscan = findViewById(R.id.linearLayout6);
        cmessage = findViewById(R.id.linearLayout8);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
            Intent s = new Intent(HomeScreen.this, ExitActivity.class);
            startActivity(s);
            finish();
    }

    //main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return true;

    }

    //main menu click
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
                return true;

        }
        return false;

    }

    @Override
    public void onClick(View view) {
        OnViewClick(view);
    }

    public void OnViewClick(View v){
        if(v==dchat){
            startActivity(new Intent(getApplicationContext(), DirectChat.class));
        }
        else if(v==ssaver){
            startActivity(new Intent(getApplicationContext(), ImageAndVideo.class));
        }
        else if(v==qrscan){
            startActivity(new Intent(getApplicationContext(), Scanner.class));
        }
        else if(v==cmessage){
            startActivity(new Intent(getApplicationContext(), ViewStatus.class));
        }
    }

}