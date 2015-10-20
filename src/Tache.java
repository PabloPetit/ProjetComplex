import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Pablo on 07/10/2015.
 */
public class Tache {

    int tempsA;
    int tempsB;
    int tempsC;

    public Tache(int tempsA, int tempsB, int tempsC){
        this.tempsA = tempsA;
        this.tempsB = tempsB;
        this.tempsC = tempsC;
    }

    @Override
    public String toString(){
       return "A : "+tempsA+" B : "+tempsB+" C : "+tempsC;
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

    public static ArrayList<Tache> randomNonCorrele(int size) {
        ArrayList<Tache> tab = new ArrayList<>();
        for(int i = 0;i<size;i++){
            tab.add(new Tache((int)(Math.random()*101),(int)(Math.random()*101),(int)(Math.random()*101)));
        }
        return tab;
    }

    public static ArrayList<Tache> randomCorreleExec(int size) {
        ArrayList<Tache> tab = new ArrayList<>();
        for(int i = 0;i<size;i++){
            int r = (int)(Math.random()*5);
            int min = 20 * r;
            tab.add(new Tache((int)(min+Math.random()*21),(int)(min+Math.random()*21),(int)(min+Math.random()*21)));
        }
        return tab;
    }

    public static ArrayList<Tache> randomCorreleMachine(int size){
        ArrayList<Tache> tab = new ArrayList<>();
        for(int i = 0;i<size;i++){
            int a = 1 + (int)(Math.random()*100);
            int b = 16 + (int)(Math.random()*100);
            int c = 31 + (int)(Math.random()*130);
            tab.add(new Tache(a,b,c));
        }
        return tab;
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
            ArrayList<Integer> tabC = tabInt(sc.nextLine());
            sc.close();
            for(int i=0;i<nbTache;i++){
                tab.add(new Tache(tabA.get(i),tabB.get(i),tabC.get(i)));
            }

        }catch (FileNotFoundException e){
            System.err.println("File not found");
            System.exit(3);
        }
        return  tab;
    }
}
