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
    private Tekton tekton;

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
     * Beállítja a tárolt tekton objektumot.
     *
     * @param tekton a tekton objektum
     */
    public void setTekton(Tekton tekton) {
        this.tekton = tekton;
    }

    /**
     * Visszaadja a tárolt tekton objektumot.
     *
     * @return a tekton objektum
     */
    public Tekton getTekton() {
        return tekton;
    }


     /**
     * Kirajzolja a tekton objektumot a pályára a megadott színnel.
     * A rajzoláshoz a Graphics kontextust kell megadni.
     *
     * @param g a grafikus kontextus
     */
    @Override
    public void Draw(Graphics g) {
        if (tekton != null && szin != null) {
            g.setColor(szin);
            g.fillRect(x, y, 50, 50); 
        }
    }
}
