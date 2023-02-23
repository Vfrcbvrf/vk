package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    static flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn_mic = (ImageButton) findViewById(R.id.imageButton17);
        btn_mic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)  {

                if (flag == true) {
                    btn_mic.setImageResource(R.drawable.on_micro);
                    flag = false;
                }
                if (flag == false)
                {
                    btn_mic.setImageResource(R.drawable.off_micro);
                    flag = true;
                }
            }
        });
    }
}