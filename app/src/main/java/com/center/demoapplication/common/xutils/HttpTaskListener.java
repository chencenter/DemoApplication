package com.center.demoapplication.common.xutils;

/**
 *
 * 描述：
 */

public interface HttpTaskListener {
    void onSuccess(int requestId, String result);
    void onException(int requestId, String code);
}
