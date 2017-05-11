package com.fuicuiedu.xc.securitycodedemo_20170511;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/11 0011.
 */

public class SecurityCodeView extends RelativeLayout{
    private EditText editText;//输入框，透明不可见
    private TextView[] textViews;//验证码显示框
    private StringBuffer stringBuffer = new StringBuffer();//拼接string
    private int count = 5;//用于限制验证码长度
    private String inputContent;//最终得到的String


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

        editText = (EditText) findViewById(R.id.item_edittext);

        textViews = new TextView[5];//五个显示框（根据需求更改）
        textViews[0] = (TextView) findViewById(R.id.item_code_tv1);
        textViews[1] = (TextView) findViewById(R.id.item_code_tv2);
        textViews[2] = (TextView) findViewById(R.id.item_code_tv3);
        textViews[3] = (TextView) findViewById(R.id.item_code_tv4);
        textViews[4] = (TextView) findViewById(R.id.item_code_tv5);

        editText.setCursorVisible(false);//将光标隐藏
        //给EditText添加监听
        setListener();
    }

    private void setListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //重点~！！！！
                //如果字符不为“”空时才进行操作
                if (!s.toString().equals("")){
                    //当用户输入完验证码时，不去做操作
                    if (stringBuffer.length() > 4){
                        editText.setText("");
                        return;
                    }else{
                        //当字符添加到StringBuffer中
                        stringBuffer.append(s);
                        //添加后将EditText置空，造成没有文字输入的错觉
                        editText.setText("");
                        //记录stringBuffer长度
                        count = stringBuffer.length();
                        //对最终的string进行赋值
                        inputContent = stringBuffer.toString();
                        //todo 当文字长度为5，则调用完成输入监听
                    }

                }

                //将输入内容用TextView进行展示
                for (int i = 0; i < stringBuffer.length(); i++) {
                    //设置TextView显示文字
                    textViews[i].setText(String.valueOf(inputContent.charAt(i)));
                    //改变textView背景
                    textViews[i].setBackgroundResource(R.drawable.bg_verify_press);
                }
            }
        });

        //editText，添加用户点击删除按键执行删除操作的监听
        editText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //判断是否点击了删除按键
                if (keyCode == KeyEvent.KEYCODE_DEL
                        && event.getAction() == KeyEvent.ACTION_DOWN){
                    // TODO: 2017/5/11 0011 删除操作
                    return true;
                }
                return false;
            }
        });
    }
}
