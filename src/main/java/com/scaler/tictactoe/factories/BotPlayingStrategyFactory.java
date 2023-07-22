package com.scaler.tictactoe.factories;

import com.scaler.tictactoe.models.BotDifficultyLevel;
import com.scaler.tictactoe.strategies.botplayingstrategy.BotPlayingStrategy;
import com.scaler.tictactoe.strategies.botplayingstrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        if (difficultyLevel.equals(BotDifficultyLevel.EASY)) {
            return new RandomBotPlayingStrategy();
        }
        return null;
    }
}
