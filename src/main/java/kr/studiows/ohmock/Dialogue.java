package kr.studiows.ohmock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.ViewOutlineProvider;

/**
 * Created by BYUN on 2017-02-28.
 */

public class Dialogue extends TextureView implements Drawable, TextureView.SurfaceTextureListener
{
    private String Tag = "ohMock ";
    int finalIndex = -1;
    public volatile boolean isAnimeComplete=false;
    public volatile int r=1;
    public volatile float dR=1;
    public volatile float rad=1;
    float dia_Left;
    float dia_Top;
    float dia_Right;
    float dia_Bottom;
    float roundTip;
    String msg;
    public Text msgVictory;

    public String str_btn_exit ;
    public String str_btn_reGame ;

    float myHeight;
    float myWidth;
    float centerX;
    float centerY;

    public final float VerticalScale = 0.1f;
    public final float HorizentalScale = 0.1f;

    Button BTN_exit;
    Button BTN_reGame;
    public Dialogue(Context context)
    {
        super(context);
        setSurfaceTextureListener(this);

    }
    public Dialogue(Context context, AttributeSet atts)
    {
        super(context,atts);
        setSurfaceTextureListener(this);
    }

    public void initOBJ()
    {

        if(finalIndex != -1) //finalIndex > -1 => 게임오버 다이얼로그
        {
            if(finalIndex%2 == 1) {           //현재 턴 홀수 : 흑돌
                msgVictory.setTextColor(Color.BLACK);
                msgVictory.setText("흑돌 승리!");
            } else {                            //현재 턴 짝수 : 백돌
                msgVictory.setTextColor(Color.WHITE);
                msgVictory.setText("백돌 승리!");
            }
            str_btn_exit = "끝내기";
            str_btn_reGame = "다시하기";

        }
        else    //기타 다이얼로그
        {
            str_btn_exit = "아니오";
            str_btn_reGame = "예";
        }

    }
    public void draw(Canvas canvas, Paint paint)
    {
        //Log.d(Tag, "GameOverScene: 에니메이션 호출");

        paint.setAntiAlias(true);
        paint.setColor(Color.argb(180, 25, 25, 25));
        canvas.drawColor(paint.getColor());

        paint.setColor(Color.argb(200, 180, 180, 180));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(dia_Left, dia_Top, dia_Right, dia_Bottom, roundTip, roundTip, paint);

        msgVictory.draw(canvas, paint);

        BTN_exit.setButtonText(str_btn_exit);
        BTN_reGame.setButtonText(str_btn_reGame);

        BTN_exit.draw(canvas, paint);
        BTN_reGame.draw(canvas, paint);

    }
    public boolean drawAnime(Canvas canvas, Paint paint)         // <드로잉> <에니메이션> 착수한돌 에니메이션효과
    {
        if (rad <= myWidth/2)
        {
            paint.setAntiAlias(true);
            paint.setColor(Color.argb(180, 25, 25, 25));
            canvas.drawColor(paint.getColor());

            paint.setColor(Color.argb(200, 180, 180, 180));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(centerX - rad, dia_Top, centerX + rad, dia_Bottom, roundTip, roundTip, paint);

            rad = rad + dR;
            dR = dR*2;
            return true;    // while inAnimation
        }
        else
        {
            paint.setAntiAlias(true);
            paint.setColor(Color.argb(180, 25, 25, 25));
            canvas.drawColor(paint.getColor());

            paint.setColor(Color.argb(200, 180, 180, 180));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRoundRect(centerX - rad, dia_Top, centerX + rad, dia_Bottom, roundTip, roundTip, paint);
            this.isAnimeComplete = true;
            return false;   // the FINAL drawing in Animation process
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }
}
