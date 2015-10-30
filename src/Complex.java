import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Complex {


    public static void testHeuristique(){
        int nb=15;
        int nbTache=10000;
        float tA1 = 0;
        float tA2 = 0;
        for(int i=0;i<nb;i++) {
            //ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
            ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
            //ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);

            AlgoExact algo = new AlgoExact(liste);
            AlgoExact algo2 = new AlgoExact(liste);

            //algo.run(AlgoExact.BORNE_B1,false);
            algo2.maxTime = 120;
            algo2.run(AlgoExact.BORNE_B1,true);

            tA1+=algo.time;
            tA2+=algo2.time;
            float gap = ((float)(algo2.datesFin[2] - algo.mBinf))/((float)algo.mBinf);
            //System.out.println("Gap : " + (int)(gap*100));
        }

        System.out.println("TM1 : "+(tA1/nb/1000)+"  TM2 : "+(tA2/nbTache/1000));
    }

    public static void main(String[] args){
       // AlgoExact algoB = new AlgoExact(Tache.tacheFromFile(new File("instance.txt")));
       // algoB.run(AlgoExact.BORNE_B1,true);
        //testHeuristique();
        TestGraph.run();
        System.exit(0);

        //Marche sur exemple sujet, sur instance.txt : 357
        // 12 tache, 100 iterations, randomCorrelleExec : 3.57983
        int nb = 50;
        int nbTache = 11; //10 taches : 0.08978 0.11441 0.09898  ||| 11 taches : 1.49376 1.43657  1.1699000000000002 renaud : 1.1712799999999999 0.82526
        double totalTime = 0;
        double totalPc=0;

        for(int i=0;i<nb;i++){
            ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
            //ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
            //ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);
            AlgoExact algo = new AlgoExact(liste);
            algo.run(AlgoExact.BORNE_B1,true);

           // AlgoExact algo2 = new AlgoExact(liste);
           // algo2.run(AlgoExact.BORNE_B3,false);

            double pc1 =  (((double)algo.nbNode)/((double)Node.maxNode(nbTache))*100.0);
          //  double pc2 =  (((double)algo2.nbNode)/((double)Node.maxNode(nbTache))*100.0);
          //  System.out.println("Rapport : "+pc1+"% B2 : "+pc2+"%");

            System.out.println("-------------");
            totalPc+=pc1;
            totalTime+=algo.time;
            //System.gc();
        }
        System.out.println("moyenne temps : " + totalTime / nb / 1000+" Pc moyen : "+totalPc/nb);

    }



}
