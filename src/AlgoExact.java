import java.util.ArrayList;

/**
 * Created by Pablo on 15/10/2015.
 */
public class AlgoExact {

    public static final int BORNE_B1 = 0;

    ArrayList<Tache> liste;
    ArrayList<Tache> res;
    int borneMax;

    double start;
    double time;
    int nbNode;

    int nbTache;

    int[] datesFin;

    public AlgoExact(ArrayList<Tache> liste) {
        this.liste = liste;
        this.res = null;
        nbTache = liste.size();
        start = 0;
        nbNode = 0;
        borneMax = -1;
    }

    public ArrayList<Tache> run(int borneType) {
        start = System.currentTimeMillis();
        Node racine = Node.nodeGen(this,borneType,new ArrayList<Tache>(0),liste);
        time = System.currentTimeMillis()-start;
        datesFin = Node.calculDates(res);
        System.out.println("Date Fin : "+datesFin[2]+" Time : "+(int)(time/1000)+":"+(int)(time%1000)+"sec Nodes : "+nbNode);

        for(Tache t: res){
            System.out.println(t.toString());
        }
        return null;
    }

}