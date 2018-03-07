package io.smarttangle.rpcdemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.BCErrorEntity;
import io.smarttangle.rpcdemo.model.BalanceEntity;
import io.smarttangle.rpcdemo.model.BlockChainRequest;
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

        BlockChainRequest.getBalance(this, address, new BlockChainRequest.Listener<BalanceEntity>() {

            @Override
            public void onResponse(final BalanceEntity response) {
                TextView tvBalance = findViewById(R.id.tvBalance);
                tvBalance.setText(response.getResult() + " Wei");
            }
        }, new BlockChainRequest.ErrorListener() {
            @Override
            public void onError(BCErrorEntity error) {
                TextView tvBalance = findViewById(R.id.tvBalance);
                if (error.getStatus() == 200) {
                    tvBalance.setText(error.getError().getMessage());
                } else {
                    tvBalance.setText("Network Error Status :" + error.getStatus());
                }
            }
        });
    }

}
