package io.smarttangle.rpcdemo.net;

import android.net.VpnService;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by haijun on 2018/3/6.
 */

public class VPNSocket extends Socket {

    public VPNSocket(VpnService vpnService, boolean byPassVPN) {
        super();
        vpnService.protect(this);
    }

    protected VPNSocket(String host, int port, VpnService vpnService, boolean byPassVPN) throws UnknownHostException, IOException {
        super(host, port);
        vpnService.protect(this);
    }
}