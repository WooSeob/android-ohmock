package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by byunw on 2018-07-14.
 */

public class Animation extends Shape implements Drawable
{
    private String Tag = "ohMock Animation";

    private boolean isEnabled = false;

    public float fromR;
    public float toR;
    public volatile float R;
    public float dR = 3f;//애니메이션 속도

    public Animation(Position p, float fromR, float toR, int Color)
    {
        super(p);
        this.fromR = fromR;
        this.R = fromR;
        this.toR = toR;
        setColor(Color);
    }
    public Animation(float fromR, float toR, int Color)
    {
        super();
        this.fromR = fromR;
        this.R = fromR;
        this.toR = toR;
        setColor(Color);
    }
    public boolean isEnabled()
    {
        return isEnabled;
    }
    public void setEnable(boolean b)
    {
        this.isEnabled = b;
    }
    public void setdR(float Delta)
    {
        this.dR = Delta;
    }

    public void draw(Canvas canvas, Paint paint)//확대
    {
    }

}
