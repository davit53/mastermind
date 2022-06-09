/*
Author: Davit Najaryan 
Date: June 2, 2022
Description: This is the game mastermind. 
*            The user will have 10 tries to guess a colour based code made by the code-maker. 
*            If the code is guessed, the player wins.
*/

import java.util.*;

public class ICS3U_FP {
    
    public static void main(String[] args){

        //Decleration of Variables
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String[] code = new String[4];
        String[] codeBreaker = new String[4];
        int i = 0, j = 0, k = 0, temp = 0, counterForRed = 0, numberOfTries = 0;
        Boolean verifyAnswer = false;


        System.out.println("\nThe code is: ");

        /*
        Random Generated Code
        */
        //First Colour
        int randomCodeNumber = random.nextInt(6);
        generateOneColour(code, randomCodeNumber, i, random);
        System.out.print(code[i] + " ");

        //Second Colour
        i = 1;
        do{
            generateOneColour(code, randomCodeNumber, i, random);
        } while(code[0].equals(code[i]));
        System.out.print(code[i] + " ");

        //Third Colour
        i = 2;
        do{
            generateOneColour(code, randomCodeNumber, i, random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]));
        System.out.print(code[i] + " ");

        //Fourth Colour
        i = 3;
        do{
            generateOneColour(code, randomCodeNumber, i,random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]) || code[2].equals(code[i]));
        System.out.print(code[i] + " ");

        do{
            //Print based on correct or incorrect code
            if(verifyAnswer == true){
                System.out.println("\nThat is the correct code!");
            }
            else{
                //User Input
                for(j = 0; j < 4; j++){
                    System.out.println("Please enter colour of the code (only 1 letter): ");
                    codeBreaker[j] = input.next();
                    while(codeBreaker[j].length() != 1){
                        System.out.println("----Please enter only 1 letter----");
                        codeBreaker[j] = input.next();
                    }
                }
                //Make Capital
                System.out.println("Your Guess: ");
                for(j = 0; j < 4; j++){
                    codeBreaker[j] = codeBreaker[j].toUpperCase();
                    System.out.print(codeBreaker[j] + " ");
                }
                /*
                Check if correct
                */
                //Case if all mathes
                for(j = 0; j < 4; j++){
                    if(codeBreaker[j].equals(code[j])){
                        verifyAnswer = true;
                    }
                    else{
                        verifyAnswer = false;
                        break;
                    }
                }

                //Case for Red Pugs
                System.out.println("\n\n");
                for(i = 0; i < 4; i ++){
                    if(counterForRed == 0){
                        for(counterForRed = 0; counterForRed < 4; counterForRed++){
                            if(codeBreaker[counterForRed].equals(code[counterForRed])){
                                System.out.println("Red Pug");
                            }
                        }
                    }
                }
                //Case for White Pugs
                if(codeBreaker[0].equals(code[0]) == false && 
                (codeBreaker[0].equals(code[1]) 
                || codeBreaker[0].equals(code[2])
                || codeBreaker[0].equals(code[3]))){
                    System.out.println("White Pug");
                }
                if(codeBreaker[1].equals(code[1]) == false && 
                (codeBreaker[1].equals(code[0]) 
                || codeBreaker[1].equals(code[2])
                || codeBreaker[1].equals(code[3]))){
                    System.out.println("White Pug");
                }
                if(codeBreaker[2].equals(code[2]) == false && 
                (codeBreaker[2].equals(code[0]) 
                || codeBreaker[2].equals(code[1])
                || codeBreaker[2].equals(code[3]))){
                    System.out.println("White Pug");
                }
                if(codeBreaker[3].equals(code[3]) == false && 
                (codeBreaker[3].equals(code[0]) 
                || codeBreaker[3].equals(code[1])
                || codeBreaker[3].equals(code[2]))){
                    System.out.println("White Pug");
                }
            }

            numberOfTries++;
        } while(verifyAnswer == false && numberOfTries != 10);
    }


    
    /*
     * Generate one colour of a the code 
     * Pre: Colour is represented as a random generated number
     * Post: Colour is converted into a letter with the type String
     */
    public static void generateOneColour(String[] code, int randomCodeNumber, int i, Random random){

        randomCodeNumber = random.nextInt(6);
        if(randomCodeNumber  == 0){
            code[i] = "R";
        } 
        else if(randomCodeNumber  == 1){
            code[i] = "G";
        } 
        else if(randomCodeNumber  == 2){
            code[i] = "B";
        }
        else if(randomCodeNumber  == 3){
            code[i] = "O";
        }
        else if(randomCodeNumber  == 4){
            code[i] = "W";
        }
        else if(randomCodeNumber  == 5){
            code[i] = "Y";
        } 

    }
    
}