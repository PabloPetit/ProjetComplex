import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Complex {

    public static void main(String[] args){

        //Marche sur exemple sujet, sur instance.txt : 357
        // 12 tache, 100 iterations, randomCorrelleExec : 3.57983

        int nb = 50;
        int nbTache = 15; //10 taches : 0.08978 0.11441 0.09898  ||| 11 taches : 1.49376 1.43657  1.1699000000000002 renaud : 1.1712799999999999 0.82526
        double totalTime = 0;
        for(int i=0;i<nb;i++){
            ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
            //ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
            //ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);
            AlgoExact algo = new AlgoExact(liste);
            algo.run(AlgoExact.BORNE_B1,true);

            /*AlgoExact algo2 = new AlgoExact(liste);
            algo2.run(AlgoExact.BORNE_B1,false);

            for(int j=0;j<nbTache;j++){
                if(algo.res.get(j).compareTo(algo2.res.get(j))!=0){
                    System.out.println("XXXXXXXXXXXXXX");
                    break;
                }
            }*/
            System.out.println("-------------");
            totalTime+=algo.time;
            //System.gc();
        }
        System.out.println("moyenne temps : " + totalTime / nb / 1000);

        /*
        if (args.length!=1){
            System.err.println("Nombre d'arguments insuffisant");
            System.exit(1);
        }
        File f = new File(args[0]);
        if(f == null){
            System.err.println("Chemin incorrect");
            System.exit(2);
        }

        ArrayList<Tache> taches = Tache.tacheFromFile(f);

        for(int i = 0;i<taches.size();i++){
            System.out.println("Tache " + i + " A : " + taches.get(i).getTempsA() + " B " +taches.get(i).getTempsB());
        }

        ArrayList<Tache> opt = AlgoJohnson.johnson(taches);

        System.out.println("\nOPTIMAL : \n");

        for(int i = 0;i<taches.size();i++){
            System.out.println("Tache " + i + " A : " + opt.get(i).getTempsA() + " B " + opt.get(i).getTempsB());
        }
        */
    }
}
