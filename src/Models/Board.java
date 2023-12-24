package Models;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int size;
    private List<List<Cell>> cell;

    public Board(int dimension) {
        this.size=dimension;
        this.cell=new LinkedList<>();
         for(int i=0;i<dimension;i++){
             this.cell.add(new LinkedList<>());
             for(int j=0;j<dimension;j++){
                 this.cell.get(i).add(new Cell(i,j));
             }
         }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCell() {
        return cell;
    }

    public void setCell(List<List<Cell>> cell) {
        this.cell = cell;
    }

    public List<List<Cell>> getBoard() {
        return cell;
    }

    public void displayBoard() {
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(cell.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("| |");
                }else{
                    System.out.print("|"+cell.get(i).get(j).getPlayer().getSymbol()+"|");
                }
            }
            System.out.println();
        }
    }


    public void applyMove(Move moves) {
        int row=moves.getCell().getRow();
        int col=moves.getCell().getCol();

        this.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        this.getBoard().get(row).get(col).setPlayer(moves.getPlayer());
    }
}
