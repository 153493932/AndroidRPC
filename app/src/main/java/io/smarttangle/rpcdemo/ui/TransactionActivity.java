package io.smarttangle.rpcdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.BCRequest;
import io.smarttangle.rpcdemo.model.RPCEntity;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

/**
 * Created by haijun on 2018/3/7.
 */

public class TransactionActivity extends BaseActivity {

    public final static String TO_ADDRESS = "TO_ADDRESS";
    public final static String AMOUNT = "0x10";
    private String toAddress;
    private String fromAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);

        fromAddress = UserStorageUtils.getObject(this, StorageKey.USER_ADDRESS);
        TextView textView = (TextView) findViewById(R.id.tvMyAddress);
        textView.setText(fromAddress);

        Intent intent = this.getIntent();
        if (intent != null) {
            toAddress = intent.getStringExtra(TO_ADDRESS);
            TextView toAddressView = (TextView) findViewById(R.id.tvDestAddress);
            toAddressView.setText(toAddress);
        }

        findViewById(R.id.btTransfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editText = (EditText) findViewById(R.id.evPassword);
                String password = editText.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Snackbar.make(findViewById(R.id.parentLayout), "Please input transaction password !", 3000).show();
                } else {
                    BCRequest.transaction(TransactionActivity.this, fromAddress, toAddress, AMOUNT, password, new BCRequest.Listener<RPCEntity>() {

                        @Override
                        public void onResponse(RPCEntity rpcEntity) {
                            UserStorageUtils.putObject(TransactionActivity.this, StorageKey.TX_HASH, rpcEntity.getResult());
                            Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }
                    }, null);
                }
            }
        });
    }
}
