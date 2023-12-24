package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> player){{
        return  Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(player)
                .build();
    }

    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Player getWinningPlayer(Game game) {
        return game.getWinningPlayer();
    }

    public void setGameStatus(Game game, GameStatus gameStatus){
        game.setGameStatus(gameStatus);
    }

    public void displayBoard(Game game) {
        game.getBoard().displayBoard();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }
}
