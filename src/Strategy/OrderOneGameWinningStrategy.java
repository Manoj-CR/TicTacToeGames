package Strategy;

import Models.Board;
import Models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy {

    // every row -> Hashmap for symbol and count
   private List<HashMap<Character,Integer>> rowSymbolCount=new ArrayList<>();
    // every col -> Hashmap for symbol and count
    private List<HashMap<Character,Integer>> colSymbolCount=new ArrayList<>();
    // every diagonal -> Hashmap for symbol and count
    private HashMap<Character,Integer> topDiagonalSymbolCount=new HashMap<>();
    private HashMap<Character,Integer> rightDiagonalSymbolCount=new HashMap<>();

    public OrderOneGameWinningStrategy(int dimension){
        for(int i=0;i<dimension;i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        char symbol=move.getPlayer().getSymbol();
        int row=move.getCell().getRow();
        int col=move.getCell().getCol();
        int dimension=board.getBoard().size();


        //update count
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(symbol,(rowSymbolCount.get(row).get(symbol)+1));

        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(symbol,(colSymbolCount.get(col).get(symbol)+1));

        if(row==col){
            if(!topDiagonalSymbolCount.containsKey(symbol)){
                topDiagonalSymbolCount.put(symbol,0);
            }
            topDiagonalSymbolCount.put(symbol,(topDiagonalSymbolCount.get(symbol)+1));
        }
        if(row+col == dimension-1){
            if(!rightDiagonalSymbolCount.containsKey(symbol)){
                rightDiagonalSymbolCount.put(symbol,0);
            }
            rightDiagonalSymbolCount.put(symbol,(rightDiagonalSymbolCount.get(symbol)+1));
        }

        //check winner
        if(rowSymbolCount.get(row).get(symbol)==dimension ||
                colSymbolCount.get(col).get(symbol)==dimension){
            return true;
        }
        if(row==col && topDiagonalSymbolCount.get(symbol)==dimension){
            return true;
        }
        if(row+col==dimension-1 && rightDiagonalSymbolCount.get(symbol)==dimension){
            return true;
        }



        return false;
    }
}
