package kr.studiows.ohmock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by byunw on 2017-08-20.
 */

public class DefaultButton extends AppCompatButton {

    public static final int COLOR_LOCKED = Color.argb(255 ,167 ,163 ,126);      // 명도낮은 배경색
    public static final int COLOR_NORMAL = Color.argb(255 , 70, 137 ,102);      // 녹색
    public static final int COLOR_PRESSED = Color.argb(255 ,36 ,71 ,53);        // 진한녹색
    public static final int COLOR_TEXT = Color.WHITE;

    public float width;
    public float height;
    public float RoundTip;
    public Paint ButtonStyle = new Paint();
    public Paint FontStyle = new Paint();
    public String Text;
    public float FontSize;

    public boolean isInitialed = false;
    public boolean isRotated = false;

    public DefaultButton(Context context)
    {
        super(context);
        init();
    }
    public DefaultButton(Context context, AttributeSet atts)
    {
        super(context,atts);
        init();
    }
    public void init()
    {
        ButtonStyle.setAntiAlias(true);
        ButtonStyle.setStyle(Paint.Style.FILL);
        ButtonStyle.setColor(COLOR_NORMAL);

    }
    public void setRotated()
    {
        this.isRotated = true;
    }
    public void setText(String s)
    {
        this.Text = s;
        this.isInitialed = true;
    }
    public void setFontSize(float size)
    {
        this.FontSize = size;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(enabled)
            ButtonStyle.setColor(COLOR_NORMAL);
        else
            ButtonStyle.setColor(COLOR_LOCKED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                if(this.isEnabled())
                    ButtonStyle.setColor(COLOR_PRESSED);
                break;

            case MotionEvent.ACTION_UP:
                if(this.isEnabled())
                    ButtonStyle.setColor(COLOR_NORMAL);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = canvas.getWidth();
        height = canvas.getHeight();
        RoundTip = ((Math.min(width,height))*0.2f);
        setFontSize((width + height)*0.08f);

        canvas.drawRoundRect(0f, 0f, width, height, RoundTip, RoundTip, ButtonStyle);

        if(isInitialed)
        {
            FontStyle.setColor(COLOR_TEXT);
            FontStyle.setTextSize(FontSize);
            FontStyle.setTextAlign(Paint.Align.CENTER);
            if(isRotated)
            {
                canvas.save();
                canvas.rotate(180, width/2, (height/2 + FontSize/2) - FontSize/2);
                canvas.drawText(Text, width/2, (height/2 + FontSize/2), FontStyle);
                canvas.restore();
            }
            else
            {
                canvas.drawText(Text, width/2, (height/2 + FontSize/2), FontStyle);
            }
        }
    }
}
