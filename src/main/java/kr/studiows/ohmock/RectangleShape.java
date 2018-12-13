package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by byunw on 2018-12-10.
 */

public class RectangleShape extends Shape
{
    private float width;
    private float height;
    private Rect mRect;
    public RectangleShape(Position p, float width, float height)
    {
        super(p);
        this.width = width;
        this.height = height;
        this.mRect = new Rect();
    }
    public RectangleShape(Position p, float width, float height, int c)
    {
        super(p,c);
        this.width = width;
        this.height = height;
    }
    //정사각형일경우
    public RectangleShape(Position p, float square)
    {
        super(p);
        this.width = square;
        this.height = square;
    }
    public RectangleShape(Position p, float square, int c)
    {
        super(p,c);
        this.width = square;
        this.height = square;
    }
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(mRect, paint);
    }
}
