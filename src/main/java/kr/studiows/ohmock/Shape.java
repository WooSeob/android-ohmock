package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by byunw on 2018-12-10.
 */

public class Shape
{
    private Position CenterPos;
    private int shapeColor;
    private boolean isInit = false;

    public Shape()
    {
    }
    public Shape(Position p)
    {
        this.CenterPos = p;
    }
    public Shape(Position p, int c)
    {
        this.CenterPos = p;
        this.shapeColor = c;
    }

    public void setColor(int c)
    {
        this.shapeColor = c;
    }
    public int getColor()
    {
        return this.shapeColor;
    }
    public void setInit()
    {
        this.isInit = true;
    }
    public boolean isInit()
    {
        return this.isInit;
    }
    public Position getCenterPosition()
    {
        return CenterPos;
    }

    public void setCenterPosition(Position p)
    {
        CenterPos = p;
    }
    public float getX()
    {
        return CenterPos.getX();
    }
    public float getY()
    {
        return CenterPos.getY();
    }

    public void draw(Canvas canvas, Paint paint)
    {
    }
}
