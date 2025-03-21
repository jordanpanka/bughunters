package bughunters;

public class Monotekton extends Tekton {

    /***
     * @brief
     * @param gf
     * @return
     */
    @Override
    public Gombafonal gombafonalAdd(Gombafaj gf, Tekton honnan){
        System.out.println("Meghívódott a Monotekton gombafonalAdd metódusa.");
        
        Skeleton skeleton = new Skeleton();
        boolean valasz = skeleton.Kerdes("van szabad hely m1-en?");
        if(valasz){
            Gombafonal gf2 = new Gombafonal();
            addFonal(gf2);
            return gf2;
        } else {
            throw new IllegalStateException("A gombafonal nem növeszthető ebben az állapotban.");
        }
    }

    /***
     * @brief
     * @param gf
     * @return
     */
    @Override
    public Gombatest gombatestNov(Gombafaj gf){
        return new Gombatest();
    }
}