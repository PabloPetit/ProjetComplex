import java.util.ArrayList;

/**
 * Created by Pablo on 11/10/2015.
 */
public class Node {

    ArrayList<Tache> taches;
    AlgoExact algo;

    public Node(AlgoExact algo, ArrayList<Tache> tache){
        this.algo = algo;
        this.taches = tache;
        algo.nbNode++;

    }


    public static Node nodeGen(AlgoExact algo,int borneType,boolean borneSup,ArrayList<Tache> fait,ArrayList<Tache> pasFait,int[]dates){

        if(pasFait.isEmpty()){// On peut economiser un etage en regardant pasFait.size == 1
            int date = dates[2];
            if(algo.borneMax == -1 || date<algo.borneMax){
                algo.borneMax = date;
                algo.res = (ArrayList<Tache>) fait.clone();
                return null;
            }
        }

        Node r = new Node(algo,fait);

        for(Tache t : pasFait){

            int [] dates2 = calculDates(t,dates);

            ArrayList<Tache> fait2 = (ArrayList<Tache>) fait.clone();
            fait2.add(t);
            ArrayList<Tache> pasFait2 = (ArrayList<Tache>) pasFait.clone();
            pasFait2.remove(t);

            if(algo.borneMax==-1){
                Node f = nodeGen(algo,borneType,borneSup,fait2,pasFait2,dates2);
                if(f!=null)f.dispose();
            }
            else{
                int bInf=-1;
                switch (borneType){
                    case AlgoExact.BORNE_B1:
                        bInf = borneInfB1(dates2,pasFait2);
                        break;
                }
                if(!borneSup){
                    if(bInf<algo.borneMax){
                        Node f = nodeGen(algo,borneType,borneSup,fait2,pasFait2,dates2);
                        if(f!=null)f.dispose();
                    }
                }else{
                    ArrayList<Tache> tmp = (ArrayList<Tache>) pasFait2.clone();
                    tmp = AlgoJohnson.johnson(tmp);//
                    int bSup = calculDates(tmp,dates2)[2];
                   // System.out.println("BUP : "+bSup +" "+algo.borneMax);
                    tmp.clear();

                    if(bInf<algo.borneMax){ // ?
                        if(bInf<=bSup) {
                            Node f = nodeGen(algo, borneType, borneSup, fait2, pasFait2, dates2);
                            if (f != null) f.dispose();
                        }
                    }
                }
            }
            fait2.clear();
            pasFait2.clear();
            fait2=null;
            pasFait2=null;
        }
        //fait.clear();

        return r;
    }

    public static int[] calculDates(ArrayList<Tache> liste,int[]dates){
        int tA = dates[0], tB = dates[1], tC= dates[2];
        for(Tache t : liste){
            tA += t.tempsA;
            tB = Math.max(tB + t.tempsB, tA + t.tempsB);
            tC =  Math.max(Math.max(tC + t.tempsC, tB + t.tempsC), tA + t.tempsC);
        }
        return new int[]{tA,tB,tC};
    }

    public static int[] calculDates(Tache t, int[] dates){
        int tA = dates[0], tB = dates[1], tC= dates[2];
        tA += t.tempsA;
        tB = Math.max(tB + t.tempsB, tA + t.tempsB);
        tC =  Math.max(Math.max(tC + t.tempsC, tB + t.tempsC), tA + t.tempsC);
        return new int[]{tA,tB,tC};
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

    public static int borneInfB1(int[] dates, ArrayList<Tache> pasFait){
        int[] d = dates;
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

    public void dispose(){
        this.taches.clear();
        this.taches = null;
        this.algo = null;
    }

}
