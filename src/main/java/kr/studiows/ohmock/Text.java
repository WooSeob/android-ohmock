package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.StringRes;

/**
 * Created by BYUN on 2017-04-29.
 */

public class Text implements Drawable
{
    float x;
    float y;
    String text;
    int textColor;
    float textSize;
    Paint.Align Align;

    public Text (int color, float size, Paint.Align a, float x, float y, String s)
    {
        this.textColor = color;
        this.textSize = size;
        this.Align = a;
        this.x = x;
        this.y = y;
        this.text = s;
    }
    public void setText(String s)
    {
        this. text = s;
    }
    public void setTextColor(int Color) { this.textColor = Color; }

    public void draw (Canvas canvas, Paint paint)
    {
        paint.setColor(textColor);                                                                              //status 표시
        paint.setTextSize(textSize);
        paint.setTextAlign(Align);
        canvas.drawText(text, x, y, paint);
    }

}
