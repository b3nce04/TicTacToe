package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private static void welcomeMessage() {
        System.out.println("Welcome to the text-based tic-tac-toe game!");
        System.out.println("I wish you good game!");
        System.out.println("Developed by: b3nce04");
    }

    public static void main(String[] args) {
        welcomeMessage();
        Scanner keyboard = new Scanner(System.in);
        Game game = new Game();
        game.drawTable();
        do {
            try {
                System.out.print("Where would you like to take? (1-9) ");
                int place = keyboard.nextInt();
                if (place < 1 || place > 9 || !game.isEmptyPlace(place - 1)) {
                    throw new InvalidPlaceException();
                }
                game.takePlace(place - 1);
                game.drawTable();
                if (!game.getState()) {
                    break;
                }
                game.takePlace();
                game.drawTable();
            } catch (InvalidPlaceException e) {

            } catch (InputMismatchException e) {
                System.out.println("Invalid number!");
                break;
            }
        } while (game.getState());
        System.out.println("Thanks for your play!");
    }

}
