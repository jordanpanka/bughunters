package bughunters;

import java.util.List;

public class Rovarasz extends Jatekos {
    List<Rovar> rovarok;

    public List<Rovar> getRovarok() {
        return rovarok;
    }

    public void setRovarok(List<Rovar> rovarok) {
        this.rovarok = rovarok;
    }

    public Rovarasz(String nev):Jatekos(nev){

    }
    public void vag(Gombafonal gf){

    }
    public void eszik(Tekton hova){

    }
    public void maszik(Spora sp){

    }
}
