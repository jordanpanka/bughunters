package bughunters;


/**
 * @brief A FonalKezeles interfész a gombafonalak kezeléséhez szükséges metódusokat határozza meg.
 */
interface FonalKezeles {
    public void addFonal(Gombafonal gf);
    public void fonalSzakad(Gombafonal gf);
}