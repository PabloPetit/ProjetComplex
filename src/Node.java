import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Pablo on 11/10/2015.
 */
public class Node {

    AlgoExact algo;

    public Node(AlgoExact algo){
        this.algo = algo;
        algo.nbNode++;

    }


    public static Node nodeGen(AlgoExact algo,ArrayList<Tache> fait,ArrayList<Tache> pasFait,int[]dates){

        if(algo.maxTime>0 && algo.nbNodeTime==AlgoExact.checkTime){ //Controle temps max
            if(!algo.timeOk()){
                algo.avorte = true;
                return null;
            }
            else {
                algo.nbNodeTime = 0;
            }
        }else {
            algo.nbNodeTime++;
        }

        if(pasFait.size()==1){ // Controle feuille
            int[] date = calculDates(pasFait.get(0),dates);
            if(algo.borneMax == -1 || date[2]<algo.borneMax){
                algo.borneMax = date[2];
                algo.datesFin = date;
                ArrayList tmp = (ArrayList<Tache>) fait.clone();
                tmp.add(pasFait.get(0));
                algo.res = tmp;
                return null;
            }
        }

        Node r = new Node(algo);

        for(Tache t : pasFait){

            int [] dates2 = calculDates(t,dates);

            ArrayList<Tache> fait2 = (ArrayList<Tache>) fait.clone();
            fait2.add(t);
            ArrayList<Tache> pasFait2 = (ArrayList<Tache>) pasFait.clone();
            pasFait2.remove(t);

            if(algo.borneMax==-1){ // Pas encore de solution
                Node f = nodeGen(algo,fait2,pasFait2,dates2);
                if(f!=null)f.dispose();
            }
            else{
                int bInf=-1;

                switch (algo.borneType){
                    case AlgoExact.BORNE_B1:
                        bInf = borneInfB1(dates2,pasFait2);
                        break;
                    case AlgoExact.BORNE_B2:
                        bInf = borneInfB2(dates,pasFait2,t);
                        break;
                    case AlgoExact.BORNE_B3:
                        bInf = Math.max(Math.max(borneInfB1(dates2, pasFait2), borneInfB2(dates, pasFait2, t)),borneInfB3(dates,pasFait2,t));
                        break;
                }

                if(bInf<algo.mBinf || algo.mBinf==-1)algo.mBinf=bInf;

                if(!algo.borneSup){
                    if(bInf<algo.borneMax){
                        Node f = nodeGen(algo,fait2,pasFait2,dates2);
                        if(f!=null)f.dispose();
                    }
                }else{

                    ArrayList<Tache> tmp = (ArrayList<Tache>) pasFait2.clone();
                    tmp = AlgoJohnson.johnson(tmp);//
                    int[] bSup = calculDates(tmp,dates2);
                    /*
                    //Borne sup random :
                    ArrayList<Tache> tmp = (ArrayList<Tache>)fait2.clone();
                    ArrayList<Tache> tmp3 = (ArrayList<Tache>)pasFait2.clone();
                    //Collections.shuffle(tmp3);
                    tmp.addAll(tmp3);
                    int[] bSup = calculDates(tmp,dates2);
                    */
                    if(bSup[2]<algo.borneMax){
                        algo.borneMax = bSup[2];
                        algo.datesFin = bSup;
                        ArrayList<Tache> tmp2 = (ArrayList<Tache>) fait2.clone();
                        tmp2.addAll(tmp);
                        algo.res = tmp2;

                        //algo.res=tmp3;
                        tmp.clear();
                    }
                    //System.out.println("SUP : "+bSup+" MAX : "+algo.borneMax);
                    if(bInf<algo.borneMax){
                        if(bSup[2]<algo.borneMax*2){
                            Node f = nodeGen(algo,fait2, pasFait2, dates2);
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
        fait.clear();
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

    public static int borneInfB2(int[] dates, ArrayList<Tache> pasFait, Tache k){
        int sommeA = 0, sommeC = 0;
        for(Tache t : pasFait){
            if(t.tempsA<=t.tempsC){
                sommeA+=t.tempsA;
            }
            else {
                sommeC+=t.tempsC;
            }
        }
        return dates[0]+k.tempsA+k.tempsB+k.tempsC+sommeA+sommeC;
    }

    public static int borneInfB3(int[] dates, ArrayList<Tache> pasFait, Tache k){
        int sommeB = 0, sommeC = 0;
        for(Tache t : pasFait){
            if(t.tempsB<=t.tempsC){
                sommeB+=t.tempsB;
            }
            else {
                sommeC+=t.tempsC;
            }
        }
        return dates[1]+k.tempsB+k.tempsC+sommeB+sommeC;
    }

    public void dispose(){
        this.algo = null;
    }


    public static int maxNode(int size){
        int res = 1;
        for(int i = size;i>= 1;i--){
            int tmp = 1;
            for (int j = size;j>=i;j--){
                tmp*=j;
            }
            res+=tmp;
        }
        return res;
    }

}
