package kr.studiows.ohmock;

/**
 * Created by BYUN on 2017-02-16.
 */

public class Position
{
    private float X;
    private float Y;
    private boolean isUsed =false;
    public Position(float x, float y)
    {
        this.X = x;
        this.Y = y;
    }
    public Position()
    {

    }
    public float getX()
    {
        return this.X;
    }
    public float getY()
    {
        return this.Y;
    }
    public void setPos(float posX, float posY)
    {
        this.X = posX;
        this.Y = posY;
        isUsed = true;
    }
    public void setFlag()
    {
        isUsed = false;
    }
    public void setFlag(boolean b)
    {
        isUsed = b;
    }

    public boolean getFlag()
    {
        return isUsed;
    }
}
