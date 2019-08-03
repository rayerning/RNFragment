package com.hotupdatetest.customreact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;

import javax.annotation.Nullable;

public abstract class ReactFragment extends Fragment implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {

    private final ReactFragmentDelegate mDelegate;
    private Activity activity;

    public ReactFragment() {
        this.activity = getActivity();
        Log.e("nel-log", "ReactFragment constructor");
        mDelegate = createReactActivityDelegate(activity);
    }

    public ReactFragment(Activity activity) {
        this.activity = activity;
        Log.e("nel-log", "ReactFragment constructor");
        mDelegate = createReactActivityDelegate(activity);
    }

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     * e.g. "MoviesApp"
     */
    protected @Nullable
    abstract String getMainComponentName();

    /**
     * Called at construction time, override if you have a custom delegate implementation.
     */
    protected ReactFragmentDelegate createReactActivityDelegate(Activity activity) {
        Log.e("nel-log", "createReactActivityDelegate");
        return new ReactFragmentDelegate(activity, getMainComponentName());
    }

    @Override
    public void onCreate(@androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("nel-log", "onCreate");
    }

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @androidx.annotation.Nullable Bundle savedInstanceState) {
        Log.e("nel-log", "onCreateView");
        new Handler().postDelayed(() -> mDelegate.onCreateView(savedInstanceState), 10);
        ReactRootView reactRootView = mDelegate.getReactRootView();
        Log.e("nel-log", "reactRootView == null : " + (reactRootView == null));
        return reactRootView;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("nel-log", "onPause");
        mDelegate.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("nel-log", "onResume");
        mDelegate.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("nel-log", "onDestroyView");
        mDelegate.onDestroy();
    }

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return getContext().checkPermission(permission, pid, uid);
    }

    @Override
    public int checkSelfPermission(String permission) {
        return getContext().checkSelfPermission(permission);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mDelegate.onActivityResult(requestCode, resultCode, data);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDelegate.onKeyDown(keyCode, event) || activity.onKeyDown(keyCode, event);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        activity.onBackPressed();
    }

    @Override
    public void requestPermissions(
            String[] permissions,
            int requestCode,
            PermissionListener listener) {
        mDelegate.requestPermissions(permissions, requestCode, listener);
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        mDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected final ReactNativeHost getReactNativeHost() {
        return mDelegate.getReactNativeHost();
    }

    protected final ReactInstanceManager getReactInstanceManager() {
        return mDelegate.getReactInstanceManager();
    }

    protected final void loadApp(String appKey) {
        mDelegate.loadApp(appKey);
    }
}
