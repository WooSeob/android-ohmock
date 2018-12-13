package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.StringBuilderPrinter;

/**
 * Created by BYUN on 2017-02-24.
 */
//float lengthScale = (float) 1/text.length();
//this.fontSize = (circumference*lengthScale);
//this.fontSize = (int)((btn_Bottom - btn_Top)*0.4f);
public class Button implements Drawable
{
    private String Tag = "Button";
    public int COLOR_BTN_PRESSED = Color.argb(255 ,36 ,71 ,53);        // 진한녹색
    public int COLOR_BTN_DEFLAULT = Color.argb(255 , 70, 137 ,102);    // 녹색
    public int COLOR_BTN_LOCKED = Color.argb(255 ,167 ,163 ,126);      // 명도낮은 배경색

    public float btn_Left;
    public float btn_Top;
    public float btn_Right;
    public float btn_Bottom;
    public float textX;
    public float textY;

    public boolean unlock = false;
    public boolean pressed = false;
    public boolean isRotated = false;
    public boolean conditionalUnlock = false;

    private String text;
    public float fontSize = 50;
    public float roundTip;

    public Button(float UI_btn_Left, float UI_btn_Top, float UI_btn_Right, float UI_btn_Bottom)
    {
        this.btn_Left = UI_btn_Left;
        this.btn_Top = UI_btn_Top;
        this.btn_Right = UI_btn_Right;
        this.btn_Bottom = UI_btn_Bottom;

        this.roundTip = ((btn_Bottom - btn_Top)*0.2f);
        Log.i(Tag, "버튼 객채 생성 fontsize : " + String.valueOf(this.fontSize) );
    }

    public Button(float UI_btn_Left, float UI_btn_Top, float UI_btn_Right, float UI_btn_Bottom, String text)
    {
        this.btn_Left = UI_btn_Left;
        this.btn_Top = UI_btn_Top;
        this.btn_Right = UI_btn_Right;
        this.btn_Bottom = UI_btn_Bottom;
        this.text = text;

        float circumference = ( ((btn_Bottom - btn_Top) + (btn_Right - btn_Left)) / 2);
        this.fontSize = (float) (circumference*0.2f);

        this.textX = (this.btn_Left + this.btn_Right)/2;
        this.textY =  (this.btn_Top + this.btn_Bottom)/2 + this.fontSize/2;

        this.roundTip = ((btn_Bottom - btn_Top)*0.2f);
        Log.i(Tag, "버튼 객채 생성 fontsize : " + String.valueOf(this.fontSize) );
    }
    public Button(float UI_btn_Left, float UI_btn_Top, float UI_btn_Right, float UI_btn_Bottom, boolean enable ,String text )
    {
        this.btn_Left = UI_btn_Left;
        this.btn_Top = UI_btn_Top;
        this.btn_Right = UI_btn_Right;
        this.btn_Bottom = UI_btn_Bottom;
        this.text = text;
        this.unlock = enable;

        float circumference = ( ((btn_Bottom - btn_Top) + (btn_Right - btn_Left)) / 2);
        this.fontSize = (float) (circumference*0.2f);

        this.textX = (this.btn_Left + this.btn_Right)/2;
        this.textY =  (this.btn_Top + this.btn_Bottom)/2 + this.fontSize/2;

        this.roundTip = ((btn_Bottom - btn_Top)*0.2f);
        Log.i(Tag, "버튼 객채 생성 fontsize : " + String.valueOf(this.fontSize) );
    }
    public Button(float UI_btn_Left, float UI_btn_Top, float UI_btn_Right, float UI_btn_Bottom, boolean enable, String text, float fontSize)
    {
        this.btn_Left = UI_btn_Left;
        this.btn_Top = UI_btn_Top;
        this.btn_Right = UI_btn_Right;
        this.btn_Bottom = UI_btn_Bottom;
        this.text = text;
        this.unlock = enable;
        this.fontSize = fontSize;

        this.textX = (this.btn_Left + this.btn_Right)/2;
        this.textY =  (this.btn_Top + this.btn_Bottom)/2 + this.fontSize/2;

        this.roundTip = ((btn_Bottom - btn_Top)*0.2f);
        Log.i(Tag, "버튼 객채 생성 fontsize : " + String.valueOf(this.fontSize));
    }

    public void setButtonText (String s)
    {
        this.text = s;
    }

    public void drawBTN(Canvas canvas, Paint paint)
    {
        try
        {
            canvas.drawRoundRect(btn_Left, btn_Top, btn_Right, btn_Bottom, roundTip, roundTip, paint);
        }
        catch (Exception e)
        {
            canvas.drawRect(btn_Left, btn_Top, btn_Right, btn_Bottom, paint);
        }

    }
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if(this.unlock)
        {
            if(this.pressed)
            {   //눌렸을때
                paint.setColor(COLOR_BTN_PRESSED);
                drawBTN(canvas, paint);
            }
            else
            {   //기본
                paint.setColor(COLOR_BTN_DEFLAULT);
                drawBTN(canvas, paint);
            }
        }
        else
        {   //비활성화
            paint.setColor(COLOR_BTN_LOCKED);
            drawBTN(canvas, paint);
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(fontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        if(this.isRotated)
        {
            canvas.save();
            canvas.rotate(180, textX, textY - fontSize/2);
            canvas.drawText(text, textX, textY, paint);
            canvas.restore();
        }
        else
        {
            canvas.drawText(text, textX, textY, paint);
        }
    }


    public void lockButton()
    {
        Log.i(Tag, "버튼 락 완료");
        this.unlock = false;
    }
    public void unlockButton()
    {
        Log.i(Tag, "버튼 언락 완료");
        this.unlock = true;
    }
}
