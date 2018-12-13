package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by byunw on 2018-07-14.
 */

public class CircleShape extends Shape implements Drawable
{
    private float rad;

    public CircleShape(Position p)
    {
        super(p);
    }
    public CircleShape(Position p, float r)
    {
        super(p);
        this.rad = r;
    }
    public CircleShape(Position p, float r, int c)
    {
        super(p,c);
        this.rad = r;
    }

    public float getRad()
    {
        return rad;
    }
    public void setRad(float r)
    {
        rad = r;
    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), getRad(),paint);
    }
}
