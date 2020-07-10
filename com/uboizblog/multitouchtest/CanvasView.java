package com.uboizblog.multitouchtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class CanvasView extends View
{
    public List<TouchInfo> MouseTouch=new ArrayList<TouchInfo>();
    private Paint mPaint;
    private int[] colors = { Color.BLUE, Color.GREEN, Color.MAGENTA,Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY,Color.LTGRAY, Color.YELLOW };

    public CanvasView(Context context) {
        super(context);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int maskedAction = event.getActionMasked();
        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                int pointerIndex=event.getActionIndex();
                int pointerId = event.getPointerId(pointerIndex);
                if(pointerId==MouseTouch.size()){
                    TouchInfo m=new TouchInfo();
                    m.mLastTouchX = event.getX(pointerIndex);
                    m.mLastTouchY = event.getY(pointerIndex);
                    m.touched = true;
                    MouseTouch.add(m);
                }else{
                    MouseTouch.get(pointerId).mLastTouchX = event.getX(pointerIndex);
                    MouseTouch.get(pointerId).mLastTouchY = event.getY(pointerIndex);
                    MouseTouch.get(pointerId).touched=true;
                }

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    int pointerId = i;//event.getPointerId(i);
                    int pointerIndex=event.findPointerIndex(pointerId);
                    if(pointerId>=0 && pointerId<MouseTouch.size() && pointerIndex>=0 && pointerIndex<event.getPointerCount()) {
                        MouseTouch.get(pointerId).moved = true;
                        MouseTouch.get(pointerId).mLastTouchX = event.getX(pointerIndex);
                        MouseTouch.get(pointerId).mLastTouchY = event.getY(pointerIndex);
                    }
                }
                TouchInfo.MOUSEMOVED=true;

                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                int pointerId = event.getPointerId(pointerIndex);

                if(pointerId<MouseTouch.size()) {
                    MouseTouch.get(pointerId).touched = false;
                    MouseTouch.get(pointerId).moved = false;
                }

                break;
            }
        }
        invalidate();

        return true;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),mPaint);

        for (int size = MouseTouch.size(), i = 0; i < size; i++) {
            mPaint.setColor(colors[i %colors.length]);
            canvas.drawRect(MouseTouch.get(i).getX()-25,MouseTouch.get(i).getY()-25,MouseTouch.get(i).getX()+25,MouseTouch.get(i).getY()+25, mPaint);
        }
    }
}
