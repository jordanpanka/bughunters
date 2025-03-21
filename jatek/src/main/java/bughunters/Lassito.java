package bughunters;


/**
 * A Lassito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes lassító hatást gyakorolni a rovarokra.
 */
public class Lassito extends Spora {
    
    /**
     * Lassito osztály konstuktora
     */
    public Lassito(int t, int m, Gombafaj g)
    {
       super(t, m, g);
    }

    /**
     * A spóra hatást fejt ki a megadott rovarra, és felgyorsítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        System.out.println("Meghívódik a Lassito osztaly hatas metodusa.");
        r.lassito();
    }
}
 