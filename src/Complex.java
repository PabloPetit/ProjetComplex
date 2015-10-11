import java.io.File;
import java.util.ArrayList;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Complex {

    public static void main(String[] args){

        ArrayList<Tache> tab = Tache.randomNonCorrele(3);
        Node racine = new Node(tab);
        racine.printRecu();
        System.out.println("nb Node : " + Node.nbNode);
        System.exit(0);

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

    }
}
