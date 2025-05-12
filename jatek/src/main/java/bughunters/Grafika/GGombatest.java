package bughunters.Grafika;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A GGombatest osztály felel a gombatestek grafikus megjelenítéséért.
 * Tárolja a gombatest képét és kirajzolja azt a pályára.
 * A gombafajnak megfelelően állítja be a képet
 */
public class GGombatest extends Rajz{
   private BufferedImage image;
   /**
     * Visszaadja a gombatesthez tartozó képet.
     * 
     * @return a BufferedImage kép
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Beállítja a gombatesthez tartozó képet.
     * 
     * @param image a megjelenítendő kép
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

     /**
     * Kirajzolja a gombatestet a pályára, ha a kép elérhető.
     * 
     */
    public void Draw(Graphics g) {
        if (image != null) {
            double X;
            double Y;
            g.drawImage(image,x , y, null);
        }
    }

}
