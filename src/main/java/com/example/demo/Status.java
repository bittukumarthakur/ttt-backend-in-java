package com.example.demo;

public record Status(
        Player[] players,
        String currentPlayerName,
        String[] board,
        Winner winner, boolean isGameOver) {
}
