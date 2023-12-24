package Models;

import Exceptions.InvalidGameDimensionException;
import Strategy.GameWinningStrategy;
import Strategy.OrderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;

    private List<Player> player;
    private List<Move> move;
    private GameStatus gameStatus;
    private int nextPlayerIndex;



    private GameWinningStrategy gameWinningStrategy;


    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }
    public Player getWinningPlayer() {
        return winningPlayer;
    }

    public void setWinningPlayer(Player winningPlayer) {
        this.winningPlayer = winningPlayer;
    }

    private Player winningPlayer;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public List<Move> getMove() {
        return move;
    }

    public void setMove(List<Move> move) {
        this.move = move;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }
    public static Builder getBuilder(){
        return new Builder();

    }

    public void makeNextMove() {

         Player playerWhosMoveItis=player.get(nextPlayerIndex);
        System.out.println("It is "+playerWhosMoveItis.getName()+"'s turn");

        Move moves=playerWhosMoveItis.decideMove(board);

         int row=moves.getCell().getRow();
         int col=moves.getCell().getCol();

         if(board.getBoard().get(row).get(col).getCellState().equals(CellState.EMPTY)){
             board.applyMove(moves);
             move.add(moves);
             
             //checkWinner
              if(gameWinningStrategy.checkWinner(board,moves)){
                  gameStatus=GameStatus.END;
                  winningPlayer=playerWhosMoveItis;
              }else if(move.size()== board.getSize()* board.getSize()){
                  gameStatus=GameStatus.DRAW;
              }
             
             nextPlayerIndex+=1;
             nextPlayerIndex%=player.size();
        }else{
         //throw error
        }
    }

    public  static class Builder{

        private int dimension;

        private List<Player> players;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(){
            try{
                isValid();
            }catch(InvalidGameDimensionException e){
                return null;
            }
            Game game=new Game();
            game.setBoard(new Board(dimension));
            game.setPlayer(players);
            game.setNextPlayerIndex(0);
            game.setMove(new LinkedList<>());
            game.setGameStatus(GameStatus.INPROGRESS);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }

        private boolean isValid() throws InvalidGameDimensionException {
            if(dimension<3){
                throw new InvalidGameDimensionException(
                        "Dimension should be greater than 2 ");
            }
            //Add more exepction

            return true;

        }
    }
}
/*
    1          M
show seat --  seat

 */