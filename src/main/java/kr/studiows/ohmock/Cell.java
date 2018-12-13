package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by BYUN on 2017-02-16.
 */
public class Cell implements Drawable
{

    private Position CenterPosition;
    private float cellSize;
    private int Index;
    private Stone mStone;

    public Cell(Position pos ,float cellSize)
    {

        CenterPosition = pos; //포지션
        this.cellSize = cellSize;                                  //셀 사이즈
        this.Index = 0;                                            //인덱스 초기값 0 (빈칸)
    }

    //얻어오기
    public Stone getStone()
    {
        return mStone;
    }
    public Position getCellPoint()
    {
        return this.CenterPosition;
    }
    public int getCellIndex()
    {
        return this.Index;
    }
    //설정하기
    public void clearIndex()
    {
        if(Index != 0)
        {
            this.Index = 0; //인덱스 세팅
            mStone = null;
        }
    }

    public void deleteStone()
    {
        mStone.delete();
        clearIndex();
    }
    public boolean putStone(int paramIndex)                                 // <착수>
    {
        if(Index == 0)
        {
            this.Index = paramIndex;
            mStone = new Stone(CenterPosition, cellSize, paramIndex);

            return true;
        }
        else
        {
            //Log.i(Tag, "이미 놓인자리");
            return false;
        }
    }

    public void draw(Canvas canvas, Paint paint)                            // <드로잉> 돌 그리기
    {
        if(mStone != null)
        {
            mStone.draw(canvas, paint);
        }
    }


}
/*
public class Cell implements Drawable
{
    private String Tag = "ohMock Cell";
    public Position CenterPosition;
    public float cellSize;
    public float stoneSize;
    public float pointerSize;
    public volatile float rad = 1;
    public float dR = 3f;  //에니메이션 속도

    public int Index;
    public int stoneColor;

    private float sRectRight;
    private float sRectLeft;
    private float sRectTop;
    private float sRectBottom;

    public volatile boolean isAnimeComplete=false;
    public volatile int r=0;

    public Cell(float centerPointX ,float centerPointY ,float cellSize)
    {
        CenterPosition = new Position(centerPointX, centerPointY); //포지션
        this.cellSize = cellSize;                                  //셀 사이즈
        this.Index = 0;                                            //인덱스 초기값 0 (빈칸)
        this.stoneSize = cellSize*0.9f; //0.9 바둑알 반지름
        this.pointerSize = cellSize*0.266f;

        sRectLeft = CenterPosition.X - cellSize*0.9f;
        sRectTop = CenterPosition.Y - cellSize*0.9f;
        sRectRight = CenterPosition.X + cellSize*0.9f;
        sRectBottom = CenterPosition.Y + cellSize*0.9f;

        Log.i(Tag, "Cell 생성자 발동\npos x : " + String.valueOf(centerPointX) + "\npos y : " + String.valueOf(centerPointY));
    }

    //얻어오기
    public Position getCellPoint() {
        return this.CenterPosition;
    }

    public int getCellIndex() {
        return this.Index;
    }
    public float getStoneSize()
    {
        return this.stoneSize;
    }
    //설정하기
    public void setCellIndex(int index) {
        this.Index = index; //인덱스 세팅
    }
    public void setStoneColor(int Color)
    {
        this.stoneColor = Color;
    }

    public boolean putStone(int paramIndex)                                 // <착수>
    {
        if(Index == 0)
        {
            this.setCellIndex(paramIndex);
            if(paramIndex%2 == 1) //홀수 : 흙돌(선)
                stoneColor=Color.BLACK;
            else
                stoneColor=Color.WHITE;

            Log.i(Tag, "착수 완료! index : " + String.valueOf(this.Index));
            return true;
        }
        else
        {
            Log.i(Tag, "이미 놓인자리");

            return false;
        }
    }

    public void selectMode(Canvas canvas, Paint paint,int currentIndex)    // <드로잉> 셀렉트모드
    {
        if(currentIndex%2 == 0)
            paint.setColor(Color.WHITE);
        else
            paint.setColor(Color.BLACK);

        paint.setStyle(Paint.Style.STROKE);     //속 빈 사각형
        paint.setStrokeWidth(5f); //선굵기

        canvas.drawRect(sRectLeft, sRectTop, sRectRight, sRectBottom, paint);
    }
    public void drawBoardPoint(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(CenterPosition.X, CenterPosition.Y, pointerSize, paint);
    }

    public void drawPointer(Canvas canvas, Paint paint)                     // <드로잉> 포인터 그리기
    {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(CenterPosition.X, CenterPosition.Y, pointerSize, paint);
    }

    public void draw(Canvas canvas, Paint paint)                            // <드로잉> 돌 그리기
    {
        paint.setColor(stoneColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(CenterPosition.X, CenterPosition.Y, stoneSize, paint);
    }

    public boolean drawAnime(Canvas canvas, Paint paint)         // <드로잉> <에니메이션> 착수한돌 에니메이션효과
    {
        if (rad <= stoneSize)
        {
            paint.setColor(stoneColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(CenterPosition.X, CenterPosition.Y, rad, paint);

            rad = rad + dR;

            return true;    // while inAnimation
        }
        else
        {
            paint.setColor(stoneColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(CenterPosition.X, CenterPosition.Y, rad, paint);
            this.isAnimeComplete=true;
            return false;   // the only final drawing in Animation process
        }

    }

}*/
