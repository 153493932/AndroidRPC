package io.smarttangle.rpcdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

/**
 * Created by haijun on 2018/3/7.
 */

public class TransferActivity extends BaseActivity {

    public final static String TO_ADDRESS = "TO_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_activity);

        final String address = UserStorageUtils.getObject(this, StorageKey.USER_ADDRESS);
        TextView textView = (TextView) findViewById(R.id.tvMyAddress);
        textView.setText(address);

        Intent intent = this.getIntent();
        if (intent != null) {
            String toAddress = intent.getStringExtra(TO_ADDRESS);
            TextView toAddressView = (TextView) findViewById(R.id.tvDestAddress);
            toAddressView.setText(toAddress);
        }

        findViewById(R.id.btTransfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransferActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
