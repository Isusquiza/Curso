package com.jesusmariagarcia.petagram2;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.jesusmariagarcia.petagram2.adapter.MascotaAdapter;
import com.jesusmariagarcia.petagram2.adapter.PageAdapter;
import com.jesusmariagarcia.petagram2.fragment.ListaMascotasFragment;
import com.jesusmariagarcia.petagram2.fragment.PerfilMascotaFragment;
import com.jesusmariagarcia.petagram2.menu.AboutActivity;
import com.jesusmariagarcia.petagram2.menu.DevContactActivity;
import com.jesusmariagarcia.petagram2.pojo.Mascota;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        }

        return super.onOptionsItemSelected(item);
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
