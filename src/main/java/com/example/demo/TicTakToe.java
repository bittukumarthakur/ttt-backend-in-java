package com.example.demo;

import java.util.Arrays;

public class TicTakToe {
    private final Players players;
    private Player currentPlayer;
    private Winner winner;
    private final String[] board = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

    TicTakToe(Players players) {
        this.players = players;
    }

    private Boolean isValidPosition(int position) {
        return position >= 0 && position < 9 && board[position].equals(" ");
    }

    private boolean isAllSymbolMatch(int[] positions, String symbol) {
        return Arrays.stream(positions).allMatch((position) -> board[position].equals(symbol));
    }

    private Winner findWinner() {
        final int[][] winningCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}};

        for (int[] winningComb : winningCombinations) {
            final boolean win = isAllSymbolMatch(winningComb, currentPlayer.symbol());
            if (win)
                return new Winner(currentPlayer, winningComb); // refactor this for loop don't return from a form room, instead use while
        }

        return new Winner(null, null);
    }

    public void mark(int position) throws Exception {
        if (!isValidPosition(position)) throw new Exception("Invalid position");

        currentPlayer = players.getCurrentPlayer();
        board[position] = currentPlayer.symbol();
        winner = findWinner();
    }

    public Status getStatus() {
        return new Status(players.getPlayersDetails(), players.currentPlayerName(), board, winner);
    }
}
