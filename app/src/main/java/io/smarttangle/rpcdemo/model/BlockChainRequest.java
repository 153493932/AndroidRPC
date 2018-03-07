package io.smarttangle.rpcdemo.model;

import android.app.Activity;
import android.net.VpnService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.smarttangle.rpcdemo.Utils.UrlUtils;
import io.smarttangle.rpcdemo.net.SocketClient;

/**
 * Created by haijun on 2018/3/7.
 */

public class BlockChainRequest {

    public interface Listener<T> {
        public void onResponse(T response);
    }

    public interface ErrorListener {
        public void onError(BCErrorEntity response);
    }

    public static BalanceEntity getBalance(final Activity context, final String address, final Listener<BalanceEntity> response, final ErrorListener error) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SocketClient client = new SocketClient(UrlUtils.URL, new VpnService(), true);
                try {
                    List<String> params = new ArrayList<>();
                    params.add(address);
                    params.add("latest");
                    String postData = client.buildPostData("eth_getBalance", params, "1");
                    final Entity entity = client.call(postData, BalanceEntity.class);
                    context.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (entity instanceof BCErrorEntity) {
                                error.onError((BCErrorEntity) entity);
                            } else {
                                response.onResponse((BalanceEntity) entity);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return null;
    }
}
