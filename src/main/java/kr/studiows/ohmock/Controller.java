package kr.studiows.ohmock;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by byunw on 2018-07-14.
 */

public class Controller implements Drawable
{
    public String Tag = "ohMock Controller";
    private static Board mBoard;
    boolean isAvailable = false;

    public int cursorX = 7;
    public int cursorY = 7;
    public int cursorX_prev = 7;
    public int cursorY_prev = 7;
    volatile boolean isInGame=false;
    volatile boolean isGameOver=false;
    volatile boolean isTimeOver=false;
    public static final int LAYER_INGAME = 0;
    public static final int LAYER_DIA_GAMEOVER = 1;
    public static final int LAYER_DIA_GIVEUP = 2;
    public int currentLAYER = 0;
    public static int currentIndex = 1;
    public long sum;
    public long startTime;
    public long startTime2;
    public long finishTime;
    public int fromX = 7;
    public int fromY = 7;

    public GameOverEvent mGameOverEvent;
    Queue<Integer> queGameOverDirection = new LinkedList();

    public Controller(int width, int height)
    {
        mBoard = new Board(width, height);
        isAvailable = true;
    }

    public void draw(Canvas canvas, Paint paint)
    {
        if(isAvailable)
        {
            mBoard.draw(canvas, paint);
            if(isGameOver)
            {
                mGameOverEvent.draw(canvas, paint);
            }
        }
    }
    public boolean undo()
    {
        if(currentIndex > 2)
        {
            synchronized (mBoard.dStones)
            {
                Cell c = mBoard.dStones.remove(currentIndex - 2);    //기존 stone 삭제, 포인터 해제
                c.getStone().getPointer().clearPointer();
                currentIndex--;    //현재 수 -1

                mBoard.deleteStone(c);

                c = mBoard.dStones.get(currentIndex - 2);    //삭제후 가장 최근 stone 포인터설정
                c.getStone().getPointer().setPointer();

                mBoard.mIndicator.setIndicator(currentIndex);
                mBoard.TempPointer = c.getStone().getPointer();
            }
            return true;//성공 : true
        }
        return false;//실패 : false
    }
    public boolean Launch()
    {
        //턴이 넘어가면 true 돌을 못두거나 게임이 끝나면false
        //Log.i(Tag, "btn!_currnetIndex : " + String.valueOf(currentIndex));
        if(mBoard.putStone(cursorX, cursorY, currentIndex))  //착수 성공시 (놓여있는 자리에 버튼눌러도 소용없음)
        {
            if(!checkGameOver(cursorX, cursorY))    //게임 안끝났을경우
            {
                currentIndex++;
                return true;
            }
            else                                    //오목 완성됬을경우
            {
                return false;
            }
        }
        else
            return false;
    }
    public void touchHandler(float X, float Y)
    {

        //Log.i(Tag, "터치 핸들러 호출");
        switch (currentLAYER)
        {
            case LAYER_INGAME:
                if(X > 0 && X < mBoard.BoardSize && Y > 0 && Y < mBoard.BoardSize && mBoard.unlock)
                {   //보드내부
                    //Log.i(Tag, "Board inner Event x : " + String.valueOf(X) + "\ny : " + String.valueOf(Y));

                    cursorX = (int)((X) / (mBoard.cellSize * 2));//실수형 좌표를 15x15 보드내 정수형 좌표로 환산
                    cursorY = (int)((Y) / (mBoard.cellSize * 2));

                    if(cursorX != cursorX_prev || cursorY != cursorY_prev)  //커서 변화 감지될 때만 수행
                    {
                        //Log.i(Tag, "Board inner Event 커서 변화감지");
                        cursorX_prev = cursorX;
                        cursorY_prev = cursorY;

                        sum = System.currentTimeMillis() - startTime;
                        if(sum >= 10)
                        {
                            startTime = System.currentTimeMillis();
                            mBoard.setIndicator(fromX, fromY, cursorX, cursorY, currentIndex);
                            fromX = cursorX;
                            fromY = cursorY;
                        }

                        //mBoard.setIndicator(cursorX, cursorY);

                    }
                }
        }

    }

