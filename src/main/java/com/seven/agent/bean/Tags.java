package com.seven.agent.bean;

import java.util.HashMap;

public class Tags extends HashMap<String,Object> {
    public Tags addTag(String key,Object val){
        this.put(key,val);
        return this;
    }
}
