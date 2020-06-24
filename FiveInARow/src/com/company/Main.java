package com.company;
import javax.management.openmbean.InvalidKeyException;
import javax.management.openmbean.InvalidOpenTypeException;
import java.util.InputMismatchException;
import java.util.Scanner;

//kell a win check, mennyit engedjen meg a validaciok
public class Main {

    public static void main(String[] args) {
	// write your code here
        String[][] gameBoard = {    {" "," "," "," "," "," "," "," "},
                                    {" "," "," "," "," "," "," "," "},
                                    {" "," "," "," "," "," "," "," "}   };
        game(gameBoard);
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

    public static void round(String player, int posX, int posY, String[][] gameBoard){
        String symbol ="X";
        if (player.equals("player")){
            symbol = "X";
        } else if(player.equals("cpu")){
            symbol = "O";
        }
        if (gameBoard[posX][posY].equals(" ")){
            gameBoard[posX][posY] = symbol;
        }
    }



    public static void pickPos(String player, String[][] gameBoard){
        printBoard(gameBoard);
        if (player.equals("player")) {
                while (true){
                    try {
                        Scanner xScanner = new Scanner(System.in);

                        System.out.println("Enter Y coordinate: ");
                        // String input
                        int posY = xScanner.nextInt() - 1;
                        Scanner yScanner = new Scanner(System.in);
                        System.out.println("Enter X coordinate: ");
                        // String input
                        int posX = yScanner.nextInt() - 1;
                        //statements that may cause an exception
                        round(player, posX, posY, gameBoard);
                        break;
                    } catch (ArrayIndexOutOfBoundsException err){
                        System.out.println("Invalid coordinate, please try again.");
                        continue;
                    } catch (InputMismatchException ie){
                        System.out.println("Invalid coordinate, please try again.");
                        continue;
                    }
                }
        }else{
            int posX = (int) (Math.random() * ((2) + 1));
            int posY = (int) (Math.random() * ((7) + 1));
            round("cpu", posX, posY, gameBoard);
        }

    }

    public static int getWinCon() throws Exception {
        Scanner winConScanner = new Scanner(System.in);
        int res = 0;
        System.out.println("Enter the number of symbols need to win(must been between 2-9): ");
        res = winConScanner.nextInt();
        if (res > 8 || res <  3) {
            throw new Exception();
        }
           return res;
    }

    public static void game(String[][] gameBoard){
        int winCon = 0;
        while (true) {
            try {
                winCon = getWinCon();
                break;
            } catch (Exception e) {
                System.out.println("wrong number!");
                continue;
            }
        }

        String player = "player";
        String winnigPlayer = "Error, String 'winningPlayer' didn't get the correct value";
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
