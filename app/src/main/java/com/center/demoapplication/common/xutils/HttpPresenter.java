package com.center.demoapplication.common.xutils;

import android.content.Context;
import android.util.Log;

import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;

/**
 *
 * 描述：
 */

public class HttpPresenter {

    private RequestParams params;
    private int  requstId;
    private HttpTaskListener listener;
    private Context context;
    private Callback.Cancelable cancelable;
    private KProgressHUD mProgressDialog;//进度窗体

    private boolean isShowDialog = false;
    private String content="加载中";
    /**
     * 需要登录
     */
    public static final String STATE_NEED_LOGIN = "888888";
    /**
     * 错误
     */
    public static final String STATE_OTHER_FAILURE = "-1";
    /**
     * 成功
     */
    public static String STATE_SUCCESS = "0";

    public String state = STATE_OTHER_FAILURE;
    public String message = "";
    public String data = "";

    public HttpPresenter(Context context) {
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    public void get() {
        if(!CheckNetwork.isNetworkConnected(context)){
//            ToastUtilsKt.shortToastCenter(context, "无网络连接,请检查网络");
//            Toast.makeText()
            return;
        }

        if (params == null) {
            System.out.println("params is null");
            return;
        }

        if (listener == null) {
            System.out.println("listener is null");
            return;
        }

        cancelable = x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                listener.onException(requstId,arg0.toString());
                if(arg0.toString().contains("ConnectException")){
//                    ToastUtilsKt.shortToastCenter(context, "网络连接超时");
                }
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub
                hideInfoProgressDialog();
                cancelable.cancel();
            }

            @Override
            public void onSuccess(String arg0) {
                // TODO Auto-generated method stub
                listener.onSuccess(requstId,arg0);
                hideInfoProgressDialog();
            }

        });
        if (isShowDialog) {
            showInfoProgressDialog(context,content);
        }

    }

    public void post() {
        if(!CheckNetwork.isNetworkConnected(context)){
//            ToastUtilsKt.shortToastCenter(context, "无网络连接,请检查网络");
            return;
        }
        if (params == null) {
            System.out.println("params is null");
            return;
        }

        if (listener == null) {
            System.out.println("listener is null");
            return;
        }

        cancelable= x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onCancelled(CancelledException arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(Throwable arg0, boolean arg1) {
                // TODO Auto-generated method stub
                hideInfoProgressDialog();
                listener.onException(requstId,arg0.toString());
                Log.e("data",arg0.toString());
                if(arg0.toString().contains("ConnectException")){
//                    ToastUtilsKt.shortToastCenter(context, "网络连接超时");
                }
            }

            @Override
            public void onFinished() {
                // TODO Auto-generated method stub
                hideInfoProgressDialog();
                cancelable.cancel();
            }

            @Override
            public void onSuccess(String arg0) {
                // TODO Auto-generated method stub
                try {
                    ResponseParser(arg0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                hideInfoProgressDialog();
            }
        });

        if(isShowDialog){
            showInfoProgressDialog(context,content);
        }

    }

    /**
     *
     * 传json
     */
    public HttpPresenter putjson(String json){
        if(params!=null){
            params.setAsJsonContent(true);
            params.setBodyContent(json);
        }
        return this;
    }

    /**
     * 传文件
     */
    public HttpPresenter putFile(String key, File file){
        if (params != null) {
            params.addBodyParameter(key, file);
        }
        return this;
    }

    public HttpPresenter putHead(String key, String value){
        if (params != null) {
            params.addHeader(key, value);
        }
        return this;
    }

    public HttpPresenter setRequsetId(int requsetId) {
        this.requstId=requsetId;
        return this;
    }

    public HttpPresenter setUrl(String url) {
        params = new RequestParams(url);
        params.setConnectTimeout(10000);
        params.setReadTimeout(10000);
        return this;
    }

    public HttpPresenter setCallBack(HttpTaskListener listener) {
        this.listener=listener;
        return this;
    }

    //用来设置请求是是否显示progressdialog
    public void setShowDialog(boolean isShowDialog) {
        this.isShowDialog = isShowDialog;
    }

    public void setshowDialogcontent(String msg){
        this.content=msg;
    }

    /**
     * 显示进度框
     * @param str
     */
    public  void showInfoProgressDialog(Context context, final String... str) {
        if (mProgressDialog == null) {
            mProgressDialog = new KProgressHUD(context);
            mProgressDialog.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
            mProgressDialog.setDimAmount(0.5f);
            mProgressDialog.setCancellable(true);
        }
        if (str.length == 0) {
            mProgressDialog.setLabel("加载中...");
        } else {
            mProgressDialog.setLabel(str[0]);
        }

        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 隐藏等待条
     */
    public  void hideInfoProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog=null;
        }
    }

    public void ResponseParser(String json)throws Exception{
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has("code")) {
            state = jsonObject.optString("code");
        } else {
            throw new RuntimeException("按照ResponseParser解析code失败，需要调整ResponseParser.");
        }
        if (jsonObject.has("message")) {
            message = jsonObject.optString("message");
        }
        if (jsonObject.has("data")) {
            data = jsonObject.optString("data");
        }
        if (STATE_NEED_LOGIN.equals(state)) {// 需要登录
//            ToastUtilsKt.longToastCenter(context, "已在其他终端登录，请重新登录");
//            LoginHelper.getInstance(context).exit(LoginActivity.class);
        }else{
            listener.onSuccess(requstId,json);
        }
    }
}
