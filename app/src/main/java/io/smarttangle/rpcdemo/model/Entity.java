package io.smarttangle.rpcdemo.model;

import java.io.Serializable;

/**
 * Created by haijun on 2018/3/6.
 */

public class Entity implements Serializable {

    public String jsonrpc;
    public String id;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
