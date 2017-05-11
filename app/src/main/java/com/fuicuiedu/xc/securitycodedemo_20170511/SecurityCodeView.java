package com.fuicuiedu.xc.securitycodedemo_20170511;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class SecurityCodeView extends RelativeLayout{

    public SecurityCodeView(Context context) {
        this(context,null);
    }

    public SecurityCodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SecurityCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        initView();
    }

    private void initView() {
        //将自定义控件的布局填充进来
        View.inflate(getContext(),R.layout.view_security_code,this);


    }
}
