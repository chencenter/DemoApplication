package com.center.demoapplication.common.xutils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aaa on 2019/3/25.
 */

public class JsonObjectXutils {
    public static String getJson(Context context, JSONObject object){
        String value="";
        try {
            JSONObject js_request1 = new JSONObject();
//            js_request1.put("uToken", SPUtils.getInstance(context).get(Constants.KEY_TOKEN, ""));
            js_request1.put("sourceFrom",1);//来源桂东
            js_request1.put("data",object);
            value=js_request1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getJsonHasPageNum(Context context,JSONObject object,int pageNum,int pageSize){
        String value="";
        try {
            JSONObject js_request1 = new JSONObject();
//            js_request1.put("uToken", SPUtils.getInstance(context).get(Constants.KEY_TOKEN, ""));
            js_request1.put("sourceFrom",1);
            js_request1.put("pageNum",pageNum);
            js_request1.put("pageSize",pageSize);
            js_request1.put("data",object);
            value=js_request1.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }

}
