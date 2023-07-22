package com.scaler.tictactoe;

import com.scaler.tictactoe.models.*;
import controllers.GameController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the dimension of the game : ");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bot ? y/n");
        String isBot = scanner.next();
        List<Player> players = new ArrayList<>();

        int numberOfHumanPlayers = dimension - 1;
        if (isBot.equals("y"))  {
            numberOfHumanPlayers = numberOfHumanPlayers - 1;

            System.out.println("What is the name of the bot ? ");
            String name = scanner.next();

            System.out.println("What is the symbol of the bot: ");
            String symbol = scanner.next();

            //String difficultyLevel = scanner.next();

            //Get the difficulty level.
            players.add(new Bot(name, symbol.charAt(0), BotDifficultyLevel.EASY));
        }

        for (int i = 0; i < numberOfHumanPlayers; i++) {
            System.out.println("What is the name of the player: " + (i+1));
            String name = scanner.next();

            System.out.println("What is the symbol of the player: " + (i+1));
            String symbol = scanner.next();

            players.add(new Player(name, symbol.charAt(0), PlayerType.HUMAN));
        }

        //Create the Game.
//        Game game = Game.getBuilder().setDimension(dimension)
//                                     .setPlayers(players)
//                                     .build();

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players);

        //Players will start playing the game.
        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the current board : ");
            gameController.displayBoard(game);

            System.out.println("Do you want to undo ? y/n");
            String input = scanner.next();

            if (input.equals("y")) {
                gameController.undo(game);
            } else {
                gameController.executeNextMove(game);
            }
        }

        //Someone has won the game or the game is draw.
        System.out.println("Game has ENDED");
        if (game.getGameStatus().equals(GameStatus.ENDED)) {
            System.out.println("Winner is " + gameController.getWinner(game).getName());
        }

    }
}

//UNDO Steps :- [Assignment]
/*
1. Remove the last move for the list of moves.
2. Update the board as well.
3. Update the hashmaps.
4. Update the currentPlayer index as well.
 */