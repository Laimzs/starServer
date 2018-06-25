package com.starServer.entity.response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BooleanResponse {
    private Boolean result = true;
    private String errMsg = "";
    //用于传递上下文信息
    private Map<String, Object> attributes = new HashMap();

    public BooleanResponse fail(String errMsg) {
        this.result = false;
        this.errMsg = errMsg;
        return this;
    }

    public static BooleanResponse build() {
        return new BooleanResponse();
    }

    public void set(String key, Object val){
        this.attributes.put(key, val);
    }

    public Object get(String key){
        return this.attributes.get(key);
    }
}
