package com.jesusmariagarcia.mapas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void onClickLocation(View v) {

        int id = v.getId();
        int nLocation;

        switch(id) {
            case R.id.imgButton1:
                nLocation = 1;
                break;

            case R.id.imgButton2:
                nLocation = 2;
                break;

            case R.id.imgButton3:
                nLocation = 3;
                break;

            case R.id.imgButton4:
                nLocation = 4;
                break;

            default:
                nLocation = 0;
                break;
        }

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("Location", nLocation);
        startActivity(intent);
    }
}
