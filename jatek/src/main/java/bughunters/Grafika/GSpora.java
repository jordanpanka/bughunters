package bughunters.Grafika;

import java.awt.Color;
import java.awt.Graphics;

public class GSpora extends Rajz {

    Color szin;
    
    
    public GSpora()
    {
        super();
    }
    public GSpora(Color szin)
    {
        super();
        this.szin=szin;
    }
    public GSpora(Color szin, int x, int y)
    {
        super(x, y);
        this.szin=szin;
    }

     /**
     * Kirajzolja a spórát a pályára.
     * Egy kis színes körrel jelenik meg.
     *
     * @param g a grafikus kontextus, ahová rajzolunk
     */
    @Override
    public void Draw(Graphics g) {
        g.setColor(szin);
        g.fillOval(x, y,12, 12);   
    }
    public void szamKiir(Graphics g,int szam){
        Color uj=new Color(255-szin.getRed(),255-szin.getGreen(),255-szin.getBlue());
        g.setColor(uj);
        g.drawString(String.valueOf(szam),x+4,y+10);
    }
}
