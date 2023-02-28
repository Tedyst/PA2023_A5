package org.example;

import org.example.Bonus1;

public class Main {
    public static void main(String[] args) {
//        ObligatoriuRunner();
//        LatinSquareRunner(args);

//        for (int i = 0; i < 4; i++) {
//            Bonus1 bonus1 = new Bonus1(4);
//            bonus1.Multiply(i+1);
//            bonus1.Print();
//        }

        Bonus2 bonus2 = new Bonus2(8, 3);
        if(!bonus2.Construct()){
            System.out.println("Cannot construct a matrix with these parameters");
            return;
        }
        bonus2.Print();
    }
    public static void ObligatoriuRunner() {
        String[] languages = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        n += 0b10101;
        n += 0xFF;
        n = n * 6;

        while (n > 9) {
            int sum = 0;
            while (n > 0) {
                sum = sum + (n % 10);
                n = n / 10;
            }
            n = sum;
        }

        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    public static void LatinSquareRunner(String[] args){
        if (args.length != 1){
            System.out.println("This app requires a command line argument");
            return;
        }
        int argument = 0;
        try {
            argument = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.out.println("Number is invalid");
            return;
        }
        long start_time = System.currentTimeMillis();
        LatinSquare ls = new LatinSquare(argument);
        long count = System.currentTimeMillis() - start_time;
        System.out.println("LatinSquare took " + count + " miliseconds");
        if(argument < 100)
            ls.Print();
    }
}
