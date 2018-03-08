package io.smarttangle.rpcdemo.ui;

import android.os.Bundle;
import android.widget.TextView;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.RPCEntity;
import io.smarttangle.rpcdemo.model.BCRequest;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

/**
 * Created by haijun on 2018/3/7.
 */

public class MineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_activity);
        TextView addressView = findViewById(R.id.tvAddress);
        final String address = UserStorageUtils.getObject(this, StorageKey.USER_ADDRESS);
        addressView.setText(address);

        BCRequest.getBalance(this, address, new BCRequest.Listener<RPCEntity>() {

            @Override
            public void onResponse(final RPCEntity response) {
                TextView tvBalance = findViewById(R.id.tvBalance);
                tvBalance.setText(response.getResult() + " Wei");
            }
        }, null);
    }

}
