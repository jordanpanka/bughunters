package bughunters.Grafika;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Rajz {
    protected int x;
    protected int y;

    public Rajz()
    {
        x=0;
        y=0;
    }
    public Rajz(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void Draw(Graphics g){}
    
}
