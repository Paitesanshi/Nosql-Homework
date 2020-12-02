package com.bjtu.redis;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Counter {
    private String name;
    private String index;
    private ArrayList<String> key;
    private String type;
    private int value;
    private String FREQ;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public ArrayList<String> getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    public String getFREQ() {
        return FREQ;
    }

    public Counter(JSONObject obj){
        name=obj.getString("counterName");
        index=obj.getString("counterIndex");
        JSONArray array = obj.getJSONArray("keyFields");
        key=new ArrayList<>();
        for (Object s: array) {
            key.add((String)s);
        }
        type=obj.getString("type");
        value=0;

        if(obj.containsKey("valueFields"))
        {
            value=obj.getInteger("valueFields");
        }
        if(obj.containsKey("FREQFields"))
        {
            FREQ=obj.getString("FREQFields");
        }
    }

    @Override
    public String toString() {
        return "Counter{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", FREQ='" + FREQ + '\'' +
                '}';
    }
}
