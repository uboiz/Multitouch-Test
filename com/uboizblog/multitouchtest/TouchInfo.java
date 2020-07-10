package com.uboizblog.multitouchtest;

public class TouchInfo
{
    public static boolean MOUSEMOVED=false;
    public float mLastTouchX=0.f,mLastTouchY=0.f;
    public boolean touched=false,moved=false;

    public TouchInfo()
    {

    }

    public final float getX()
    {
        return mLastTouchX;
    }

    public final float getY()
    {
        return mLastTouchY;
    }
}
