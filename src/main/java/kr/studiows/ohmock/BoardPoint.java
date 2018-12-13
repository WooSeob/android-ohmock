package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by byunw on 2018-07-14.
 */

public class BoardPoint implements Drawable
{
    private CircleShape[] Points;

    public float Left;
    public float Top;
    private final float ratio = 0.17f;

    public BoardPoint(float Left, float Top, float cellSize)
    {
        Points = new CircleShape[5];
        Points[0] = new CircleShape(new Position(Left + cellSize*2*3, Top + cellSize*2*3), cellSize*ratio, Color.BLACK);
        Points[1] = new CircleShape(new Position(Left + cellSize*2*3, Top + cellSize*2*11), cellSize*ratio, Color.BLACK);
        Points[2] = new CircleShape(new Position(Left + cellSize*2*11, Top + cellSize*2*3), cellSize*ratio, Color.BLACK);
        Points[3] = new CircleShape(new Position(Left + cellSize*2*11, Top + cellSize*2*11), cellSize*ratio, Color.BLACK);
        Points[4] = new CircleShape(new Position(Left + cellSize*2*7, Top + cellSize*2*7), cellSize*ratio, Color.BLACK);

    }

    public void draw(Canvas canvas, Paint paint)
    {
        for(int i = 0; i<5; i++)
        {
            Points[i].draw(canvas, paint);
        }
    }
}

