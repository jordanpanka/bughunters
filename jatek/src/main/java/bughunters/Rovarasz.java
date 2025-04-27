package bughunters;

import java.util.ArrayList;
import java.util.List;

public class Rovarasz extends Jatekos {
    private List<Rovar> rovarok;

    public List<Rovar> getRovarok() {
        return rovarok;
    }

    public void setRovarok(List<Rovar> rovarok) {
        this.rovarok = rovarok;
    }

    public Rovarasz(String nev){
        super(nev);
        rovarok = new ArrayList<>();
    }
   
    public void addRovar(Rovar rovar) {
        rovarok.add(rovar);
    }

    public void removeRovar(Rovar rovar) {
        rovarok.remove(rovar);
    }

    public void vag(Gombafonal gf, Rovar r) throws Exception {
        try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            r.vag(gf);
            akciopontCsokkentes(1);
        } catch (Exception e) {
            throw e;
        }
        
    }

    public void maszik(Tekton hova, Rovar r) throws Exception {
       try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }

            switch (r.getAllapot()) {
                case Gyorsitott:
                    if (getakcioSzama() < 0.5) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(0.5);
                    break;
                case Lassitott:
                    if (getakcioSzama() < 2) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(2);
                    break;
                default:
                    if (getakcioSzama() < 1) {
                        throw new Exception("Nincs elegendő akciópont.");
                    }
                    r.maszik(hova);
                    akciopontCsokkentes(1);
                    break;
            }
           
        } catch (Exception e) {
            throw e;
        }
    }

    public void eszik(Spora sp, Rovar r) throws Exception {
        try {
            if(!rovarok.contains(r)){
                throw new Exception("A rovar nem tartozik a Rovarászhoz.");
            }
            if (getakcioSzama() < 1) {
                throw new Exception("Nincs elegendő akciópont.");
            }

            r.eszik(sp);
            akciopontCsokkentes(1);
            gyozelmiPontokNovelese(sp.getTapertek());
        } catch (Exception e) {
            throw e;
        }
    }

    public void rovarokAllapotIdejenekNovelese(){
        for (Rovar rovar : rovarok) {
            int ideiglenes = rovar.getAllapotIdeje();
            rovar.setAllapotIdeje(ideiglenes++);
        }
    }

    public void rovarokAlapallapotbaHelyezese(){
        for (Rovar rovar : rovarok) {
            if(rovar.getAllapotIdeje()>=1){
                rovar.alapAllapot();
            }
        }
    }

    @Override
    public void endTurnForTests(){
        this.setakcioSzama(0);
        rovarokAllapotIdejenekNovelese();
        rovarokAlapallapotbaHelyezese();
    }
}
