package bughunters;
public abstract class Jatekos {

    private int akcioSzama;
    private String nev;

    public Jatekos(){}
    public Jatekos(String nev){
        this.nev=nev;
    }

    public void akciopontAlapbaallit(){
        akcioSzama=3;
    }
    public void bemenetAkcio(){

    }

}
