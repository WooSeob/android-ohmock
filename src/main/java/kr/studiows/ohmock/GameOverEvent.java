package kr.studiows.ohmock;

/**
 * Created by byunw on 2018-07-14.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameOverEvent implements Drawable
{
    private String Tag = "ohMock GameOverEvent";
    private Animation fadeOut;
    private float cellSize;

    public List<GameOverEffect> effect = Collections.synchronizedList(new LinkedList<GameOverEffect>());

    public GameOverEvent(float cellSize)
    {
        this.cellSize = cellSize;
        fadeOut = new Animation(0f, 180f, Color.BLACK)   {//투명도 255(불투명) -> 100(반투명)
            @Override
            public void draw(Canvas canvas, Paint paint)
            {
                canvas.drawColor(Color.argb((int)R, 0, 0, 0));
                if(R <= toR)
                {
                    R = R + dR;
                    dR = dR*1.2f;
                }
                else//애니메이션 완료
                    setEnable(false);
            }
        };
        fadeOut.setdR(1.1f);
        fadeOut.setEnable(true);
    }
    public void add(Position p)
    {
        Log.i(Tag, "이펙트 추가");
        synchronized (effect)
        {
            effect.add(new GameOverEffect(p, cellSize));
        }
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        if(fadeOut.isEnabled())
            fadeOut.draw(canvas, paint);
        else
            canvas.drawColor(Color.argb(180, 0, 0, 0));

        for(GameOverEffect e : effect)	//stones draw
        {
            e.draw(canvas, paint);    //includes ( Animation - stone ) pointer
        }
    }
}
