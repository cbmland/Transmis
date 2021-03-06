package com.dss886.transmis.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.dss886.transmis.R;
import com.dss886.transmis.base.App;

/**
 * Created by dss886 on 2017/6/29.
 */

@SuppressLint("ViewConstructor")
public class SwitchItem extends BaseItem {

    private TextView mTitle;
    private Switch mSwitchView;
    private String mKey;
    private boolean mDefaultValue;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener;

    public SwitchItem(Context context) {
        super(context);
        View.inflate(getContext(), R.layout.view_switch_item, this);
        mTitle = findViewById(R.id.title);
        mSwitchView = findViewById(R.id.switcher);
    }

    public SwitchItem(Context context, String title, String key, boolean defaultValue) {
        this(context);
        mTitle.setText(title);
        setSpInfo(key, defaultValue);
    }

    @Override
    public void onResume() {
        boolean isChecked = App.sp.getBoolean(mKey, mDefaultValue);
        mSwitchView.setChecked(isChecked);
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(mSwitchView, isChecked);
        }
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setSpInfo(String key, boolean defaultValue) {
        mKey = key;
        mDefaultValue = defaultValue;
        mSwitchView.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = App.sp.edit();
            editor.putBoolean(key, isChecked);
            editor.apply();
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged(mSwitchView, isChecked);
            }
        });
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }
}
