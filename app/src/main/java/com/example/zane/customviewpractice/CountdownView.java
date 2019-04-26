package com.example.zane.customviewpractice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import timber.log.Timber;

public class CountdownView extends View {


    private static final int MAX_SECONDS = 99999;
    private Paint backgroundPaint;
    private TextPaint numberPaint;

    private long startTime;
    private Runnable updateRunnable = this::updateTimer;

    public CountdownView(Context context) {
        super(context);
        init();
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.parseColor("#880E4F"));

        numberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));

        // Drawing Operation are in pixels, so need to convert to DP
        // In this example we want it 64sp
        numberPaint.setTextSize(64f * getResources().getDisplayMetrics().scaledDensity);
    }

    public void start(){
        startTime = System.currentTimeMillis();
        updateTimer();
    }

    public void stop(){
        startTime = 0;

        // Stop periodic updates
        removeCallbacks(updateRunnable);
    }

    public void updateTimer(){
        invalidate();
        postDelayed(updateRunnable, 200L);
    }



    /**
     * Override Methods
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Be able to see constraint passed in from parent
        Timber.i("onMeasure w: " + MeasureSpec.toString(widthMeasureSpec));
        Timber.i("onMeasure h: " + MeasureSpec.toString(heightMeasureSpec));

        // Return pixel width of string MAX_SECONDS
        int maxTextWidth = (int) Math.ceil(numberPaint.measureText(String.valueOf(MAX_SECONDS)));
        // numberPaint.measureText only measures width
        // getTextBound is available to measure height
        // However, getTextBound accuracy varies depending on typeface
        // Therefore, getFontMetrics should be used to get the maximum possible height
        Paint.FontMetrics numberFontMetrics = numberPaint.getFontMetrics();
        // numberFontMetrics.top is negative on the y axis, therefore - is needed
        int maxTextHeight = (int) Math.ceil(-numberFontMetrics.top + numberFontMetrics.bottom);


        // Account for padding
        int contentWidth = maxTextWidth + getPaddingLeft() + getPaddingRight();
        int contentHeight = maxTextHeight + getTop() + getPaddingBottom();
        int contentSize = Math.max(contentWidth, contentHeight);

        // Basically a Math.min() functionality
        // If contentSize is larger than measureSpec, measuredWidth/Height = measureSpec
        int measuredWidth = resolveSize(contentSize, widthMeasureSpec);
        int measuredHeight =  resolveSize(contentSize, heightMeasureSpec);

        // HAS TO BE CALLED, OTHERWISE EXCEPTION!!!
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = getWidth();
        int canvasHeight = getHeight();

        float centerX = Math.round(canvasWidth * 0.5f);
        float centerY = Math.round(canvasHeight * 0.5f);

        float radius = (canvasWidth < canvasHeight ? canvasWidth : canvasHeight) * 0.5f;

        // 0.001 to convert milliseconds into seconds
        long seconds = Math.min((long) ((System.currentTimeMillis() - startTime) * 0.001), MAX_SECONDS);
        String number = String.valueOf(seconds);

        // Since views are drawn from bottom left, offset is needed
        float offsetX = numberPaint.measureText(number) * 0.5f;
        float offsetY = numberPaint.getFontMetrics().ascent * -0.4f;

        canvas.drawCircle(centerX, centerY, radius, backgroundPaint);
        canvas.drawText(number, centerX - offsetX, centerY + offsetY, numberPaint);
    }


}
