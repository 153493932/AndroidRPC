package io.smarttangle.rpcdemo.model;

/**
 * Created by haijun on 2018/3/6.
 */

public class BalanceEntity extends Entity {

    private static final long serialVersionUID = 1L;

    public String result;


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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
