package io.smarttangle.rpcdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

/**
 * Created by haijun on 2018/3/7.
 */

public class ProxyServersActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> datas;
    private ServiceListAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proxy_servers_activity);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.rvServers);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recycleAdapter = new ServiceListAdapter(this, datas);
        recyclerView.setAdapter(recycleAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recycleAdapter.setOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final String address = UserStorageUtils.getObject(ProxyServersActivity.this, StorageKey.USER_ADDRESS);
                Intent intent = null;
                if (TextUtils.isEmpty(address)) {
                    intent = new Intent(ProxyServersActivity.this, LoginActivity.class);
                } else {
                    intent = new Intent(ProxyServersActivity.this, TransferActivity.class);
                }
                startActivity(intent);
            }
        });
    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i = 0; i < 40; i++) {
            datas.add(i + "");
        }

    }
}
