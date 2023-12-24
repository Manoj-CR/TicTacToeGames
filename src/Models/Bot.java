package Models;

import Strategy.BotPlayingStrategy;
import Strategy.EasyBotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, char c, BotDifficultyLevel botDifficultyLevel) {
        super(name,c,PlayerType.BOT);
        this.botDifficultyLevel=botDifficultyLevel;
        // To do : Better way is to implement using factory Method.
        this.botPlayingStrategy=new EasyBotPlayingStrategy();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move decideMove(Board board) {
        return  botPlayingStrategy.decideMove(this,board);

    }
}
