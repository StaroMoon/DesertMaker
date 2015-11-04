package com.acd21.staromoon.desertmaker.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.acd21.staromoon.desertmaker.R;
import com.acd21.staromoon.desertmaker.busevent.DessertBusEvent;
import com.acd21.staromoon.desertmaker.fragment.MainFragment;
import com.acd21.staromoon.desertmaker.fragment.MoreInfoFragment;
import com.acd21.staromoon.desertmaker.fragment.SummaryFragment;
import com.acd21.staromoon.desertmaker.manager.DessertListManager;
import com.inthecheesefactory.thecheeselibrary.manager.bus.MainBus;
import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance())
                    .commit();

        }
    }

    private void initInstances() {
        toolbar = (Toolbar) findViewById(R.id.tool);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                R.string.hello_world,
                R.string.hello_world);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))       // ทำให้กดปุ่มแล้วเรียก drawer
            return true;

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainBus.getInstance().register(this);   //เชื่อม bus
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainBus.getInstance().unregister(this);         //มีเชื่อมก็ต้องมี ถอด
    }

    @Subscribe
    public void busEventReceived(DessertBusEvent event){        //ต้องประกาศเป็น public void เท่านั้น
        DessertListManager.getInstance().setSelectedDao(event.getDao());

        FrameLayout moreInfoConatainer = (FrameLayout) findViewById(R.id.moreInfoContainer);
        if(moreInfoConatainer == null) {
            // Mobile
            Intent intent = new Intent(MainActivity.this, MoreInfoActivity.class);
            startActivity(intent);
        }else{
            //Tablet
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer, MoreInfoFragment.newInstance())
                    .commit();
        }
    }
}
