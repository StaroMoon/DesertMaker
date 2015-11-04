package com.acd21.staromoon.desertmaker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.acd21.staromoon.desertmaker.R;
import com.acd21.staromoon.desertmaker.fragment.MoreInfoFragment;

/**
 * Created by staromoon on 10/30/2015 AD.
 */
public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        initInstace();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contain, MoreInfoFragment.newInstance())
                    .commit();
        }
    }

    private void initInstace() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_more_info, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);                   // รับค่าไอดีของ menu
        ShareActionProvider shareActionProvider = (ShareActionProvider)         // shareactionprovider  ตัวไว้แชร์
                MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
        return true;
    }

    private Intent getShareIntent(){        //การส่งข้อมูลไปหา app อื่น
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");               // ประเภทที่ส่งไป
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Extra text");
        return intent;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:         // ปุ่ม home
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
