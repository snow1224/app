package com.example.user.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 2019/5/3.
 */

public class myCanvas extends View{

    Canvas mCanvas;
    Bitmap bitmap;
    Path path;
    Paint mPaint;
    float cx,cy;
    public myCanvas(Context context) {
        super(context);
        mPaint=new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15f);
        mPaint.setAntiAlias(true);

        path=new Path();
        path.moveTo(100,100);


    }

    public void doDown(float x, float y){
        path.lineTo(x,y);
        invalidate();
    }
    public void clear(){
        path.reset();
        invalidate();;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        cx=event.getX();
        cy=event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                doDown(cx,cy);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap=Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        mCanvas=new Canvas(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(50,50,150,150);
        canvas.drawPath(path,mPaint);
    }
}
