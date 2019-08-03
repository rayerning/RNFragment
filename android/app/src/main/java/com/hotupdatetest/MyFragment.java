package com.hotupdatetest;

import android.app.Activity;
import android.util.Log;

import com.hotupdatetest.customreact.ReactFragment;

import javax.annotation.Nullable;

public class MyFragment extends ReactFragment {

    MyFragment(Activity activity) {
        super(activity);
    }

    @Nullable
    @Override
    protected String getMainComponentName() {
        Log.e("nel-log", "getMainComponentName");
        return "HotUpdateTest";
    }
}
