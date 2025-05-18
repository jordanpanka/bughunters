package bughunters.Grafika;


import java.awt.Color;
import java.awt.Graphics;

/**
 * A GGombafonal osztály egy gombafonal grafikus megjelenítéséért felel.
 * Tárolja a gombafonal két végpontjának koordinátáit és a megjelenítési színét.
 * A megjelenítést a Draw metódus végzi.
 */
public class GGombafonal extends Rajz {
    private Color szin;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public GGombafonal(Color szin, int x1, int y1, int x2, int y2)
    {
        super();
        this.szin= szin;
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
    }
    public GGombafonal(Color szin)
    {
        this.szin=szin;
        this.x1=0;
        this.x2=0;
        this.y1=0;
        this.y2=0;
    }
    public GGombafonal()
    {
        this.x1=0;
        this.x2=0;
        this.y1=0;
        this.y2=0;
    }

    /**
     * Beállítja az első végpont X koordinátáját.
     *
     * @param x az első végpont X koordinátája
     */
    public void setX1(int x) {
        x1 = x;
    }

    /**
     * Visszaadja az első végpont X koordinátáját.
     *
     * @return az első végpont X koordinátája
     */
    public int getX1() {
        return x1;
    }

    /**
     * Beállítja az első végpont Y koordinátáját.
     *
     * @param y az első végpont Y koordinátája
     */
    public void setY1(int y) {
        y1 = y;
    }

    /**
     * Visszaadja az első végpont Y koordinátáját.
     *
     * @return az első végpont Y koordinátája
     */
    public int getY1() {
        return y1;
    }

    /**
     * Beállítja a második végpont X koordinátáját.
     *
     * @param x a második végpont X koordinátája
     */
    public void setX2(int x) {
        this.x2 = x;
    }

    /**
     * Visszaadja a második végpont X koordinátáját.
     *
     * @return a második végpont X koordinátája
     */
    public int getX2() {
        return x2;
    }

    /**
     * Beállítja a második végpont Y koordinátáját.
     *
     * @param y a második végpont Y koordinátája
     */
    public void setY2(int y) {
        y2 = y;
    }

    /**
     * Visszaadja a második végpont Y koordinátáját.
     *
     * @return a második végpont Y koordinátája
     */
    public int getY2() {
        return y2;
    }

    /**
     * Beállítja a gombafonal színét.
     *
     * @param szin a kirajzoláshoz használt szín
     */
    public void setSzin(Color szin) {
        this.szin = szin;
    }

    /**
     * Visszaadja a gombafonal színét.
     *
     * @return a szín
     */
    public Color getSzin() {
        return szin;
    }

    /**
     * Kirajzolja a gombafonalat a megadott grafikus kontextusra.
     *
     * @param g a rajzoláshoz használt grafikus elem
     */
   @Override
    public void Draw(Graphics g) {
        if (szin != null) {
            g.setColor(szin);
            g.drawLine(x1, y1, x2, y2);
        }
    }
}
