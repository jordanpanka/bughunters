package bughunters;
public abstract class Jatekos {

    private double akcioSzama;
    private String nev;
    private int gyozelmiPontok;

    //public Jatekos(){}
    public Jatekos(String nev){
        this.nev=nev;
        this.gyozelmiPontok=0;
    }

    public void setakcioSzama(double akcioSzama){
        this.akcioSzama=akcioSzama;
    }
    public double getakcioSzama(){
        return akcioSzama;
    }
    public String getNev() {
        return nev;
    }
    public void setNev(String nev) {
        this.nev = nev;
    }
    public int getGyozelmiPontok() {
        return gyozelmiPontok;
    }
    public void setGyozelmiPontok(int gyozelmiPontok) {
        this.gyozelmiPontok = gyozelmiPontok;
    }
    public void gyozelmiPontokNovelese(int novel) {
        gyozelmiPontok += novel;
    }

    public void akciopontCsokkentes(double minusz) throws Exception {
        double jelenlegiAkciopont = getakcioSzama();
        jelenlegiAkciopont = jelenlegiAkciopont - minusz;
        if (jelenlegiAkciopont < 0) {
            throw new Exception("Akciópont nem lehet negatív.");
        }
        setakcioSzama(jelenlegiAkciopont);
    }


    public void akciopontAlapbaallit(){
        akcioSzama=3;
    }
    
    public void korVege(){
        akcioSzama=0;
    }

    public void endTurnForTests(){

    }
}
