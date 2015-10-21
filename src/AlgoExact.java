import java.util.ArrayList;

/**
 * Created by Pablo on 15/10/2015.
 */
public class AlgoExact {

    public static final int BORNE_B1 = 0;

    ArrayList<Tache> liste;
    ArrayList<Tache> res;
    boolean borneSup;
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

    public ArrayList<Tache> run(int borneType,boolean borneSup) {
        this.borneSup = borneSup;
        //Tache[] l2 = new Tache[nbTache];
        //for(int i = 0;i<nbTache;i++)l2[i]=liste.get(i);
        start = System.currentTimeMillis();
        //NodeTab racine = NodeTab.nodeGen(this,borneType,new Tache[nbTache],l2,new int[]{0,0,0});
        Node racine = Node.nodeGen(this,borneType,borneSup,new ArrayList<Tache>(),liste,new int[]{0,0,0});
        time = System.currentTimeMillis()-start;
        datesFin = Node.calculDates(res);

        System.out.println("Date Fin : "+datesFin[2]+" Time : "+(int)(time/1000)+":"+(int)(time%1000)+"sec Nodes : "+nbNode);

        /*
        for(Tache t: res){
            System.out.println(t.toString());
        }
        */
        return null;
    }

}