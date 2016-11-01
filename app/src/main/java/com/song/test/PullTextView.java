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
    private ValueAnimator showAnimator;
    private ValueAnimator hideAnimator;
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
        //1.显示动画
        showAnimator = ValueAnimator.ofInt(initLines,maxLines);
        showAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = (int) animation.getAnimatedValue();
                setMaxLines(num);
                invalidate();
            }
        });
        showAnimator.setDuration(animationTime);
        //2.隐藏动画
        hideAnimator = ValueAnimator.ofInt(maxLines,initLines);
        showAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int num = (int) animation.getAnimatedValue();
                setMaxLines(num);
                invalidate();
            }
        });
        hideAnimator.setDuration(animationTime);
    }

    /**
     * 展开
     */
    public void startShow() {
        showAnimator.start();
    }

    /**
     * 停止展开
     */
    public void stopShow() {
        showAnimator.end();
    }

    /**
     * 开始隐藏
     */
    public void startHide() {
        hideAnimator.start();
    }

    /**
     * 停止隐藏
     */
    public void stopHide(){
        hideAnimator.end();
    }

}
