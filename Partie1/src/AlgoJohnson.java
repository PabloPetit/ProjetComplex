import java.util.ArrayList;

/**
 * Created by Pablo on 07/10/2015.
 */
public class AlgoJohnson {

    public static int findMin(ArrayList<Tache> tab){
        int min = Math.min(tab.get(0).getTempsA(),tab.get(0).getTempsB());
        int indexMin = 0;
        for(int i = 1;i<tab.size();i++){
            if(tab.get(i).getTempsA() < min){
                min = tab.get(i).getTempsA();
                indexMin = i;
            }
            if(tab.get(i).getTempsB() < min){
                min = tab.get(i).getTempsB();
                indexMin = i;
            }
        }
        return indexMin;
    }


    public static ArrayList<Tache> johnson(ArrayList<Tache> initial){
        ArrayList<Tache> tab = new ArrayList<>();
        for(Tache t : initial)tab.add(t);

        ArrayList<Tache> gauche = new ArrayList<>();
        ArrayList<Tache> droite = new ArrayList<>();

        while(!tab.isEmpty()){
            int i = findMin(tab);
            if(Math.min(tab.get(i).getTempsA(),tab.get(i).getTempsB())==tab.get(i).getTempsA()){
                gauche.add(tab.get(i));
            }else{
                droite.add(tab.get(i));
            }
            tab.remove(i);
        }
        gauche.addAll(droite);
        return gauche;
    }
}
