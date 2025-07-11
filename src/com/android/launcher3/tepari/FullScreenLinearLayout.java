package com.android.launcher3.tepari;

import android.content.Context;
import android.os.Build;
import android.view.WindowInsets;
import android.widget.LinearLayout;
import android.content.Intent;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import com.android.launcher3.R;

public class FullScreenLinearLayout extends LinearLayout {

    public static final String TAG = "FullScreenLinearLayout";

    public FullScreenLinearLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.overlay_macrostock, this, true);
        setClickable(true);
        setFocusable(true);
//        setOnTouchListener((v, event) -> true);

        Button startBtn = findViewById(R.id.btn_start);
        startBtn.setOnClickListener(v -> {
            Log.d(TAG, "START BUTTON CLICK ---- 11111");
            Context ctx = getContext();
            Log.d(TAG, "START BUTTON CLICK ---- 222222");
            Intent launch = ctx.getPackageManager().getLaunchIntentForPackage("com.tepari.macrostock.macrostock");
            Log.d(TAG, "START BUTTON CLICK ---- 333333");
            if (launch != null) {
                Log.d(TAG, "START BUTTON CLICK ---- 444444");
                // ensure it comes up as a new task
                launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(launch);
            } else {
                // optional: fallback or log
                Log.d(TAG, "MacroStock app not found");
            }
            Log.d(TAG, "START BUTTON CLICK ---- 555555");
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Button btn = findViewById(R.id.btn_start);
        Rect hit = new Rect();
        btn.getHitRect(hit);
        Log.d(TAG, "onInterceptTouchEvent ---- 1111");
        if (hit.contains((int)ev.getX(), (int)ev.getY())) {
            Log.d(TAG, "onInterceptTouchEvent ---- 22222");
            return false;
        }
        return true;
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return WindowInsets.CONSUMED;
        }
        return super.onApplyWindowInsets(insets);
    }
}
