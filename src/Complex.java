import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Complex {

    public static void main(String[] args){

        int nb = 20;
        int nbTache = 12;
        double totalTime = 0;
        for(int i=0;i<nb;i++){
            ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
            //ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
            //ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);
            AlgoExact algo = new AlgoExact(liste);
            algo.run(AlgoExact.BORNE_B1);
            totalTime+=algo.time;
            System.gc();
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
