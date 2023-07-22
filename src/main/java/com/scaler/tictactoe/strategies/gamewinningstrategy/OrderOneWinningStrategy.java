package com.scaler.tictactoe.strategies.gamewinningstrategy;

import com.scaler.tictactoe.models.Board;
import com.scaler.tictactoe.models.Cell;
import com.scaler.tictactoe.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy {
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character, Integer> topRighDiagonalSymbolCount;
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount;

    public OrderOneWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
        topLeftDiagonalSymbolCount = new HashMap<>();
        topRighDiagonalSymbolCount = new HashMap<>();
    }

    private boolean isCellOnTopRightDiagonal(int row, int col, int dimension) {
        return row + col == dimension - 1;
    }

    private boolean isCellOnTopLeftDiagonal(int row, int col, int dimension) {
        return row == col;
    }

    @Override
    public boolean checkWinner(Board board, Player playerToMove, Cell cell) {
        char symbol = cell.getPlayer().getSymbol();
        int row = cell.getRow();
        int col = cell.getCol();
        int dimension = board.getBoard().size();

        if (!rowSymbolCount.get(row).containsKey(symbol)) {
            rowSymbolCount.get(row).put(symbol, 0);
        }
        rowSymbolCount.get(row).put(symbol,
                rowSymbolCount.get(row).get(symbol) +  1);

        if (!colSymbolCount.get(col).containsKey(symbol)) {
            colSymbolCount.get(col).put(symbol, 0);
        }
        colSymbolCount.get(col).put(symbol,
                colSymbolCount.get(col).get(symbol) +  1);

        if (isCellOnTopRightDiagonal(row, col, dimension)) {
            if (!topRighDiagonalSymbolCount.containsKey(symbol)) {
                topRighDiagonalSymbolCount.put(symbol, 0);
            }
            topRighDiagonalSymbolCount.put(symbol,
                    topRighDiagonalSymbolCount.get(symbol) +  1);
        }

        if (isCellOnTopLeftDiagonal(row, col, dimension)) {
            if (!topLeftDiagonalSymbolCount.containsKey(symbol)) {
                topLeftDiagonalSymbolCount.put(symbol, 0);
            }
            topLeftDiagonalSymbolCount.put(symbol,
                    topLeftDiagonalSymbolCount.get(symbol) +  1);
        }

        if (rowSymbolCount.get(row).get(symbol) == dimension ||
                colSymbolCount.get(col).get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopLeftDiagonal(row, col, dimension) &&
                topLeftDiagonalSymbolCount.get(symbol) == dimension) {
            return true;
        }

        if (isCellOnTopRightDiagonal(row, col, dimension) &&
                topRighDiagonalSymbolCount.get(symbol) == dimension) {
            return true;
        }
        return false;
    }
}
