package bughunters;


/**
 * A VagasKeptelenito osztály a Spora osztály egyik egyik utódja.
 * Ez a spóra képes gyorsito hatást gyakorolni a rovarokra.
 */
public class VagasKeptelenito extends Spora {


    /**
     * VagasKeptelenito osztály konstuktora
     */
    public VagasKeptelenito(int t, int m, Gombafaj g)
    {
       super(t, m, g);
       System.out.println("Létrejött egy új VagasKeptleníto spóra:" );
    }

    public VagasKeptelenito()
    {
        System.out.println("Létrejött egy új VagasKeptleníto spóra:" );
    }
    /**
     * A spóra hatást fejt ki a megadott rovarra, és nem engedi, hogy fonalat vágjon az adott rovar.
     *
     * @param r a rovar, amelyre a spóra hatással lesz
     */
    @Override
    public void hatas(Rovar r)
    {
        System.out.println("Meghívódik a VagasKeptelenito osztaly hatas metodusa.");
        r.vagaskeptelen();
    }
}
