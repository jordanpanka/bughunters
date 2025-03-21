package bughunters;

public class Puritekton : Tekton {

    /***
     * @brief Meghívása során exception-t dob, mivel ezen a tekton típuson nem nőhet gombatest.
     * @param gf Gombafaj:
     * @return Mindig exception, mert nem növeszthet gombatestet
     */
    public Gombatest gombatestNov(Gombafaj gf){}
}