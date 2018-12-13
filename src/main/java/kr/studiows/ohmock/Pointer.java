package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
/**
 * Created by byunw on 2018-07-14.
 */
public class Pointer extends CircleShape implements Drawable
{
    private Boolean isPointer = false;
    private float cellsize;
    public Pointer(Position p, float r)
    {
        super(p,r*0.266f);
        this.cellsize = r;
    }
    public int getCellX()
    {
        return (int)(getX()/cellsize*2);
    }
    public int getCellY()
    {
        return (int)(getY()/cellsize*2);
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {

            if(isPointer)
            {
                paint.setColor(Color.GREEN);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), getRad(),paint);
            }
            //draw

    }
    public Boolean getIsPointer()
    {
        return isPointer;
    }
    public void setPointer()
    {
        this.isPointer = true;
    }
    public void clearPointer()
    {
        this.isPointer = false;
    }
}
