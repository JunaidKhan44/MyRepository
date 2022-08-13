package com.example.webproject.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.webproject.R;

public class CommonUtils {


    public static void showDialog(Context context) {

        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.alert_dialog, null);
            final AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .setView(view)
                    .create();
            Button yes = view.findViewById(R.id.yes);
            Button no = view.findViewById(R.id.no);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    OnBackPress(context);

                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        } catch (Exception e) {
        }

    }

    private static void OnBackPress(Context context) {

        ((Activity)context).onBackPressed();
        ((Activity)context).finish();
        System.exit(0);
    }


}
