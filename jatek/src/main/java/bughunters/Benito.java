package bughunters;

/**
 * A Benito osztály a Spora osztály egyik utódja.
 * Ez a spóra képes bénító hatást gyakorolni a rovarokra.
 */
public class Benito extends Spora {

    /**
     * Benito osztály konstuktora
     */
    public Benito(int t, int m, Gombafaj g)
    {
       super(t, m, g);
       System.out.println("Létrejött egy új Bénító spóra:" );
    }

    public Benito()
    {
        System.out.println("Létrejött egy új Bénító spóra:" );
    }

    /**
     * A spóra hatást fejt ki a megadott rovarra, és lebénítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r) {
        System.out.println("Meghívódik a Benito osztaly hatas metodusa.");
        r.benit();
        
    }
}