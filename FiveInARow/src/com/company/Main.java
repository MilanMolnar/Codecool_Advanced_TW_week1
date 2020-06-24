package com.company;
import javax.management.openmbean.InvalidOpenTypeException;
import java.util.Scanner;

//kell a win check, mennyit engedjen meg a validaciok
public class Main {

    public static void main(String[] args) {
	// write your code here
        String[][] gameBoard = {    {" "," "," "," "," "," "," "," "},
                                    {" "," "," "," "," "," "," "," "},
                                    {" "," "," "," "," "," "," "," "}   };
        try{
            game(gameBoard);
        }
        catch (Exception e){
            System.out.println("lol");
        }

    }
    public static void printBoard(String[][] gameBoard){
        System.out.println("-------------------------------------------------");
        System.out.print("|  ");
        for(String[] row : gameBoard){
            for(String c : row){
                System.out.print(c + "  |  ");
            }
            System.out.println();
            System.out.println("-------------------------------------------------");
            System.out.print("|  ");
        }
    }

    public static void round(String player, int posX, int posY, String[][] gameBoard) throws Exception{
        String symbol ="X";
        if (player.equals("player")){
            symbol = "X";
        } else if(player.equals("cpu")){
            symbol = "O";
        }
        if (gameBoard[posX][posY].equals(" ")){
            gameBoard[posX][posY] = symbol;
        }
        else {
            throw new Exception();
        }
        }



    public static void pickPos(String player, String[][] gameBoard) throws Exception {
        printBoard(gameBoard);
        if (player.equals("player")) {
            Scanner xScanner = new Scanner(System.in);

            System.out.println("Enter X cordinate: ");
            // String input
            int posX = xScanner.nextInt() - 1;
            Scanner yScanner = new Scanner(System.in);
            System.out.println("Enter Y cordinate: ");
            // String input
            int posY = yScanner.nextInt() - 1;
            try
            {
                //statements that may cause an exception
                round(player, posX, posY, gameBoard);
            }
            catch (Exception e)‏
            {
                //error handling code
                System.out.println("Invalid position");

            }

        }else{
            int posX = (int)(Math.random() * ((2 - 0) + 1)) + 0;
            int posY = (int)(Math.random() * ((7 - 0) + 1)) + 0;
            round("cpu", posX, posY, gameBoard);
        }

    }

    public static int getWinCon(){
        Scanner winConScanner = new Scanner(System.in);

        System.out.println("Enter the number of symbols need to win: ");
        // String input
        return winConScanner.nextInt();
    }

    public static void game(String[][] gameBoard) throws Exception{
        int winCon = getWinCon();
        String player = "player";
        String winnigPlayer = "Error, String 'winningPlayer' didnt get the correct value";
        int round = 1;
        while(true){
            if (!checkWin(gameBoard, winCon).equals("Draw")){
                winnigPlayer = checkWin(gameBoard, winCon);
                break;
            }
            if(round % 2 == 0){
                player = "cpu";
            }else{
                player = "player";
            }
            pickPos(player, gameBoard);
            round++;
        }
        printWinner(winnigPlayer);
    }
    public static String checkWin(String[][] gameBoard, int winNum){
        int countX = 0;
        int countO = 0;
        for(String[] row : gameBoard){
            for(String c : row){
                if (c.equals("X")){
                    countX++;
                    if (countX >= winNum){
                        return "X";
                    }
                    continue;
                } else{
                    countX = 0;
                }
                if (c.equals("O")){
                    countO++;
                    if (countO >= winNum){
                        return "O";
                    }
                    continue;
                } else{
                    countO = 0;
                }
            }
        }
        return "Draw";
        }
        public static void printWinner(String winner){
            System.out.println("The winner is: " + winner + "!");
        }
}
