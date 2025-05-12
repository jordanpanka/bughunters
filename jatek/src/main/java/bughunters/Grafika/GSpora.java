package bughunters.Grafika;

import java.awt.Graphics;

public class GSpora extends Rajz {
     /**
     * Kirajzolja a spórát a pályára.
     * Egy kis színes körrel jelenik meg.
     *
     * @param g a grafikus kontextus, ahová rajzolunk
     */
    public void Draw(Graphics g) {
        g.fillOval(x, y, 6, 6);   // Kis körrel jelöljük a spórát
    }
}
