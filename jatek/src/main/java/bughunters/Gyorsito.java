package bughunters;

/**
 * A Gyorsito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes gyorsito hatást gyakorolni a rovarokra.
 */
public class Gyorsito extends Spora {
    

    /**
     * Gyorsito osztály konstuktora
     */
    public Gyorsito(int t, int m, Gombafaj g)
    {
       super(t, m, g);
    }
     /**
     * A spóra hatást fejt ki a megadott rovarra, és gyorsítja  azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        System.out.println("Meghívódik a Gyorsito osztaly hatas metodusa.");
        r.gyorsito();
    }
}

