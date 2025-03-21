package bughunters;

/**
 * A Benito osztály a Spora osztály egyik utódja.
 * Ez a spóra képes bénító hatást gyakorolni a rovarokra.
 */
public class Benito extends Spora {

    /**
     * A spóra hatást fejt ki a megadott rovarra, és lebénítja azt.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r) {
        r.benit();
    }
}