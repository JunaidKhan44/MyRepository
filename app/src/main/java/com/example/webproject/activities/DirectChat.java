package com.example.webproject.activities;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.webproject.R;
import com.example.webproject.base.CommonUtils;
import com.example.webproject.base.Constant;
import com.example.webproject.base.CountryData;

public class DirectChat extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText;
    private EditText editTextmessage;
    private Button btnSend;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_chat);

            setTitle(R.string.lblDirectChat);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            initializeView();
            btnSend.setOnClickListener(this::onClick);


    }

    private void initializeView() {
        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        editText = findViewById(R.id.editTextPhone);
        editTextmessage = findViewById(R.id.message);
        btnSend=findViewById(R.id.btnContinue);

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
                Toast.makeText(DirectChat.this, "Direct chat", Toast.LENGTH_SHORT).show();
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
             case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DirectChat.this, HomeScreen.class));
        finish();
    }

    private void onClick(View v) {
        try {
            Log.i("mytag","btn click");
            String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
            String number = editText.getText().toString().trim();
            String mymessage = editTextmessage.getText().toString();
            if (number.isEmpty() || number.length() < 10) {
                editText.setError(getString(R.string.lblErrorMsg));
                editText.requestFocus();
                return;
            }
            if (mymessage.isEmpty()) {
                editTextmessage.setError(getString(R.string.lblErrorMsgEmp));
                editText.requestFocus();
                return;
            }
            String phoneNumber = "+" + code + number;
            String toNumber = phoneNumber;
            toNumber = toNumber.replace("+", "").replace(" ", "");

            Intent sendIntent = new Intent(Constant.MAIN_INTENT);
            sendIntent.putExtra("jid", toNumber + Constant.WHATSAPP_URL1);
            sendIntent.putExtra(Intent.EXTRA_TEXT, mymessage);
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.setPackage(Constant.WHATSAPP_URL2);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