    public boolean checkGameOver(int posX, int posY)
    {
        int[] cnt = new int[4];
        Position[] offSet = new Position[4];

        for(int i=0; i<4; i++)
            offSet[i] = new Position();

        boolean isGameOver=false;
        for(int i=(posY)-4; i<=(posY)+4; i++)// i=0; i<=15; i++) //i=(posY)-4; i<=(posY)+4; i++
        {
            try{
                if(mBoard.mCells[i][posX].getCellIndex()  != 0 && mBoard.mCells[i+1][posX].getCellIndex()  != 0 && mBoard.mCells[i][posX].getCellIndex() %2 == mBoard.mCells[i+1][posX].getCellIndex() %2)
                {
                    cnt[0]++;
                    if(offSet[0].getFlag() == false)
                        offSet[0].setPos(posX, i);
                }
                else
                {
                    if(cnt[0] < 4)
                    {
                        cnt[0] = 0; //연결 끊어지면 초기화
                        offSet[0].setFlag();
                    }
                }

                if(mBoard.mCells[posY][i].getCellIndex()  != 0 && mBoard.mCells[posY][i+1].getCellIndex()  != 0 && mBoard.mCells[posY][i].getCellIndex() %2 == mBoard.mCells[posY][i+1].getCellIndex() %2)
                {
                    cnt[1]++;
                    if(offSet[1].getFlag() == false)
                        offSet[1].setPos(i, posY);
                }
                else
                {
                    if(cnt[1] < 4)
                    {
                        cnt[1] = 0;
                        offSet[1].setFlag();
                    }
                }

                if(mBoard.mCells[i][-i + posX + posY].getCellIndex() !=0 && mBoard.mCells[i+1][-(i+1) + posX + posY].getCellIndex() !=0 && mBoard.mCells[i][-i + posX + posY].getCellIndex() %2 == mBoard.mCells[i+1][-(i+1) + posX + posY].getCellIndex() %2)
                {
                    cnt[2]++;
                    if(offSet[2].getFlag() == false)
                        offSet[2].setPos(-i + posX + posY, i);
                }
                else
                {
                    if(cnt[2] < 4)
                    {
                        cnt[2] = 0;
                        offSet[2].setFlag();
                    }
                }

                if(mBoard.mCells[i][i + posX - posY].getCellIndex() !=0 && mBoard.mCells[i+1][+(i+1) + posX - posY].getCellIndex() !=0 && mBoard.mCells[i][+i + posX - posY].getCellIndex() %2 == mBoard.mCells[i+1][+(i+1) + posX - posY].getCellIndex() %2)
                {
                    cnt[3]++;
                    if(offSet[3].getFlag() == false)
                        offSet[3].setPos(i + posX - posY, i);
                }
                else
                {
                    if(cnt[3] < 4)
                    {
                        cnt[3] = 0;
                        offSet[3].setFlag();
                    }
                }

            } catch (ArrayIndexOutOfBoundsException e){
                Log.i(Tag, "array error");
                Log.i(Tag, " i : " + String.valueOf(i));
                continue;
            }
        }

        for(int i=0; i<4; i++)
        {
            if(cnt[i]==4)
            {
                queGameOverDirection.offer(i);
                isGameOver=true;
            }
        }
        for(int i=0; i<4; i++)
        {
            Log.i(Tag, "cnt " + String.valueOf(i+1) + " : " + String.valueOf(cnt[i]));
            Log.i(Tag, "offset X" + String.valueOf(i+1) + " : " + String.valueOf(offSet[i].getX() ));
            Log.i(Tag, "offset Y" + String.valueOf(i+1) + " : " + String.valueOf(offSet[i].getY() ));
        }

        if(isGameOver)
        {
            mGameOverEvent = new GameOverEvent(mBoard.cellSize);
            Log.i(Tag, "checkGameOver : 게임오버 이벤트 발동 return : true");
            test();
            while(!queGameOverDirection.isEmpty())
            {
                int Dir = queGameOverDirection.poll();
                Position pos;
                switch (Dir)
                {
                    case 0:
                        for(int i = 0; i<5; i++)
                        {
                            //Log.i(Tag, "Dir " + String.valueOf(i+1) + " : " + String.valueOf(cnt[i]));
                            pos = mBoard.mCells[i + (int)offSet[Dir].getY() ][(int)offSet[Dir].getX() ].getCellPoint();
                            mGameOverEvent.add(pos);
                        }
                        break;
                    case 1:
                        for(int i = 0; i<5; i++)
                        {
                            pos = mBoard.mCells[(int)offSet[Dir].getY() ][i + (int)offSet[Dir].getX() ].getCellPoint();
                            mGameOverEvent.add(pos);
                        }
                        break;
                    case 2:
                        for(int i = 0; i<5; i++)
                        {
                            pos = mBoard.mCells[i + (int)offSet[Dir].getY() ][-i + (int)offSet[Dir].getX() ].getCellPoint();//+ posX + posY
                            mGameOverEvent.add(pos);
                        }
                        break;
                    case 3:
                        for(int i = 0; i<5; i++)
                        {
                            pos = mBoard.mCells[i + (int)offSet[Dir].getY() ][i + (int)offSet[Dir].getX() ].getCellPoint();//+ posX - posY
                            mGameOverEvent.add(pos);
                        }
                        break;
                }
            }
            isInGame = false;
            this.isGameOver = true;
            currentLAYER = LAYER_DIA_GAMEOVER;
            //showVictoryEffect();    //이펙트 에니메이션
            //다이얼로그
            return true;
        }


        return false;
    }

    public void test ()
    {
        String tmp = "";
        Log.i(Tag, "**************************************************");
        for(int i=0; i<15; i++)
        {
            for(int j=0; j<15; j++)
            {
                if(mBoard.mCells[i][j].getCellIndex() == 0)
                    tmp = tmp + "  -";
                else
                    tmp = tmp + "  " + String.valueOf(mBoard.mCells[i][j].getCellIndex());
            }
            Log.i(Tag, tmp);
            tmp = "";
        }
        Log.i(Tag, "**************************************************");

    }

}
