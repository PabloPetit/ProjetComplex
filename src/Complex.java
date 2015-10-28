import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Complex {


    public static void testHeuristique(){
        int nb=50;
        int nbTache=11;
        for(int i=0;i<nb;i++) {
            ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
            //ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
            //ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);

            AlgoExact algo = new AlgoExact(liste);
            AlgoExact algo2 = new AlgoExact(liste);

            algo.run(AlgoExact.BORNE_B1,false);
            algo2.run(AlgoExact.BORNE_B1,true);

            float gap = ((float)(algo2.datesFin[2] - algo.mBinf))/((float)algo.mBinf);
            System.out.println("Gap : " + gap);
        }


    }

    public static void main(String[] args){

        testHeuristique();
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

    public static final String helpMessage =

            "Projet d'ordonnancement en COMPLEX 2015/16\n\nCatalina Assman - Pablo Petit\n\n\n" +
            "Pour utiliser le programme, entrez en ligne de commande \nune des options suivantes : \n\n" +
            "   -f <file> : pour calculer la date de fin d'un ordonnancement dans contenu dans un fichier\n" +
            "   -R <type d'instance> <nombre de tâches>\n\n" +
            "Option suplémentaires : \n\n" +
            "   -b <type> : choisir le type de borne inférieur utilisé"+
            "   -bSup : utiliser la borne supérieur"+
            "   -v : mode verbeux, résulats détaillés\n" +
            "   -s <out>: pour sauvegarder le résultats dans un fichier\n\n"+
            "Types de borne : \n"+
            "   - B1 : \n"+
            "   - B2 : \n\n"+
            "Type d'instance : \n" +
            "   - NC : Non corrélées\n" +
            "   - CM : Corrélées machines\n"+
            "   - CE : Corrélées éxecution\n";

    public static boolean v = false, bSup = false, s = false;
    public static String path = null,borneType = null,random = null,nbTache = null;

    public static void main2(String[] args) {
        if (args.length == 0) {
            System.out.println(helpMessage);
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] == "-f" && i < args.length - 1) {
                path = args[++i];
            } else if (args[i] == "-R" && i < args.length - 2) {
                if (path != null) {
                    System.err.println("Vous ne pouvez pas utiliser -f et -R en même temps.");
                    System.exit(1);
                }
                random = args[++i];
                nbTache = args[++i];
            } else if (args[i] == "-b" && i < args.length - 1) {
                borneType = args[++i];
            } else if (args[i] == "-bSup") {
                bSup = true;
            } else if (args[i] == "-v") {
                v = true;
            } else if (args[i] == "-s") {
                s = true;
            }
        }

        if(path != null){

        }

    }

}
