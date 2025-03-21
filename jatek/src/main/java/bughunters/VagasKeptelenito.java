package bughunters;


/**
 * A VagasKeptelenito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes gyorsito hatást gyakorolni a rovarokra.
 */
public class VagasKeptelenito extends Spora {

    /**
     * A spóra hatást fejt ki a megadott rovarra, és nem engedi, hogy fonalat vágjon az adott rovar.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        r.vagaskeptelen();
    }
}
