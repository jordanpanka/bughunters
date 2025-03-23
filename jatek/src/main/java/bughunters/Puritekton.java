package bughunters;

public class Puritekton extends Tekton {

    /***
     * @brief Megpróbál gombatestet növeszteni
     * @param gf Gombafaj: ehhez tartozó testet próbál növeszteni
     * @throws Exception nem lehet gombatestet növeszteni
     */
    @Override
    public Gombatest gombatestNov(Gombafaj gf) throws Exception{
        System.out.println("Meghívódik a Puritekton gombatestNov metódusa.");
        throw new Exception("Erre a tektonra nem lehet gombatestet növeszteni.");
    }
}