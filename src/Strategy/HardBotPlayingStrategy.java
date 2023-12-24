package Strategy;

import Models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HardBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Move decideMove(Player player, Board board, Game game) {
        int dimension = board.getSize();
        /*
        Step 1 : get empty Cell List where possible moves can happen for computer player
        Step 2: iterate through emptyCellList and get the bestPossible score and Cell Object

        Step 3: return the bestPossible Cell Object
         */
        List<Cell> emptyCellList=new ArrayList<>();

        for(int i=0;i<dimension;i++){
             for(int j=0;j<dimension;j++){
                 if(board.getBoard().get(i).get(j).getCellState() == CellState.EMPTY){
                     emptyCellList.add(new Cell(i,j));
                 }
             }
         }


        return null;
    }

    public int calculateScore(Board board,Cell cell,Player player){
        int bestScore=0;
        List<HashMap<Character,Integer>> rowSymbolCount=new ArrayList<>();
        // every col -> Hashmap for symbol and count
        List<HashMap<Character,Integer>> colSymbolCount=new ArrayList<>();
        // every diagonal -> Hashmap for symbol and count
        HashMap<Character,Integer> topDiagonalSymbolCount=new HashMap<>();
        HashMap<Character,Integer> rightDiagonalSymbolCount=new HashMap<>();

        int dimension = board.getSize();

        for(int i=0;i<dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
        int row= cell.getRow();
        int col=cell.getCol();
        //row score
        for(int i=0;i<dimension;i++){
            if(board.getBoard().get(row).get(i).getCellState()==CellState.FILLED){
                if(board.getBoard().get(row).get(i).getPlayer().getSymbol()==player.getSymbol()) {
                    bestScore++;
                }else{
                    bestScore--;
                }
            }

        }


        return bestScore;
    }
}
