package com.song.test;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Song on 2016/10/28.
 */
public class PullTextView extends TextView {

    private int initLines;//初始化的时候显示几行
    private int animationTime;//执行时间
    private int maxLines;//文本的最大行数
    private boolean hasMeasure;//是否获取到最大行数
    ValueAnimator animator;
    public PullTextView(Context context) {
        this(context, null);
    }
    public PullTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PullTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PullTextView, defStyleAttr, 0);
        initLines = ta.getInteger(R.styleable.PullTextView_initLines,1);
        animationTime = ta.getInteger(R.styleable.PullTextView_animationTime,0);
        ta.recycle();
        init();
    }

    private void init() {
        setMaxLines(initLines);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if(hasWindowFocus && !hasMeasure) {
            initTv();
            hasMeasure = true;
        }
    }

    private void initTv() {

        maxLines = getLineCount();
        animator = ValueAnimator.ofInt(initLines,maxLines);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = (int) animation.getAnimatedValue();
                setMaxLines(num);
                invalidate();
            }
        });
        animator.setDuration(animationTime);
    }

    public void startShow() {
        animator.start();
    }

    public void stopShow() {
        animator.end();
    }

}
