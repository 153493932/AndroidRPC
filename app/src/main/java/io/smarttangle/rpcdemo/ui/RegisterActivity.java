package io.smarttangle.rpcdemo.ui;

import android.os.Bundle;
import android.view.View;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.UserStorageUtils;

/**
 * Created by haijun on 2018/3/7.
 */

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        findViewById(R.id.btRegister).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserStorageUtils.putObject(RegisterActivity.this, StorageKey.USER_ADDRESS, "0x73217b66d582c13aa7078d66a6008a36924170a0");
                finish();
            }
        });
    }
}
