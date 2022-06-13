/*
Author: Davit Najaryan 
Date: June 2, 2022
Description: This is the game mastermind. 
*            The user will have 10 tries to guess a colour based code made by the code-maker. 
*            If the code is guessed, the player wins.
*/

//Imports 
import java.util.*;
public class ICS3U_FP {

    //Save points from previous games
    private static int totalBreakerPoints = 0;
    private static int totalMakerPoints = 0;

    public static void main(String[] args){

        //Decleration of Variables
        Scanner input = new Scanner(System.in);
        String playAgain = "";

        //First Run
        oneGame();

        //If user wants to play again
        do{
            System.out.println("Would you like to play again?");
            System.out.println("Enter the following numbers: ");
            System.out.println("<1> Yes");
            System.out.println("<2> No");
            playAgain = input.nextLine();
            if(playAgain.equals("1")){
                oneGame();
            }
            else if(playAgain.equals("2")){
                System.out.println("ENTER GOODBYE MESSAGE HERE");
                break;
            }
            else{
                while(playAgain.equals("1") == false || playAgain.equals("2") == false ){
                    System.out.println("Please enter a correct number: ");
                    System.out.println("<1> Yes");
                    System.out.println("<2> No");
                    playAgain = input.nextLine();
                }
            }
        } while(playAgain.equals("1"));

        //Dsiplay who won
        System.out.println("\nThe Code-Maker has " + totalMakerPoints + " points!");
        System.out.println("The Code-Breaker has " + totalBreakerPoints + " points!");

        if(totalMakerPoints < totalBreakerPoints){
            System.out.println("The Code-Breaker has more points! You have won!");
        }
        else if(totalMakerPoints == totalBreakerPoints){
            System.out.println("Wow! The Code-Breaker and Code-Maker have the same amount of points! It's a tie!");
        }
        else if(totalBreakerPoints < totalMakerPoints){
            System.out.println("The Code-Maker has more points! They have won!");
        }

    }

    public static void oneGame(){

        //Decleration of Main variables
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        String[] code = new String[4];
        String[] codeBreaker = new String[4];
        int i = 0, j = 0, numberOfTries = 0;
        Boolean verifyAnswer = false, checkPlayAgain = false;
        int codeBreakerPoints = 0, codeMakerPoints = 0;
        String playAgain = "";

        //Temporary Welcoming Message
        System.out.println("The colours that you can input include: ");
        System.out.println("R for Red");
        System.out.println("B for blue");
        System.out.println("O for orange");
        System.out.println("W for white");
        System.out.println("Y for yellow");
        System.out.println("G for green");
    
        /*
        Random Generated Code
        */
        //First Colour
        int randomCodeNumber = random.nextInt(6);
        generateOneColour(code, randomCodeNumber, i, random);

        //Second Colour
        i = 1;
        do{
            generateOneColour(code, randomCodeNumber, i, random);
        } while(code[0].equals(code[i]));

        //Third Colour
        i = 2;
        do{
            generateOneColour(code, randomCodeNumber, i, random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]));

        //Fourth Colour
        i = 3;
        do{
            generateOneColour(code, randomCodeNumber, i,random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]) || code[2].equals(code[i]));

        do{
            //Print if correct code
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

                //Case Pugs
                System.out.println("\n\n");
                for(int x = 0; x < 4; x++){
                    for(int y = 0; y < 4; y++){
                        if(codeBreaker[x].equals(code[y])){
                            if(x == y){
                                System.out.println("Red Pug");
                            }
                            else{
                                System.out.println("White Pug");
                            }
                        }
                    }
                }
            }

            numberOfTries++;
        } while(verifyAnswer == false && numberOfTries != 10);

        //Point System (1 try --> CodeMaker: 1 point CodeBreaker: 9 points)
        //              10 tries --> CodeMaker: 10 points CodeBreaker: 0 points)
        if(numberOfTries == 1){
            codeMakerPoints++;
            codeBreakerPoints+=9;
        }
        else if(numberOfTries == 2){
            codeMakerPoints+=2;
            codeBreakerPoints+=8;
        }
        else if(numberOfTries == 3){
            codeMakerPoints+=3;
            codeBreakerPoints+=7;
        }
        else if(numberOfTries == 4){
            codeMakerPoints+=4;
            codeBreakerPoints+=6;
        }
        else if(numberOfTries == 5){
            codeMakerPoints+=5;
            codeBreakerPoints+=5;
        }
        else if(numberOfTries == 6){
            codeMakerPoints+=6;
            codeBreakerPoints+=4;
        }
        else if(numberOfTries == 7){
            codeMakerPoints+=7;
            codeBreakerPoints+=3;
        }
        else if(numberOfTries == 8){
            codeMakerPoints+=8;
            codeBreakerPoints+=2;
        }
        else if(numberOfTries == 9){
            codeMakerPoints+=9;
            codeBreakerPoints++;
        }
        else{
            codeMakerPoints+=10;
        }
        //Add up the points
        totalBreakerPoints = totalBreakerPoints+codeBreakerPoints;   
        totalMakerPoints = totalMakerPoints+codeMakerPoints; 

        //Messages at the end
        if(numberOfTries == 10){
            System.out.println("\nYou have reached the limit of 10 tries! Game over!");
        }
        if(verifyAnswer == true){
            System.out.println("\nGreat Job! That is the correct code!");
        }

        //Display Currect Point Tally
        System.out.println("\nCurrect Point Tally: ");
        System.out.println("Code-Maker: " + codeMakerPoints);
        System.out.println("Code-Breaker: " + codeBreakerPoints);
         
    }

    
    /*
     * Description: Generate one colour of a the code 
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
