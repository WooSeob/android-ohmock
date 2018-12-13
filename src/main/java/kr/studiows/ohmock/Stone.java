package kr.studiows.ohmock;

/**
 * Created by byunw on 2018-07-14.
 */
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Stone extends CircleShape implements Drawable
{
    public String Tag = "ohMock Stone";
    private Animation createStone;
    private Animation deleteStone;
    private boolean isCreated = false;
    private Pointer mPointer;
    private int index;
    private static final int Color_BLACK = Color.BLACK;
    private static final int Color_WHITE = Color.WHITE;
    public Stone(Position p, float r, int i)
    {
        super(p,r*0.9f);
        if(i%2 == 1) //홀수 : 흙돌(선)
            super.setColor(Color_BLACK);
        else
            super.setColor(Color_WHITE);
        this.index = i;

        createStone = new Animation(getCenterPosition(), 0f, getRad(), getColor()) {    //확대 애니메이션 기존 0f -> r
            @Override
            public void draw(Canvas canvas, Paint paint)
            {
                paint.setColor(getColor());
                paint.setStyle(Paint.Style.FILL);

                canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), R, paint);
                if(R <= toR)
                    R = R + dR;
                else//애니메이션 완료
                    setEnable(false);
            }
        };
        createStone.setEnable(true);

        deleteStone = new Animation(getCenterPosition(), getRad(), 0f, getColor()){     //축소 애니메이션 기존 r -> 0f
            @Override
            public void draw(Canvas canvas, Paint paint)
            {
                paint.setColor(getColor());
                paint.setStyle(Paint.Style.FILL);

                canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), R, paint);
                if(R >= toR)
                    R = R - dR;
                else//애니메이션 완료
                {
                    setEnable(false);
                    isCreated = false;
                }

            }
        };

        mPointer = new Pointer(p,r);
        isCreated = true;
    }
    public Pointer getPointer()
    {
        return mPointer;
    }
    public void delete()
    {
        if(isCreated)
            deleteStone.setEnable(true);//삭제애니메이션활성화
    }
    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        if(isCreated)
        {
            if (createStone.isEnabled())    //생성 애니메이션o
                createStone.draw(canvas, paint);
            else
            {
                if(deleteStone.isEnabled())     //생성 애니메이션x 삭제 애니메이션o
                    deleteStone.draw(canvas, paint);
                else                            //생성 애니메이션x 삭제 애니메이션x
                {
                    paint.setColor(getColor());
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(getCenterPosition().getX(), getCenterPosition().getY(), getRad(), paint);
                }
                mPointer.draw(canvas, paint);
            }
        }
        //딜리트 할때 애니메이션이 완료되면 스톤은 사라지기때문에 그려줄 필요x
    }

}
