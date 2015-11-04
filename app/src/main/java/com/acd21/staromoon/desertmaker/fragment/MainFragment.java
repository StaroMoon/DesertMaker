package com.acd21.staromoon.desertmaker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.acd21.staromoon.desertmaker.R;
import com.acd21.staromoon.desertmaker.activity.MoreInfoActivity;
import com.acd21.staromoon.desertmaker.adapter.DessertListAdapter;
import com.acd21.staromoon.desertmaker.busevent.DessertBusEvent;
import com.acd21.staromoon.desertmaker.dao.DessertItemCollectionDao;
import com.acd21.staromoon.desertmaker.dao.DessertItemDao;
import com.acd21.staromoon.desertmaker.manager.DessertListManager;
import com.acd21.staromoon.desertmaker.manager.http.HTTPManager;
import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.inthecheesefactory.thecheeselibrary.manager.bus.MainBus;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    ListView listview;
    DessertListAdapter listAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        //setRetainInstance(true);

        listview = (ListView) rootView.findViewById(R.id.listview);
        listAdapter = new DessertListAdapter();
        listview.setAdapter(listAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO : Send Signal to Activity
                /*Intent intent = new Intent(getActivity(), MoreInfoActivity.class);
                startActivity(intent);      แย่แย่*/
                // 1.แบบแย่ๆแย่ๆ สร้าง Mainactivity
                // 2.แบบแย่คือ สร้าง fragment เป็น interface แล้ว implement ทุก activitty class ข้อเสียคือถ้า activity เยอะต้อง implement ทุก class
                // 3.วิธีดีใช้ localboardcastmanager ส่งของ ข้อเสียถ้าส่งของมาหลายแบบต้องประกาศเยอะ ยุ่งยาก
                // 4.ดีดีดีดี   ใช้ eventbus lib otto
                DessertItemDao dao = DessertListManager.getInstance().getDao().getData().get(position);
                MainBus.getInstance().post(new DessertBusEvent(dao));   //ส่งตัวยา
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                swipeRefreshLayout.setEnabled(firstVisibleItem == 0);
                if(firstVisibleItem + visibleItemCount >= totalItemCount){
                    // Load More
                }
            }
        });
        loadData();
    }

    private void loadData() {
        Call<DessertItemCollectionDao> call = HTTPManager.getInstance().getService().loadDesserList();
        //call.execute() // เป็น synconous ห้ามใช้ใน android ไม่งั้นแอพค้าง
        call.enqueue(new Callback<DessertItemCollectionDao>() {
            @Override
            public void onResponse(Response<DessertItemCollectionDao> response, Retrofit retrofit) {        //ติดต่อติดแม้จะเป็น 404 error
                if(response.isSuccess()){
                    swipeRefreshLayout.setRefreshing(false);
                    // Do something
                    //Toast.makeText(getActivity(),                           //ถ้า user เลิกใช้กลางคันระหว่างโหลด แอพอาจพัง
                    DessertListManager.getInstance().setDao(response.body());
                    listAdapter.notifyDataSetChanged();         //บอก adapter ให้ไปบอก listview ว่ามีข้อมูลใหม่ไม่งั้นจะรีเฟรชใหม่ตลอดเวลา

                    Toast.makeText(Contextor.getInstance().getContext(),
                            response.body().getData().get(0).getName(),
                            Toast.LENGTH_SHORT)
                            .show();
                }else{
                    // Handle not success

                    try{
                        Toast.makeText(Contextor.getInstance().getContext(),
                                response.errorBody().string(),
                                Toast.LENGTH_SHORT)
                                .show();
                    }catch(IOException e){

                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {        //กรณีติดต่อไม่ติด
                //Handle Failure
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Contextor.getInstance().getContext(),
                        t.toString(),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
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
    }

    /*
     * Restore Instance State Here
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
