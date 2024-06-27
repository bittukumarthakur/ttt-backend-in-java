package com.example.demo;
public class Players {
    private final Player[] players = new Player[2];
    private int currentPlayerIndex;

    public Players(Player firstPlayer, Player secondPlayer) {
        this.players[0] = firstPlayer;
        this.players[1] = secondPlayer;
        this.currentPlayerIndex = 0;
    }

    public Player getCurrentPlayer() {
        final int index = currentPlayerIndex % 2;
        currentPlayerIndex++;
        return this.players[index];
    }

    public String getCurrentPlayerSymbol() {
        final Player player = this.players[currentPlayerIndex % 2];
        return player.symbol();

    }

    public String[] playersName() {
        return new String[]{players[0].name(), players[1].name()};
    }

    public String currentPlayerName() {
        return players[currentPlayerIndex % 2].name();
    }

    public Player[] getPlayersDetails() {
        return players;
    }
}