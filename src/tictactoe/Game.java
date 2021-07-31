package tictactoe;

import java.util.ArrayList;

public class Game {

    private final char PLAYER = 'X';
    private final char AI = 'O';

    private char[] places = new char[9];
    private ArrayList<Integer> playerMarks = new ArrayList<>();
    private ArrayList<Integer> aiMarks = new ArrayList<>();

    public Game() {
        for (int i = 0; i < places.length; i++) {
            places[i] = ' ';
        }
    }

    public void drawTable() {
        System.out.println("");
        int currentIndex = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        System.out.print(places[currentIndex]);
                        currentIndex++;
                    } else {
                        System.out.print("|");
                    }
                } else {
                    if (j % 2 == 0) {
                        System.out.print("-");
                    } else {
                        System.out.print("+");
                    }
                }
            }
            System.out.println("");
        }
    }

    public boolean isEmptyPlace(int index) {
        return places[index] == ' ';
    }

    private boolean isDraw() {
        int emptyPlaces = 0;
        for (int i = 0; i < places.length; i++) {
            if (isEmptyPlace(i)) {
                emptyPlaces++;
            }
        }
        return emptyPlaces == 0;
    }

    private char getWinner() {
        if (places[0] == places[1] && places[1] == places[2] && !isEmptyPlace(0)) {
            return places[0];
        }
        if (places[3] == places[4] && places[4] == places[5] && !isEmptyPlace(3)) {
            return places[3];
        }
        if (places[6] == places[7] && places[7] == places[8] && !isEmptyPlace(6)) {
            return places[6];
        }
        if (places[0] == places[3] && places[3] == places[6] && !isEmptyPlace(0)) {
            return places[0];
        }
        if (places[1] == places[4] && places[4] == places[7] && !isEmptyPlace(1)) {
            return places[1];
        }
        if (places[2] == places[5] && places[5] == places[8] && !isEmptyPlace(2)) {
            return places[2];
        }
        if (places[0] == places[4] && places[4] == places[8] && !isEmptyPlace(0)) {
            return places[0];
        }
        if (places[2] == places[4] && places[4] == places[6] && !isEmptyPlace(2)) {
            return places[2];
        }
        return 'N';
    }

    public boolean getState() {
        if (getWinner() != 'N') {
            System.out.println("The winner is " + getWinner() + ".");
            return false;
        }
        if (isDraw()) {
            System.out.println("It's draw. :(");
            return false;
        }
        return true;
    }

    private int minimax(boolean isMaximizing) {
        if (getWinner() == AI) {
            return 1;
        }
        if (getWinner() == PLAYER) {
            return -1;
        }
        if (isDraw()) {
            return 0;
        }
        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < places.length; i++) {
                if (isEmptyPlace(i)) {
                    places[i] = AI;
                    int score = minimax(false);
                    places[i] = ' ';
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < places.length; i++) {
                if (isEmptyPlace(i)) {
                    places[i] = PLAYER;
                    int score = minimax(true);
                    places[i] = ' ';
                    bestScore = Math.min(score, bestScore);
                }
            }
            return bestScore;
        }
    }

    public void takePlace() {
        int bestMove = 0;
        int bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < places.length; i++) {
            if (isEmptyPlace(i)) {
                places[i] = AI;
                int score = minimax(false);
                places[i] = ' ';
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        if (isEmptyPlace(bestMove)) {
            if (aiMarks.size() > 2) {
                places[aiMarks.get(0)] = ' ';
                aiMarks.remove(0);
            }
            places[bestMove] = AI;
            aiMarks.add(bestMove);
        }
    }

    public void takePlace(int index) {
        if (playerMarks.size() > 2) {
            places[playerMarks.get(0)] = ' ';
            playerMarks.remove(0);
        }
        places[index] = PLAYER;
        playerMarks.add(index);
    }
}
