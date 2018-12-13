package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by BYUN on 2017-04-30.
 */

public class ProgressBar extends Button
{
    int COLOR_BTN_PRESSED = Color.argb(255 ,36 ,71 ,53);        // 진한녹색
    int COLOR_BTN_DEFLAULT = Color.argb(255 , 70, 137 ,102);    // 녹색
    int COLOR_BTN_LOCKED = Color.argb(255 ,167 ,163 ,126);      // 명도낮은 배경색

    float X;
    float Y;
    float Width;
    float rWidth;
    float Height;

    int TIME;
    float dX;

    int Direction;
    final int DIRECTION_toRIGHT = 1;
    final int DIRECTION_toLEFT = 0;

    public ProgressBar(float x, float y, float width, float height, int time, boolean unlock, int dir)
    {
        super(x, y, x + width, y + height);
        this.Width = width;
        this.rWidth = width;
        this.Height = height;

        this.TIME = time;
        this.dX = (width/time)/62.5f;

        this.unlock = unlock;
        this.Direction = dir;
    }

    public void initWidth()
    {
        this.rWidth = Width;
    }

    @Override
    public void drawBTN(Canvas canvas, Paint paint)
    {
        try
        {
            if(Direction == DIRECTION_toLEFT)
                canvas.drawRoundRect(btn_Left, btn_Top, btn_Left + this.rWidth, btn_Bottom, roundTip, roundTip, paint);
            else
                canvas.drawRoundRect(btn_Right - this.rWidth, btn_Top, btn_Right, btn_Bottom, roundTip, roundTip, paint);

        }
        catch (Exception e)
        {
            canvas.drawRect(btn_Left, btn_Top, btn_Right, btn_Bottom, paint);
        }

    }

    public boolean drawAnime (Canvas canvas, Paint paint)
    {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if(this.unlock)
        {   //활성화
            paint.setColor(COLOR_BTN_DEFLAULT);
            drawBTN(canvas, paint);

            if (this.rWidth > 0)
                this.rWidth = this.rWidth - dX;
            else    //타이머가 다되면
                return true;
        }

        return false;
    }
}
