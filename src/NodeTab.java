import java.util.ArrayList;

/**
 * Created by Pablo on 11/10/2015.
 */
public class NodeTab {

    Tache[] taches;
    Node[] fils;
    AlgoExact algo;

    public NodeTab (AlgoExact algo, Tache[] tache,Node[] fils){
        this.algo = algo;
        this.taches = tache;
        this.fils = fils;
        algo.nbNode++;
    }


    public static NodeTab nodeGen(AlgoExact algo,int borneType, Tache[] fait,Tache[] pasFait,int[]dates){

        if(pasFait.length==0){
            int date = dates[2];
            if(algo.borneMax == -1 || date<algo.borneMax){
                algo.borneMax = date;
                ArrayList<Tache> res = new ArrayList<Tache>(algo.nbTache);
                for(Tache t : fait)res.add(t);
                algo.res = res;
                return null;
            }
        }

        NodeTab r = new NodeTab(algo,fait,new Node[algo.nbTache]);

        for(Tache t : pasFait){

            int [] dates2 = calculDates2(t, dates);

            Tache[] fait2 = fait.clone();
            addTab(fait2,t);
            Tache[] pasFait2 = pasFait.clone();
            removeTab(pasFait2,t);
            /*
            ArrayList<Tache> fait2 = (ArrayList<Tache>) fait.clone();
            fait2.add(t);
            ArrayList<Tache> pasFait2 = (ArrayList<Tache>) pasFait.clone();
            pasFait2.remove(t);
            */
            if(algo.borneMax==-1){
                addTab(r.fils,nodeGen(algo,borneType,fait2,pasFait2,dates2));
            }
            else{
                int bInf=-1;
                switch (borneType){
                    case AlgoExact.BORNE_B1:
                        bInf = borneInfB1(dates2,pasFait2);
                        break;
                }
                if(bInf<algo.borneMax){
                    addTab(r.fils, nodeGen(algo, borneType, fait2, pasFait2, dates2));
                }
            }
        }
        return r;
    }
    public static void removeTab(Object[] tab, Object o){
        boolean removed = false;
        for (int i =0;i<tab.length-1;i++){
            if(tab[i]==o){
                removed = true;
            }
            if(removed){
                tab[i] = tab[i++];
            }
        }
        if (removed)tab[tab.length-1]=null;
    }

    public static void addTab(Object[] tab, Object o){
        for (int i =0;i<tab.length;i++){
            if(tab[i]==null)tab[i]=o;
            break;
        }
    }

    public static int[] calculDates2(Tache t, int[] dates){
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

    public static int borneInfB1(int[] dates, Tache[] pasFait){
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

}
