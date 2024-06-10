/*
Varun Singh
Mastermind Game
*/

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class Mastermind {
    public static void main(String[] args) {
        String[] colors = {"r", "b", "g", "y", "p", "w"}; //the string array containing all the “colors”
        String[] masterkey = new String[4]; //the combination key
        String[] guesskey = new String[4]; //the key which takes user input
        int right = 0; //how many correct guesses
        int misplace = 0; //how many correct color but wrong order guesses
        int counter = 0; //how many attempts taken place
        for(int i = 0; i < 4; i++) { //inputting random colors inside the masterkey array
            int rnd = (int)(Math.random() * 5);
            masterkey[i] = colors[rnd];
        }
        System.out.println("");
        System.out.println("Starting mastermind game");
        System.out.println("You will try to guess the color combination in 10 attempts.");
        System.out.println("You are given black pegs for correct guesses, and white pegs for guesses in the wrong order."); //explanation of black and white peg
        System.out.println("");
        for(int x = 0; x < 10; x++){ //prompts user for their guess and then puts it inside guess key 10 times
            System.out.println("Attempt #" + (counter + 1)); //tells what attempt user is on
            System.out.println("Colors: " + Arrays.toString(colors)); //displays what colors to guess from
            System.out.println("MasterKey: [? ? ? ?]");
            //System.out.println(Arrays.toString(masterkey)); //used to test if code works correctly in test
            System.out.print("Guess: "); //where user inputs their guess
            Scanner console = new Scanner(System.in);
            for (int h = 0 ; h < guesskey.length; h++ ) { // puts user prompt into 4 slots of guess key
                guesskey[h] = console.next();
            }
            counter++; //how many attempts have been made
            check(masterkey, guesskey, right, misplace, counter); //checks guesskey with the masterkey
        }
    }
    public static void check(String[] key, String[] guess, int right, int misplace, int counter){ /*will be used to check the guesskey with the masterkey*/
        String [] tempkey = new String[4];
        String [] tempguess = new String[4];
        int cond = 0;
        for (int i = 0; i < 4; i++) { //puts the masterkey and the guess into temporary arrays
            tempkey[i] = key[i];
            tempguess[i] = guess[i];
        }
        for(int x = 0; x < key.length; x++){ //uses for loop to check if guess is 100% correct
            if(tempguess[x].equals(tempkey[x])){
                right++;
                cond++;
            }
        }
        while (cond < 4){ //when not 100% correct, loops through to find wrong order
            for(int i = 0; i < key.length;i++){
                if(tempguess[i].equals(tempkey[0]) || tempguess[i].equals(tempkey[1]) || tempguess[i].equals(tempkey[2]) || tempguess[i].equals(tempkey[3])) {
                    misplace++;
                    cond++;
                }
                cond++; //increases condition count to break out of while loop
            }
        }
        misplace -= right; //subtract all correct from the misplace variable
        if (right == 4){ //when all right passes to win method
            win(right, key);
        }
        else { //when not all right displays how many right and misplaced
            System.out.println("You got " + right + " colors right! (Black Pegs: "+right+")");
            System.out.println("You got " + misplace + " in the wrong spot! (White Pegs: "+misplace+")");
            if (counter == 10) { //if 10 attempts passed
                lose(key);
            }
            System.out.println("");
        }


    }
    public static void win (int right, String[] key) { /*the win method which displays masterkey and you win message*/
        System.out.print("The masterkey is ");
        System.out.println(Arrays.toString(key));
        System.out.println("You win!!!");
        System.out.println("Thanks for Playing!");
        System.out.println("");
        System.exit(0);
    }
    public static void lose (String[] key) { //the lose method which displays masterkey and nice try
        System.out.print("The masterkey is ");
        System.out.println(Arrays.toString(key));
        System.out.println("Nice try!");
    }
}
