package bughunters;


/**
 * A Lassito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes lassító hatást gyakorolni a rovarokra.
 */
public class Lassito extends Spora {
    
    /**
     * A spóra hatást fejt ki a megadott rovarra, és felgyorsítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        r.lassito();
    }
}
 