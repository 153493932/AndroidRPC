package io.smarttangle.rpcdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import io.smarttangle.rpcdemo.R;
import io.smarttangle.rpcdemo.Utils.StorageKey;
import io.smarttangle.rpcdemo.model.BCRequest;
import io.smarttangle.rpcdemo.model.RPCEntity;
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

                EditText editText = findViewById(R.id.evPassword);
                String password = editText.getText().toString();
                if (TextUtils.isEmpty(password)) {
                    Snackbar.make(findViewById(R.id.parentLayout), "Please input transaction password !", 3000).show();
                } else {
                    BCRequest.newAccount(RegisterActivity.this, password, new BCRequest.Listener<RPCEntity>() {
                        @Override
                        public void onResponse(RPCEntity response) {
                            UserStorageUtils.putObject(RegisterActivity.this, StorageKey.USER_ADDRESS, response.getResult());
                            Intent intent = new Intent(RegisterActivity.this, ProxyZonesActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, null);
                }
            }
        });
    }
}
