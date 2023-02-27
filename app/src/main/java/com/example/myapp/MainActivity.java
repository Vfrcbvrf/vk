package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {


    ImageButton micro, camera, exit;

    ImageButton sms, people, tabs;

    ImageView img1, img2, phon1, phon2, blur1, blur2;

    TextView t1, t2;


    boolean micrOn = true;
    boolean camer = true;
    boolean pages = true;

    final int DIALOG_EXIT = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        micro= (ImageButton)findViewById(R.id.micro);
        camera = (ImageButton)findViewById(R.id.camera);
        exit = (ImageButton)findViewById(R.id.exit);
        sms = (ImageButton)findViewById(R.id.sms);
        people = (ImageButton)findViewById(R.id.people);
        tabs = (ImageButton)findViewById(R.id.tabs);
        img1 = (ImageView)findViewById(R.id.ava1);
        img2 = (ImageView)findViewById(R.id.ava2);
        phon1 = (ImageView)findViewById(R.id.phon1);
        phon2 = (ImageView)findViewById(R.id.phon2);
        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView2);




    }
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon1)).into(phon1);
            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon2)).into(phon2);

        }

    }





    public void mcr(View v) { // кнопка микрофона
        if (!micrOn){
            micro.setImageResource(R.drawable.ic_baseline_mic_24);
            micro.setBackgroundResource(R.drawable.circlebutton);

            if (pages) t1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_24__, 0);
            else t2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_24__, 0);

        }
        else {
            micro.setImageResource(R.drawable.ic_baseline_mic_off_24);
            micro.setBackgroundResource(R.drawable.blackcircle);

            if (pages) t1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
            else t2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
        }


        micrOn = !micrOn;
    }

    public void cmr(View v) { // кнопка камеры
        if (!camer){
            camera.setImageResource(R.drawable.ic_baseline_videocam_24);
            camera.setBackgroundResource(R.drawable.circlebutton);

        }
        else{
            camera.setImageResource(R.drawable.ic_baseline_videocam_off_24);
            camera.setBackgroundResource(R.drawable.blackcircle);
        }
        camer = !camer;
    }

    public void hand(View v) { // кнопка диалога
        showDialog(DIALOG_EXIT);
    }

    public void finish(View v) { // кнопка выхода
        finish();
    }


    @SuppressLint("IntentReset")
    public void mms(View v){ // открытие приложения смс

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.setData(Uri.parse("sms:"));
        startActivity(smsIntent);

    }


    public void peop(View v) {

        Intent pepoIntent = new Intent(Intent.ACTION_DEFAULT, ContactsContract.Contacts.CONTENT_URI);
        startActivity(pepoIntent);
    }

    public void tab(View v){
        if (!pages){
            img1.setImageResource(R.drawable.photo1);
            img2.setImageResource(R.drawable.photo2);

            phon1.setImageResource(R.drawable.me);
            phon2.setImageResource(R.drawable.layer);
            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon1)).into(phon1);
            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon2)).into(phon2);


            t2.setText(R.string.someone);
            t1.setText(R.string.you);


            if (micrOn) t1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_24__, 0);
            else t1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
            t2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
        }
        else {
            img1.setImageResource(R.drawable.photo2);
            img2.setImageResource(R.drawable.photo1);


            phon2.setImageResource(R.drawable.me);
            phon1.setImageResource(R.drawable.layer);

            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon2)).into(phon2);
            Blurry.with(this).sampling(10).capture(findViewById(R.id.phon1)).into(phon1);

            t2.setText(R.string.you);
            t1.setText(R.string.someone);

            if (micrOn) t2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_24__, 0);
            else t2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
            t1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_mic_off_24, 0);
        }
        pages = !pages;
    }


    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.text);
            adb.setPositiveButton(R.string.yes, myClickListener);
            adb.setNegativeButton(R.string.no, myClickListener);
            adb.setNeutralButton(R.string.cancel, myClickListener);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }



    DialogInterface.OnClickListener myClickListener = (dialog, which) -> { // действия кнопок в меню диалога
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                ve();
                break;
            case Dialog.BUTTON_NEGATIVE:
                be();
                break;
            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    };

    void be() {
        Toast.makeText(this, R.string.minus, Toast.LENGTH_SHORT).show();
    }
    void ve() {
        Toast.makeText(this, R.string.plus, Toast.LENGTH_SHORT).show();
    }







}