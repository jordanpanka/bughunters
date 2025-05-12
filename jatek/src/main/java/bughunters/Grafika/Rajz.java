package bughunters.Grafika;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Rajz {
    protected int x;
    protected int y;
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    
    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void Draw(Graphics g){}
    
}
