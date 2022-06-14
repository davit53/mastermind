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

        //Introductions
        introductionsPartOne();

        String name = "";
        String next = "";
        System.out.println("What is your name?");
        name = input.nextLine();
        System.out.println("Hello there, " + name + ". Are you ready for the ultimate challange?!");

        introductionsPartTwo();

        //Instructions
        instructions();

        //First Run
        oneGame();

        //If user wants to play again
        do{
            System.out.println("\nWould you like to play again?");
            System.out.println("Enter the following numbers: ");
            System.out.println("<1> Yes");
            System.out.println("<2> No");
            playAgain = input.nextLine();
            if(playAgain.equals("1")){
                oneGame();
            }
            else if(playAgain.equals("2")){
                System.out.print("\033[0;1m");
                System.out.println(textPurple + "Thank you for playing " + textReset + name + textPurple +  ", Come back for more later! " + textReset);
                break;
            }
            else{
                while(playAgain.equals("1") == false && playAgain.equals("2") == false ){
                    System.out.println("Please enter a correct number: ");
                    System.out.println("<1> Yes");
                    System.out.println("<2> No");
                    playAgain = input.nextLine();
                    if(playAgain.equals("2")){
                        System.out.println(textPurple + "Thank you for playing " + textReset + name + textPurple +  ", Come back for more later! " + textReset);
                        break;
                    }
                }
            }
        } while(playAgain.equals("1"));

        //Dsiplay amount of points
        System.out.println("\nThe Code-Maker has " + totalMakerPoints + " points!");
        System.out.println("The Code-Breaker has " + totalBreakerPoints + " points!");

        /*
        Grading System:
        F: 2 or less points
        D: 4 - 2 (not indluding)
        C: 6 -4 (not including)
        B: 10 - 6 (not including)
        A: 15 - 10 points (not including)
        */
        if(totalBreakerPoints <= 2){
            System.out.println("\nYour grade is: F");
        }
        else if(totalBreakerPoints > 2 && totalBreakerPoints <= 4){
            System.out.println("\nYour grade is: D");
        }
        else if(totalBreakerPoints > 4 && totalBreakerPoints <= 6){
            System.out.println("\nYour grade is: C");
        }
        else if(totalBreakerPoints > 6 && totalBreakerPoints <= 10){
            System.out.println("\nYour grade is: B");
        }
        else if(totalBreakerPoints > 10){
            System.out.println("\nYour grade is: A");
        }

        //Dsiplay who won
        if(totalMakerPoints < totalBreakerPoints){
            System.out.println(textCyan + "The Code-Breaker has more points! You have won!" + textReset);
        }
        else if(totalMakerPoints == totalBreakerPoints){
            System.out.println(textRed + "Wow! The Code-Breaker and Code-Maker have the same amount of points! It's a tie!" + textReset);
        }
        else if(totalBreakerPoints < totalMakerPoints){
            System.out.println(textGreen + "The Code-Maker has more points! They have won!" + textReset);
        }

    }

    public static void oneGame(){

        //Decleration of Main variables
        Scanner input = new Scanner(System.in);
        String[] code;
        String[] codeBreaker = new String[4];
        int i = 0, j = 0, numberOfTries = 0;
        Boolean verifyAnswer = false;
        int codeBreakerPoints = 0, codeMakerPoints = 0;

        //Temporary Welcoming Message
        System.out.println("\nThe colours that you can input include: ");
        System.out.println("R for Red");
        System.out.println("B for blue");
        System.out.println("O for orange");
        System.out.println("W for white");
        System.out.println("Y for yellow");
        System.out.println("G for green");
    
        /*
        Random Generated Code
        */
        code = fullColourCode();

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
                    while(codeBreaker[j].length() != 1 //Check for correct letters
                    || (codeBreaker[j].equalsIgnoreCase("R") == false
                    && codeBreaker[j].equalsIgnoreCase("B") == false
                    && codeBreaker[j].equalsIgnoreCase("O") == false
                    && codeBreaker[j].equalsIgnoreCase("W") == false
                    && codeBreaker[j].equalsIgnoreCase("Y") == false
                    && codeBreaker[j].equalsIgnoreCase("G") == false)){
                        System.out.println("----Please input a valid letter----");
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

        //Point System (1 try --> CodeMaker: 1 point CodeBreaker: 10 points)
        //              10 tries --> CodeMaker: 10 points CodeBreaker: 1 point)
        if(numberOfTries == 1){
            codeMakerPoints++;
            codeBreakerPoints+=10;
        }
        else if(numberOfTries == 2){
            codeMakerPoints+=2;
            codeBreakerPoints+=9;
        }
        else if(numberOfTries == 3){
            codeMakerPoints+=3;
            codeBreakerPoints+=8;
        }
        else if(numberOfTries == 4){
            codeMakerPoints+=4;
            codeBreakerPoints+=7;
        }
        else if(numberOfTries == 5){
            codeMakerPoints+=5;
            codeBreakerPoints+=6;
        }
        else if(numberOfTries == 6){
            codeMakerPoints+=6;
            codeBreakerPoints+=5;
        }
        else if(numberOfTries == 7){
            codeMakerPoints+=7;
            codeBreakerPoints+=4;
        }
        else if(numberOfTries == 8){
            codeMakerPoints+=8;
            codeBreakerPoints+=3;
        }
        else if(numberOfTries == 9){
            codeMakerPoints+=9;
            codeBreakerPoints+=2;
        }
        else{
            codeMakerPoints+=10;
            codeBreakerPoints++;
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
        System.out.println("Code-Maker: " + totalMakerPoints);
        System.out.println("Code-Breaker: " + totalBreakerPoints);
         
    }

    
    /*
     * Description: Generate one colour of a the code 
     * Pre: Colour is represented as a random generated number
     * Post: Colour is converted into a letter with the type String
     */
    public static void generateOneColour(String[] code, int i, Random random){

        int randomCodeNumber = random.nextInt(6);
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

    public static String[] fullColourCode(){
        
        String[] code = new String[4];
        Random random = new Random();

        /*
        Random Generated Code
        */
        //First Colour
        int  i = 0;
        int randomCodeNumber = random.nextInt(6);
        generateOneColour(code, i, random);

        //Second Colour
        i = 1;
        do{
            generateOneColour(code, i, random);
        } while(code[0].equals(code[i]));

        //Third Colour
        i = 2;
        do{
            generateOneColour(code, i, random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]));

        //Fourth Colour
        i = 3;
        do{
            generateOneColour(code, i,random);
        } while(code[0].equals(code[i]) || code[1].equals(code[i]) || code[2].equals(code[i]));

        return code;

    }

    public static void introductionsPartOne(){

        //Decleration Of Variables
        Scanner input = new Scanner(System.in);
        String name = "";
        String next = "";

        //Introduction
        System.out.print("\033[0;1m");
        System.out.println("_____________________________________________________");
        System.out.print("                ~~~~" + textPurple + " M" 
        + textGreen + "A" 
        + textRed + "S" 
        + textBlue + "T" 
        + textYellow + "E" 
        + textPurple + "R" 
        + textRed + "M"
        + textBlue + "I" 
        + textGreen + "N"
        + textYellow + "D" 
        + textReset + "\033[0;1m" +  " ~~~~");
        System.out.println("\n_____________________________________________________");
        System.out.println("Hello There and Welcome to Mastermind! ");

    }
    public static void introductionsPartTwo(){

        
        //Decleration Of Variables
        Scanner input = new Scanner(System.in);
        String name = "";
        String next = "";

        //Made so that the user is not overwhelmed with information and text
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println("------EXPLENATION------");
        System.out.println("NOTE: This is a general overview of the game.");
        System.out.println("**** 1 ****");
        System.out.println("Mastermind is a player vs player board game. It consists of the code-maker and the code-breaker. ");
        System.out.println("The code-maker takes a specified amount of colours and makes a 4 colour code. ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println("**** 2 ****");
        System.out.println("Example: the available colours are");
        System.out.print(textRed + "Red " + textReset);
        System.out.print(textGreen + "Green " + textReset);
        System.out.print(textBlue + "Blue " + textReset);
        System.out.print("Orange ");
        System.out.print(textWhite + "White " + textReset);
        System.out.print(textYellow + "Yellow " + textReset);
        System.out.print("\033[0;1m");
        System.out.println("The job of the code-breaker is to find the correct code. ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println("**** 3 ****");
        System.out.println("Pegs on the side of the board are placed to provide clues to the code-breaker.");
        System.out.println("A white coloured peg represents that the colour is in the code, but not in the right position.");
        System.out.println("A red coloured peg, represents that the colour is in the code and in the right position. ");
        System.out.println("It should be noted that there is no correlation of the location of the pegs to the location of the colours.");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println("**** 4 ****");
        System.out.println("You must use logic and reasoning to figure out which positions are correct and which are not!");
        System.out.println("If no peg is placed, that means a colour within the current code does not exist in the correct code.");
        System.out.println("You will have 10 tries to guess the code!");
        System.out.print(textReset);

    }
    
    public static void instructions(){

        Scanner input = new Scanner(System.in);

        //Decleration Of Variables
        String next = "";

        System.out.println("Before we get started, here are the official instructions for this game: ");
        System.out.println(textCyan + "1)" + textReset + " The code-maker takes 4 colours and makes a code. ");
        System.out.println("    - The available colours are red, green, blue, orange, white, and yellow.");
        System.out.println("    - They will be repreented by the fits letter of their name. E.g. Red --> 'R' ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "2)" + textReset + " The job of the code-breaker is to find the correct code. ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "3)" + textReset + " Pegs on the side of the board are placed to provide clues to the code-breaker. ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "4)" + textReset + " A white coloured peg represents that the colour is in the code, but not in the right position.");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "5)" + textReset + " A red coloured peg, represents that the colour is in the code and in the right position. ");
        System.out.println("    - It should be noted that there is no correlation of the location of the pegs to the location of the colours.");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "6)" + textReset + " Please enter the colour of the corresponding colour from left to right.");
        System.out.println("    - E.g.: If you think the code is Red, Green, Orange, White --> Enter: R G O W");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "7)" + textReset + " The letters will be inputted one by one.");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "8)" + textReset + " You will have 10 tries to guess the code! ");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "9)" + textReset + " Points will be awarded accordingly: 1 try --> CodeMaker: 1 point CodeBreaker: 10 points");
        System.out.println("    - 10 tries --> CodeMaker: 10 points CodeBreaker: 1 point");
        System.out.println("<Enter Anything To Continue>");
        next = input.nextLine();
        System.out.println(textCyan + "10)" + textReset + textPurple + " THATS IT! GOOD LUCK! " + textReset);
            
    }

    //Colour Options for text
    public static final String textReset = "\u001B[0m";
    public static final String textBlack = "\u001B[30m";
    public static final String textRed = "\u001B[31m";
    public static final String textGreen = "\u001B[32m";
    public static final String textYellow = "\u001B[33m";
    public static final String textBlue = "\u001B[34m";
    public static final String textPurple = "\u001B[35m";
    public static final String textCyan = "\u001B[36m";
    public static final String textWhite = "\u001B[37m";


}
