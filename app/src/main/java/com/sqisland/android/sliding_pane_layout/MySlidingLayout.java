package com.sqisland.android.sliding_pane_layout;

import android.content.Context;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by bezalel-s on 28/03/2016.
 */
public class MySlidingLayout extends SlidingPaneLayout {
    public MySlidingLayout(Context context) {
        super(context);
    }

    public MySlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySlidingLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

}
