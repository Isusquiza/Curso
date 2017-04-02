package com.jesusmariagarcia.petagram2.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jesusmariagarcia.petagram2.R;

public class ConfigActivity extends AppCompatActivity {

    TextInputEditText ietInstaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }

    public void onClickBtnConfigurarCuenta(View view) {

        ietInstaUser = (TextInputEditText) findViewById(R.id.ietInstaUser);

        String instaUser = ietInstaUser.getText().toString();

        SharedPreferences misPreferencias = getSharedPreferences("Instagram", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = misPreferencias.edit();

        editor.putString("User", instaUser);
        editor.commit();

        ietInstaUser.setText("");

        finish();
    }

}
