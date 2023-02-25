package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageButton micro;
    ImageButton camera;
    ImageButton exit;

    boolean micrOn = true;
    boolean camer = true;

    final int DIALOG_EXIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        micro= (ImageButton)findViewById(R.id.micro);
        camera = (ImageButton)findViewById(R.id.camera);
        exit = (ImageButton)findViewById(R.id.exit);





    }


    public void mcr(View v) { // кнопка микрофона
        if (!micrOn) micro.setImageResource(R.drawable.ic_baseline_mic_24);
        else micro.setImageResource(R.drawable.ic_baseline_mic_off_24);
        micrOn = !micrOn;
    }

    public void cmr(View v) { // кнопка камеры
        if (!camer) camera.setImageResource(R.drawable.ic_baseline_videocam_24);
        else camera.setImageResource(R.drawable.ic_baseline_videocam_off_24);
        camer = !camer;
    }

    public void hand(View v) { // кнопка диалога
        showDialog(DIALOG_EXIT);
    }

    public void finish(View v) { // кнопка выхода
        finish();
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