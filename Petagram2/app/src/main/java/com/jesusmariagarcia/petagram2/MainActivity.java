package com.jesusmariagarcia.petagram2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.jesusmariagarcia.petagram2.adapter.MascotaAdapter;
import com.jesusmariagarcia.petagram2.adapter.PageAdapter;
import com.jesusmariagarcia.petagram2.fragment.ListaMascotasFragment;
import com.jesusmariagarcia.petagram2.fragment.PerfilMascotaFragment;
import com.jesusmariagarcia.petagram2.menu.AboutActivity;
import com.jesusmariagarcia.petagram2.menu.ConfigActivity;
import com.jesusmariagarcia.petagram2.menu.DevContactActivity;
import com.jesusmariagarcia.petagram2.pojo.Mascota;
import com.jesusmariagarcia.petagram2.restApi.EndpointsApi;
import com.jesusmariagarcia.petagram2.restApi.adapter.RestApiAdapter;
import com.jesusmariagarcia.petagram2.restApi.model.InstaUserResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String instaUser = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*borrar este toolbar?*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        Toolbar actionBar = (Toolbar) findViewById(R.id.actionBar);

        if(actionBar != null)
            setSupportActionBar(actionBar);

        setUpViewPager();

        if(getIntent().getAction().equals("OPEN_TAB_1"))
            tabLayout.getTabAt(1).select();

    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {

            case R.id.mAbout:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                break;

            case R.id.mContact:
                Intent contactIntent = new Intent(this, DevContactActivity.class);
                startActivity(contactIntent);
                break;

            case R.id.mConfig:
                Intent configIntent = new Intent(this, ConfigActivity.class);
                startActivity(configIntent);
                break;

            case R.id.mNotifications:
                String token = FirebaseInstanceId.getInstance().getToken();

                SharedPreferences misPreferencias = getSharedPreferences("Instagram", Context.MODE_PRIVATE);
                instaUser = misPreferencias.getString("User", "1");

                if(instaUser != "1")
                    enviarUserYToken(token, instaUser);
                else
                    Toast.makeText(this, "Por favor configure la cuenta de instagram", Toast.LENGTH_SHORT).show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void enviarUserYToken(String token, String user) {

        Log.d("TOKEN", token);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<InstaUserResponse> instaUserResponseCall = endpointsApi.registrarUsuario(token, user);

        instaUserResponseCall.enqueue(new Callback<InstaUserResponse>() {
            @Override
            public void onResponse(Call<InstaUserResponse> call, Response<InstaUserResponse> response) {
                InstaUserResponse instaUserResponse = response.body();

                Log.d("ID_FIREBASE", instaUserResponse.getId());
                Log.d("TOKEN_USER", instaUserResponse.getToken());
                Log.d("INSTA_USER", instaUserResponse.getUser());
            }

            @Override
            public void onFailure(Call<InstaUserResponse> call, Throwable t) {

            }
        });
    }

    public void irTop5Mascotas(View v)
    {
        Intent intent = new Intent(this, CincoFavoritas.class);
        startActivity(intent);
    }

    private ArrayList<Fragment> agregarFragments() {

        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new ListaMascotasFragment());
        fragments.add(new PerfilMascotaFragment());

        return fragments;
    }

    public void setUpViewPager() {

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_profile);

    }
}
