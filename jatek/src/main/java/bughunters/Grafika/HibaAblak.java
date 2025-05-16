package bughunters.Grafika;

import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * A HibaAblak osztály felel a hibaüzenetek kiírásáért.
 * Ha egy akció végrehajtása problémába ütközik, akkor egy pop-up ablakban jeleníti meg a hibaüzenetet.
 */
public class HibaAblak extends JFrame{
    private JLabel hibaLabel;

    /**
     * A HibaAblak osztály paraméteres konstruktora, amely a hibaüzenetet
     * egy JLabel-ben jeleníti meg a képernyőn.
     * 
     * @param hiba a hibaüzenet szövege, amit ki kell írni
     */
    public HibaAblak(String hiba) {
        setTitle("Hiba");
        setSize(300, 150);  
        setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        hibaLabel = new JLabel(hiba);
        hibaLabel.setHorizontalAlignment(JLabel.CENTER); 

        add(hibaLabel);
        setVisible(true);
    
    }

   
}
