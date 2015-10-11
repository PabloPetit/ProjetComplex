import java.util.ArrayList;

/**
 * Created by Pablo on 11/10/2015.
 */
public class Node {

    ArrayList<Tache> taches;
    ArrayList<Node> fils;

    public static int nbNode = 0;

    public Node(ArrayList<Tache> tab){
        taches = tab;
        fils = new ArrayList<>();
        for (Tache t : taches){
            ArrayList<Tache> tmp = (ArrayList<Tache>) tab.clone();
            tmp.remove(t);
            fils.add(new Node(tmp));
        }
        nbNode ++;
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
