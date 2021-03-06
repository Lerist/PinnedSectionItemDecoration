package com.oushangfeng.pinneddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.oushangfeng.pinneddemo.adapter.RecyclerAdapter;
import com.oushangfeng.pinneddemo.entitiy.SmallPinnedHeaderEntity;
import com.oushangfeng.pinneddemo.holder.RecyclerViewHolder;
import com.oushangfeng.pinnedsectionitemdecoration.SmallPinnedHeaderItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private RecyclerAdapter<String, SmallPinnedHeaderEntity<String>> mAdapter;

    private int[] mDogs = {R.mipmap.dog0, R.mipmap.dog1, R.mipmap.dog2, R.mipmap.dog3, R.mipmap.dog4, R.mipmap.dog5, R.mipmap.dog6, R.mipmap.dog7, R.mipmap.dog8};
    private int[] mCats = {R.mipmap.cat0, R.mipmap.cat1, R.mipmap.cat2, R.mipmap.cat3, R.mipmap.cat4, R.mipmap.cat5, R.mipmap.cat6, R.mipmap.cat7, R.mipmap.cat8};
    private int[] mRabbits = {R.mipmap.rabbit0, R.mipmap.rabbit1, R.mipmap.rabbit2, R.mipmap.rabbit3, R.mipmap.rabbit4, R.mipmap.rabbit5, R.mipmap.rabbit6, R.mipmap.rabbit7, R.mipmap.rabbit8};
    private int[] mPandas = {R.mipmap.panda0, R.mipmap.panda1, R.mipmap.panda2, R.mipmap.panda3, R.mipmap.panda4, R.mipmap.panda5, R.mipmap.panda6, R.mipmap.panda7, R.mipmap.panda8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SmallPinnedHeader");

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        List<SmallPinnedHeaderEntity<String>> data = new ArrayList<>();
        int i = 0;
        for (int dog : mDogs) {
            data.add(new SmallPinnedHeaderEntity<>(dog + "", i == 0 ? RecyclerAdapter.TYPE_SECTION : RecyclerAdapter.TYPE_DATA, "狗狗"));
            i++;
        }
        i = 0;
        for (int cat : mCats) {
            data.add(new SmallPinnedHeaderEntity<>(cat + "", i == 0 ? RecyclerAdapter.TYPE_SECTION : RecyclerAdapter.TYPE_DATA, "猫咪"));
            i++;
        }
        i = 0;
        for (int rabbit : mRabbits) {
            data.add(new SmallPinnedHeaderEntity<>(rabbit + "", i == 0 ? RecyclerAdapter.TYPE_SECTION : RecyclerAdapter.TYPE_DATA, "兔子"));
            i++;
        }
        i = 0;
        for (int panda : mPandas) {
            data.add(new SmallPinnedHeaderEntity<>(panda + "", i == 0 ? RecyclerAdapter.TYPE_SECTION : RecyclerAdapter.TYPE_DATA, "熊猫"));
            i++;
        }

        mAdapter = new RecyclerAdapter<String, SmallPinnedHeaderEntity<String>>() {
            @Override
            public int getItemLayoutId(int viewType) {
                switch (viewType) {
                    case RecyclerAdapter.TYPE_SECTION:
                        return R.layout.item_data_with_small_pinned_header;
                    case RecyclerAdapter.TYPE_DATA:
                        return R.layout.item_data;
                }
                return 0;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int viewType, int position, String item) {
                switch (viewType) {
                    case RecyclerAdapter.TYPE_SECTION:
                        holder.setText(R.id.tv_small_pinned_header, this.getData().get(position).getPinnedHeaderName());
                        Glide.with(SecondActivity.this).load(Integer.parseInt(item)).into(holder.getImageView(R.id.iv_animal));
                        break;
                    case RecyclerAdapter.TYPE_DATA:
                        holder.setText(R.id.tv_pos, position + "");
                        Glide.with(SecondActivity.this).load(Integer.parseInt(item)).into(holder.getImageView(R.id.iv_animal));
                        break;
                }
            }
        };
        mAdapter.setData(data);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new SmallPinnedHeaderItemDecoration(R.id.tv_small_pinned_header));
        mRecyclerView.setAdapter(mAdapter);

    }

}
