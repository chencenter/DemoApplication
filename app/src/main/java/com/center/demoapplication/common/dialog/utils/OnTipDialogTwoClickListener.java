package com.center.demoapplication.common.dialog.utils;

import java.io.Serializable;

/**
 * Created by aaa on 2019/3/25.
 */

public interface OnTipDialogTwoClickListener extends Serializable {
    /**
     * 有两个按钮的对话框
     */
    void onCancelButtonClick();

    void onConfirmButtonClick();
}
