import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Pablo on 28/10/2015.
 */
public class TestGraph {

    public static final int nbTest = 10;
    public static final int maxTime = 120;

    public static void runExactSup(){
        File cmT = new File("./Data/exactCM_T.dat");
        File cmN = new File("./Data/exactCM_N.dat");
        File ncT = new File("./Data/exactNC_T.dat");
        File ncN = new File("./Data/exactNC_N.dat");
        File ceT = new File("./Data/exactCE_T.dat");
        File ceN = new File("./Data/exactCE_N.dat");

        PrintWriter pwcmT,pwcmN,pwncT,pwncN,pwceT,pwceN;

        try {
            pwcmT = new PrintWriter(cmT);
            pwcmN = new PrintWriter(cmN);
            pwncT = new PrintWriter(ncT);
            pwncN = new PrintWriter(ncN);
            pwceT = new PrintWriter(ceT);
            pwceN = new PrintWriter(ceN);
        

            int nbTache = 1;
            boolean aborted = false;

            // Random Correle Machine
            while(!aborted){
                long nbNoeud  = 0;
                double time = 0;
                for(int i = 0;i<nbTest;i++){
                    ArrayList<Tache> liste = Tache.randomCorreleMachine(nbTache);
                    AlgoExact algo = new AlgoExact(liste);
                    algo.maxTime=maxTime;
                    algo.run(AlgoExact.BORNE_B1,true);

                    if(algo.avorte){
                        aborted = true;
                        break;
                    }
                    nbNoeud+=algo.nbNode;
                    time+=algo.time;
                }
                pwcmT.println(nbTache+" "+(time/1000.0/nbTest));
                pwcmN.println(nbTache+" "+(nbNoeud/nbTest));
                nbTache++;
            }
            // Random Non Correle
            nbTache=1;
            aborted = false;

            while(!aborted){
                long nbNoeud  = 0;
                double time = 0;
                for(int i = 0;i<nbTest;i++){
                    ArrayList<Tache> liste = Tache.randomNonCorrele(nbTache);
                    AlgoExact algo = new AlgoExact(liste);
                    algo.maxTime=maxTime;
                    algo.run(AlgoExact.BORNE_B1,true);

                    if(algo.avorte){
                        aborted = true;
                        break;
                    }
                    nbNoeud+=algo.nbNode;
                    time+=algo.time;
                }
                pwncT.println(nbTache+" "+(time/1000.0/nbTest));
                pwncN.println(nbTache+" "+(nbNoeud/nbTest));
                nbTache++;
            }

            // Random Correle Execution

            nbTache=1;
            aborted = false;

            while(!aborted){
                long nbNoeud  = 0;
                double time = 0;
                for(int i = 0;i<nbTest;i++){
                    ArrayList<Tache> liste = Tache.randomCorreleExec(nbTache);
                    AlgoExact algo = new AlgoExact(liste);
                    algo.maxTime=maxTime;
                    algo.run(AlgoExact.BORNE_B1,true);

                    if(algo.avorte){
                        aborted = true;
                        break;
                    }
                    nbNoeud+=algo.nbNode;
                    time+=algo.time;
                }
                pwceT.println(nbTache+" "+(time/1000.0/nbTest));
                pwceN.println(nbTache+" "+(nbNoeud/nbTest));
                nbTache++;
            }

            pwcmT.close();pwcmN.close();pwncT.close();pwncN.close();pwceT.close();pwceN.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void run(){

    }
}
