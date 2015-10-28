import java.util.ArrayList;

/**
 * Created by Pablo on 15/10/2015.
 */
public class AlgoExact {

    public static final int BORNE_B1 = 0;
    public static final int BORNE_B2 = 1;
    public static final int BORNE_B3 = 2;

    public static final int checkTime = 10000;

    ArrayList<Tache> liste;
    int maxTime = -1;
    int borneType;
    boolean borneSup;
    boolean avorte = false;
    int borneMax;
    int mBinf=-1;

    ArrayList<Tache> res;
    double start;
    double time;


    int nbNode;
    int nbNodeTime;

    int nbTache;

    int[] datesFin;

    public AlgoExact(ArrayList<Tache> liste) {
        this.liste = liste;
        this.res = null;
        nbTache = liste.size();
        start = 0;
        nbNode = 0;
        nbNodeTime = 0;
        borneMax = -1;
    }

    public ArrayList<Tache> run(int borneType,boolean borneSup) {
        start = System.currentTimeMillis();
        this.borneSup = borneSup;
        this.borneType = borneType;

        ArrayList<Tache> tmp = AlgoJohnson.johnson(liste); // Calcul d'une premiere solution
        res = tmp;
        datesFin = Node.calculDates(tmp);
        borneMax = datesFin[2];

        Node racine = Node.nodeGen(this,new ArrayList<Tache>(),liste,new int[]{0,0,0});

        time = System.currentTimeMillis()-start;
        //datesFin = Node.calculDates(res);

        System.out.println("Date Fin : "+datesFin[2]+" Time : "+(int)(time/1000)+":"+(int)(time%1000)+"sec Nodes : "+nbNode);

        return res;
    }

    public boolean timeOk(){
        return (System.currentTimeMillis()<start+(maxTime*1000));
    }

}