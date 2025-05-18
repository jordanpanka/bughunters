package bughunters.Grafika;

import java.awt.Color;
import java.awt.Graphics;

import bughunters.Tekton.Tekton;

/**
 * A GTekton osztály egy tekton objektum grafikus megjelenítését kezeli,
 * tárolva a tekton objektumot és a hozzárendelt színt.
 */
public class GTekton extends Rajz {
    private Color szin;

    public GTekton(int x, int y, Color szin)
    {
        super(x, y);
        this.szin=szin;
    }
    public GTekton(Color szin)
    {
        super();
        this.szin=szin;
       
    }

    /**
     * Beállítja a megjelenítendő színt.
     *
     * @param szin a kívánt szín
     */
    public void setSzin(Color szin) {
        this.szin = szin;
    }

    /**
     * Visszaadja a beállított színt.
     *
     * @return a szín
     */
    public Color getSzin() {
        return szin;
    }


     /**
     * Kirajzolja a tekton objektumot a pályára a megadott színnel.
     * A rajzoláshoz a Graphics kontextust kell megadni.
     *
     * @param g a grafikus kontextus
     */
    @Override
    public void Draw(Graphics g) {
        
            g.setColor(szin);
            g.fillOval(x, y, 50, 50); 
        
    }
}
