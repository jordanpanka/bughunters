package bughunters.Grafika;

public abstract class Rajz {
    private double x;
    private double y;
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void Draw(){}
    
}
