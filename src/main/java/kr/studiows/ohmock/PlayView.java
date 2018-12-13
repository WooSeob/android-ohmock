package kr.studiows.ohmock;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.service.carrier.CarrierService;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by BYUN on 2017-02-16.
 */
//Log.i(Tag, "onSurfaceTexture Updated");
public class PlayView extends TextureView implements TextureView.SurfaceTextureListener, View.OnTouchListener
{
    public String Tag = "ohMock PlayView";

    boolean isInit = false;
    public Thread mThread;
    public Controller mController;

    volatile private boolean mIsRunnable;

    public int ScrWidth;
    public int ScrHeight;

    volatile public float mTouchedX;
    volatile public float mTouchedY;

    public PlayView(Context context)
    {
        super(context);
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
    }
    public PlayView(Context context, AttributeSet atts)
    {
        super(context,atts);
        setSurfaceTextureListener(this);
        setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        Log.i(Tag, "터치 발생 onTouch");
        mTouchedX = event.getX();
        mTouchedY = event.getY();

        mController.touchHandler(mTouchedX, mTouchedY);
        return true;
    }
    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height)
    {
        Log.i(Tag, "onSurfaceTexture Available");
        this.ScrWidth = width;
        this.ScrHeight = height;
        initObject(width,height);
        start();
    }
    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height)
    {
        Log.i(Tag, "onSurfaceTexture Changed");
        stop();
        this.ScrWidth = width;
        this.ScrHeight = height;
        initObject(width,height);
        start();
    }
    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface)
    {
        Log.i(Tag, "onSurfaceTexture Destroyed");
        return true;
    }
    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface)
    {

    }

    public void initObject(int width, int height)
    {
        mIsRunnable = false;
        mController = null;
        mController = new Controller(width, height);
        mIsRunnable = true;
        isInit = true;

        Log.i(Tag, "initObject");
    }

    public boolean BTN_launch_event()
    {//턴이 넘어가면 true 돌을 못두거나 게임이 끝나면false
        //Log.i(Tag, "btn!_currnetIndex : " + String.valueOf(currentIndex));
        return mController.Launch();
    }
    public void start()
    {
        if(!mIsRunnable)
        {
            mThread = new Thread(new gameRendering());
            Log.i(Tag, "** Thread Start **");
            mIsRunnable = isInit;
            mThread.start();
        }
        else
        {
            //mIsRunnable이 true인데 어디선가 start를 호출한 경우
        }
    }
    public void stop()
    {
        if(mIsRunnable)
        {
            mIsRunnable = false;
            Log.i(Tag, "** Thread Stop **");
        }
    }
    class gameRendering implements Runnable
    {
        @Override
        public void run()
        {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            while(true)
            {
                long startTime = System.currentTimeMillis();

                if(mIsRunnable)
                {
                    Canvas canvas = lockCanvas();
                    if (canvas == null)
                        continue;
                    mController.draw(canvas, paint);
                    unlockCanvasAndPost(canvas);
                }
                long SleepTime = startTime - System.currentTimeMillis() + 16;
                    /*
                    60f/1000ms = 1f/16.6ms
                    ...
                    Lead time of 1 Loop of This Thread is = 16ms
                    16ms * 62.5(fps) = 1000ms (1s)
                    fps of This Thread is 62.5
                    */
                if (SleepTime > 0)
                {
                    try {
                        Thread.sleep(SleepTime);
                    }catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}