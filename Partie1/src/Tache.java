import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Tache {

    private int tempsA;
    private int tempsB;
    private int tempsC;

    public Tache(int tempsA, int tempsB, int tempsC){
        this.tempsA = tempsA;
        this.tempsB = tempsB;
        this.tempsC = tempsC;
    }
    public Tache(int tempsA, int tempsB){
        this.setTempsA(tempsA);
        this.setTempsB(tempsB);
        this.tempsC = 0;
    }

    public int getTempsA() {
        return tempsA;
    }

    public void setTempsA(int tempsA) {
        this.tempsA = tempsA;
    }

    public int getTempsB() {
        return tempsB;
    }

    public void setTempsB(int tempsB) {
        this.tempsB = tempsB;
    }

    public int getTempsC() {
        return tempsC;
    }

    public void setTempsC(int tempsC) {
        this.tempsC = tempsC;
    }

    public static ArrayList<Integer> tabInt(String line){
        ArrayList<Integer> numbers = new ArrayList<>();

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(line);
        while (m.find()) {
            numbers.add(Integer.parseInt(m.group()));
        }
        return numbers;
    }

    public static ArrayList<Tache> randomGenTache(int size){
            return null;
    }


    public static ArrayList<Tache> tacheFromFile(File f){
        Scanner sc;
        ArrayList<Tache> tab = new ArrayList<>();
        try {
            sc = new Scanner(f);
            int nbTache = sc.nextInt();
            sc.nextLine();
            ArrayList<Integer> tabA = tabInt(sc.nextLine());
            ArrayList<Integer> tabB = tabInt(sc.nextLine());
            sc.close();
            for(int i=0;i<nbTache;i++){
                tab.add(new Tache(tabA.get(i),tabB.get(i)));
            }

        }catch (FileNotFoundException e){
            System.err.println("File not found");
            System.exit(3);
        }
        return  tab;
    }
}
