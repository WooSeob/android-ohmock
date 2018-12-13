package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by byunw on 2018-12-11.
 */

public class GameOverEffect extends CircleShape
{
    private Animation GameOverAnimation;

    public GameOverEffect(Position p, float cellSize)
    {
        super(p, cellSize);
        GameOverAnimation = new Animation(p, 180f, cellSize, Color.RED) {
            @Override
            public void draw(Canvas canvas, Paint paint)
            {
                paint.setColor(getColor());
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(7f);
                canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), R, paint);
                if(R >= toR*0.9f)
                {
                    R = R - dR;
                    dR = dR*1.001f;
                }
                else//애니메이션 완료
                    setEnable(false);

            }
        };
        GameOverAnimation.setdR(5f);
        GameOverAnimation.setEnable(true);
    }
    public void draw(Canvas canvas, Paint paint)
    {
        if(GameOverAnimation.isEnabled())
        {
            GameOverAnimation.draw(canvas, paint);
        }
        else
        {
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(7f);
            canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), getRad(), paint);
        }
    }
}
