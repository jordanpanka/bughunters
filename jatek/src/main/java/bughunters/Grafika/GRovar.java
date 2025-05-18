package bughunters.Grafika;
import java.awt.Graphics;
import java.awt.image.BufferedImage;




public class GRovar extends Rajz {
   
    private BufferedImage image;

    public GRovar()
    {
        super();
    }
     public GRovar(BufferedImage image)
    {
        super();
        this.image=image;
    }
    public GRovar(BufferedImage image, int x, int y)
    {
        super(x, y);
        this.image=image;
    }


    /**
     * Beállítja a rovar képét.
     *
     * @param image a beállítandó kép
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * Visszaadja a rovar képét.
     *
     * @return a rovar képe
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Kirajzolja a rovart a megadott Graphics objektumra az (x, y) koordinátákra.
     *
     * @param g a grafikus kontextus, amire rajzolni kell
     */
    @Override
    public void Draw(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, 40, 40, null);
        }
    }
}
