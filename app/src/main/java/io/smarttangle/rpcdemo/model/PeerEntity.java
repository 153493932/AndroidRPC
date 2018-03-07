package io.smarttangle.rpcdemo.model;

/**
 * Created by haijun on 2018/3/7.
 */

public class PeerEntity extends Entity {

    private static final long serialVersionUID = 1L;

    private String ip;
    private String nickName;
    private String address;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
