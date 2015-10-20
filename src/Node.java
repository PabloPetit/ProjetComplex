import java.util.ArrayList;

/**
 * Created by Pablo on 11/10/2015.
 */
public class Node {

    ArrayList<Tache> taches;
    ArrayList<Node> fils;
    AlgoExact algo;

    public Node(AlgoExact algo, ArrayList<Tache> tache,ArrayList<Node> fils){
        this.algo = algo;
        this.taches = tache;
        this.fils = fils;
        algo.nbNode++;
    }


    public static Node nodeGen(AlgoExact algo,int borneType, ArrayList<Tache> fait,ArrayList<Tache> pasFait){
        if(pasFait.isEmpty()){
            int date = calculDates(fait)[2];
            if(algo.borneMax == -1 || date<algo.borneMax){
                algo.borneMax = date;
                algo.res = fait;
                return null;
            }
        }

        Node r = new Node(algo,fait,new ArrayList<Node>());

        for(Tache t : pasFait){
            ArrayList<Tache> fait2 = (ArrayList<Tache>) fait.clone();
            fait2.add(t);
            ArrayList<Tache> pasFait2 = (ArrayList<Tache>) pasFait.clone();
            pasFait2.remove(t);

            if(algo.borneMax==-1){
                r.fils.add(nodeGen(algo,borneType,fait2,pasFait2));
            }
            else{
                int bInf=-1;
                switch (borneType){
                    case AlgoExact.BORNE_B1:
                        bInf = borneInfB1(fait2,pasFait2);
                        break;
                }
                if(bInf<algo.borneMax){
                    r.fils.add(nodeGen(algo,borneType,fait2,pasFait2));
                }
            }
        }
        return r;
    }

    public static int[] calculDates(ArrayList<Tache> liste){
        int tA = 0, tB = 0, tC= 0;
        for(Tache t : liste){
            tA += t.tempsA;
            tB = Math.max(tB + t.tempsB, tA + t.tempsB);
            tC =  Math.max(Math.max(tC + t.tempsC, tB + t.tempsC), tA + t.tempsC);
        }
        return new int[]{tA,tB,tC};
    }

    public static int borneInfB1(ArrayList<Tache> fait, ArrayList<Tache> pasFait){
        int[] d = calculDates(fait);
        int dA = d[0], dB = d[1], dC = d[2];
        int eA = 0, eB = 0, eC = 0;
        int minA = -1, minB = -1, minC = -1, minBC = -1, minAB = -1;

        for(Tache t : pasFait){
            eA += t.tempsA;
            eB +=t.tempsB;
            eC += t.tempsC;

            if(t.tempsA < minA || minA ==-1)
                minA = t.tempsA;

            if(t.tempsB < minB || minB ==-1)
                minB = t.tempsB;

            if(t.tempsB+t.tempsC<minBC || minBC == -1)
                minBC = t.tempsB+t.tempsC;

            if(t.tempsC<minC || minC == -1)
                minC = t.tempsC;

            if(t.tempsA+t.tempsB < minAB || minAB == -1)
                minAB = t.tempsA+t.tempsB;
        }
        dA += eA + minBC;
        dB = Math.max(dB,d[0]+minA) + eB + minC;
        dC = Math.max(Math.max(dC,d[1]+minB),d[0]+minAB) + eC;
        return Math.max(Math.max(dA,dB),dC);
    }

    public void printRecu(){
        System.out.println("\nNouveau noeud : "+taches.size()+" taches, "+fils.size()+" fils\n");
        for (Tache t : taches){
            System.out.println(t.toString());
        }
        for(Node f : fils){
            f.printRecu();
        }
    }
}
