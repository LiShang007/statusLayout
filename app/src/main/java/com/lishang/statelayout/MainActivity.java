package com.lishang.statelayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {
        startActivity(new Intent(this, GlobalStyleActivity.class));
    }

    public void onClick2(View view) {
        startActivity(new Intent(this, XmlStyleActivity.class));
    }

    public void onClick3(View view) {

        startActivity(new Intent(this, XmlStyle2Activity.class));

    }

    public void onClick4(View view) {
        startActivity(new Intent(this, CustomStyleActivity.class));
    }

    public void onClick5(View view) {
        startActivity(new Intent(this, RelativeLayoutActivity.class));
    }
}
