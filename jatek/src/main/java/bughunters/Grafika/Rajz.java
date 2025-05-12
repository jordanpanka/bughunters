package bughunters.Grafika;

public abstract class Rajz {
    protected int x;
    protected int y;
    public double getX() {
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

    public void Draw(){}
    
}
