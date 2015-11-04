package com.acd21.staromoon.desertmaker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acd21.staromoon.desertmaker.R;
import com.acd21.staromoon.desertmaker.dao.DessertItemDao;
import com.acd21.staromoon.desertmaker.manager.DessertListManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SummaryFragment extends Fragment {

    ImageView imgBg;
    TextView tvName;
    TextView tvDesc;
    DessertItemDao dao;
    public SummaryFragment() {
        super();
    }

    public static SummaryFragment newInstance() {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        initInstances(rootView);
        /*if(savedInstanceState == null){
            dao = DessertListManager.getInstance().getSelectedDao();
        }else{
            //Load dao
        }*/
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        //setRetainInstance(true);

        //ควรจะเก็บค่า DAO ไม่งั้นพอรอบถัดไปที่เปิด app หลัง gc เก็บจะ null pointer
        dao = DessertListManager.getInstance().getSelectedDao();

        imgBg = (ImageView) rootView.findViewById(R.id.imgBg);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvDesc = (TextView) rootView.findViewById(R.id.tvDesc);

        tvName.setText(dao.getName());
        tvDesc.setText(dao.getDescription());
        Glide.with(SummaryFragment.this)
                .load(dao.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgBg);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //  save dao
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
