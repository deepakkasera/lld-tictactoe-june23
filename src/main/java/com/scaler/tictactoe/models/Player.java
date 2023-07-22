package com.scaler.tictactoe.models;

import java.util.Scanner;

public class Player {
    private char symbol;
    private String name;
    private PlayerType type;

    public Player(String name, char ch, PlayerType playerType) {
        this.name = name;
        this.symbol = ch;
        this.type = playerType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public Move decideMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter row for the move : ");
        int row = scanner.nextInt();

        System.out.println("Please enter col for the move : ");
        int col = scanner.nextInt();

        return new Move(this, new Cell(this, row, col, CellState.FILLED));
    }
}
