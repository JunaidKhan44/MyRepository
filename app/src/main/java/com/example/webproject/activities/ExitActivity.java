package com.example.webproject.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.webproject.R;

public class ExitActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnYes, btnNo;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        initializeView();
        btnYes.setOnClickListener(this::onClick);
        btnNo.setOnClickListener(this::onClick);

        }


    @Override
    public void onBackPressed() {
        Intent s = new Intent(ExitActivity.this, HomeScreen.class);
        startActivity(s);
        finish();
    }

    void initializeView(){
       progressBar=findViewById(R.id.prograssbar);
        btnYes=findViewById(R.id.yes);
        btnNo=findViewById(R.id.no);



    }

    @Override
    public void onClick(View view) {

        if(view==btnYes){
            finish();
            System.exit(0);
        }else{
            Intent s = new Intent(ExitActivity.this, HomeScreen.class);
            startActivity(s);
            finish();
        }
    }
}