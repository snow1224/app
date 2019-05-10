package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by user on 2019/5/10.
 */

public class MySurfaceview extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    SurfaceHolder surfaceholder;
    Thread thread;
    Bitmap bubble;
    Boolean running=false;
    Boolean fly=false;
    float bX=0,bY=0;
    ArrayList<Bubble> bubbles;
    Paint mPaintBackground;
    public MySurfaceview(Context context) {
        super(context);

        setZOrderOnTop(true);
        bubble = BitmapFactory.decodeResource(getResources(),R.drawable.bubble2);
        surfaceholder=getHolder();
        surfaceholder.addCallback(this);
        setFocusable(true);
        Log.v("cclo","MySurfaceview");
        mPaintBackground = new Paint();
        mPaintBackground.setAntiAlias(true);
        mPaintBackground.setColor(Color.WHITE);

    }

    public void go(){
        running=true;
        thread = new Thread(this);
        thread.start();
        Log.v("cclo","go");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.v("cclo","draw");
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaintBackground);
        for(int i=0;i<bubbles.size();i++)
            canvas.drawBitmap(bubble,bubbles.get(i).x,bubbles.get(i).y,new Paint());
    }

    public void changeBubble(){
        for(int i=0;i<bubbles.size();i++) {

            bubbles.set(i,new Bubble(bubbles.get(i).x,bubbles.get(i).y-=50.0f));
            if (bubbles.get(i).y <= 20.0f) {
                fly = false;
                Canvas c = null;
                try {
//                c=surfaceholder.lockCanvas();
//                synchronized (surfaceholder){
                    draw(c);
//                }
                } catch (Exception e) {

                } finally {
//                    if (c != null) {
//                    surfaceholder.unlockCanvasAndPost(c);
//                    }
                }
            }
        }
    }

    @Override
    public void run() {
        Canvas c=null;
        Log.v("cclo","run");
        while(running) {
//            if(fly) {
                try {
//                    c = surfaceholder.lockCanvas();
//                    synchronized (surfaceholder) {
                        draw(c);
//                    }
                } catch (Exception e) {

                } finally {
//                    if (c != null) {
//                        surfaceholder.unlockCanvasAndPost(c);
//                    }
                }
                changeBubble();
//            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(!fly){
//            fly=true;
            bX=event.getX();
            bY=event.getY();
            bubbles.add(new Bubble(bX,bY));
//        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    class Bubble{
        float x,y;
        boolean fly=false;
        Bubble(float x,float y){
            x=0;y=0;
        }
    }
}

