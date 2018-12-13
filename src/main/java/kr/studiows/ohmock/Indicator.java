package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by byunw on 2018-07-14.
 */

public class Indicator extends CircleShape implements Drawable
{
    private Boolean isIndicator = true;
    public Indicator(Position p, float r)
    {
        super(p,r);
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
            if(isIndicator)
            {
                paint.setColor(getColor());
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), getRad(),paint);
                //System.out.println("draw indicator" + getX() + "," + getY());
            }
            //draw
    }
    public void move(Position from, Position to)
    {
        this.setCenterPosition(to);
    }
    public Boolean getIsIndicator()
    {
        return isIndicator;
    }
    public void setIndicator(int i)
    {
        if(i%2 == 1) //홀수 : 흙돌(선)
            super.setColor(Color.argb(100, 0, 0, 0));
        else
            super.setColor(Color.argb(100, 255, 255, 255));
        this.isIndicator = true;
    }
    public void clearIndicator()
    {
        this.isIndicator = false;
    }
}
