package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
/**
 * Created by BYUN on 2017-02-16.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by BYUN on 2017-02-16.
 */

public class Board implements Drawable
{
    private String Tag = "ohMock Board";
    int COLOR_BD_SCREENBG = Color.argb(255, 239,236,202);
    int COLOR_BD_BDBG = Color.argb(200 ,255 ,220 ,115);

    float BoardSize;

    static float[] BoardLines;
    static final int X = 0;
    static final int Y =1;

    boolean isAvailable = false;
    boolean unlock = true;
    public Indicator mIndicator;
    public Pointer TempPointer;
    public BoardPoint mBoardPoint;
    public Stone deletedStone;

    public Cell[][] mCells = new Cell[15][15];

    float cellSize;
    float screenWidth;
    float screenHeight;
    float Left;
    float Top;
    float Right;
    float Bottom;

    List<Cell> dStones = Collections.synchronizedList(new ArrayList<Cell>());
    //Queue<Cell> queCell = new LinkedList();

    public Board(int width, int height) //생성자, 변수 초기화
    {
        screenWidth = width;
        screenHeight = height;

        BoardSize = Math.min(screenHeight,screenWidth);              //보드 사이즈
        cellSize = BoardSize/30; //15 Lines                 //보드 한칸 사이즈 = (cellSize*2)

        Left = cellSize;
        Top = cellSize;
        Right = BoardSize - cellSize;
        Bottom = BoardSize - cellSize;

        BoardLines = new float[4*15*2];
        float BoardDrawingStep;

        for(int i = 0; i < 15*1; i++)       //일차원배열에 4개 단위로 Line 저장 => vertical한줄(4) Horizental한줄(4) => i*8
        {
            BoardDrawingStep = cellSize + cellSize*2*i;

            BoardLines[i*8 + X] = Left;
            BoardLines[i*8 + Y] = BoardDrawingStep;
            BoardLines[i*8 + X +2] = Right;
            BoardLines[i*8 + Y +2] = BoardDrawingStep;

            BoardLines[i*8 + X + 4] = BoardDrawingStep;
            BoardLines[i*8 + Y + 4] = Top;
            BoardLines[i*8 + X + 6] = BoardDrawingStep;
            BoardLines[i*8 + Y + 6] = Bottom;
        }
        mBoardPoint = new BoardPoint(Left, Top, cellSize);

        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 15; j++)
            {               //15*15 셀들 초기화
                mCells[i][j] = new Cell(new Position(Left + cellSize*2*j, Top + cellSize*2*i), cellSize);
                //Log.i(Tag, "i : " + String.valueOf(i) + " j : " + String.valueOf(j));
            }
        }
        mIndicator = new Indicator(mCells[7][7].getCellPoint(), cellSize);
        isAvailable = true;
    }

    public float getBoardsize()
    {
        return BoardSize;
    }
    public float getCellSize()
    {
        return cellSize;
    }
    public boolean putStone(int x, int y, int index)
    {
        if(mCells[y][x].putStone(index))	//돌을 두면 true 못두면 false
        {
            if(TempPointer != null) //이전에 지정해둔 포인터가 있으면
            {
                TempPointer.clearPointer(); //그 포인터를 해제하고
            }
            TempPointer = mCells[y][x].getStone().getPointer();	//새로 돌을 둔 자리를 TempPointer로 지정
            TempPointer.setPointer(); //포인터지정
            synchronized (dStones)
            {
                dStones.add(mCells[y][x]);
            }
            mIndicator.setIndicator(index+1);//controller에서 putstone을 호출후 index를 +1함
            System.out.println("putStone" + x + "," + y);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void deleteStone(Cell c)
    {
        deletedStone = c.getStone();
        c.deleteStone();
    }
    public void setIndicator(int prevX, int prevY, int X, int Y, int i)
    {
        mIndicator.setIndicator(i);
        //mIndicator.setCenterPosition(mCells[y][x].getCellPoint());
        mIndicator.move(mCells[prevY][prevX].getCellPoint(), mCells[Y][X].getCellPoint());

    }
    public void draw(Canvas canvas, Paint paint)//오목판 드로잉
    {
        if(isAvailable)
        {
            //System.out.println("draw Board");

            //----------------------------------------------------------------------------
            canvas.drawColor(COLOR_BD_SCREENBG);                                                      //흰색으로 전체 초기화

            paint.setColor(COLOR_BD_BDBG);
            paint.setStyle(Paint.Style.FILL);

            canvas.drawRect(0f, 0f, BoardSize, BoardSize, paint);         //백그라운드 꽉찬 사각형(나무색)

            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5f); //선굵기
            canvas.drawRect(Left, Top, Right, Bottom, paint); //속 굵은 테두리, 빈사각형 (BLACK)

            paint.setStrokeWidth(2f);
            //오목판 내부 Line (BLACK) 선굵기 2f

            canvas.drawLines(BoardLines, paint);
            mBoardPoint.draw(canvas ,paint);
            //Log.i(Tag, "보드 드로잉 완료");
            //-----------------------------------------------------------------------
            synchronized (dStones)
            {
                for(Cell stones : dStones)	//stones draw
                {
                    stones.draw(canvas, paint);    //includes ( Animation - stone ) pointer
                }
            }
            if(deletedStone != null)
            {
                Log.i(Tag, "삭제 스톤 드로잉");
                deletedStone.draw(canvas, paint);
            }


            mIndicator.draw(canvas ,paint);	//indicator draw
        }
        else
        {
            Log.i(Tag, "아직 초기화안됨");
        }

    }
}