import java.util.Arrays;

public class Solver {
    private Cell[][] grid;
    private Row[] rows;
    private Column[] columns;
    private Block[] blocks;
    Solver(Cell[] clues) throws Exception{
        initialise(clues);
    }
    private void initialise(Cell[] clues) throws Exception{
        grid = new Cell[9][9];
        rows = new Row[9];
        for(int i = 0;i<9;i++){
            rows[i] = new Row();
        }
        columns = new Column[9];
        for(int i = 0;i<9;i++){
            columns[i] = new Column();
        }
        blocks = new Block[9];
        for(int i=0;i<9;i++){
            blocks[i]=new Block();
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                Cell toAdd = new Cell(i, j);
                for(Cell clue:clues){
                    Integer[] position = new Integer[]{i, j};
                    if(Arrays.equals(clue.getPosition(), position)){
                        toAdd = clue;
                    }
                }
                grid[i][j] = toAdd;
                if(toAdd.getValue()!=null) {
                    columns[i].addNumber(toAdd);
                    rows[j].addNumber(toAdd);
                    blocks[findBlock(i, j)].addNumber(toAdd);
                }
            }
        }
    }
    private int findBlock(int i, int j){
        if(i<=2){
            if(j<=2){
                return 0;
            }else if(j<=5){
                return 3;
            }else{
                return 6;
            }
        }else if(i<=5){
            if(j<=2){
                return 1;
            }else if(j<=5){
                return 4;
            }else{
                return 7;
            }
        }else{
            if(j<=2){
                return 2;
            }else if(j<=5){
                return 5;
            }else{
                return 8;
            }
        }
    }
    private boolean isPossible(int i, int j, int value){
        if(columns[i].hasNumber(value)){
            return false;
        }else if(rows[j].hasNumber(value)){
            return false;
        }else return !blocks[findBlock(i, j)].hasNumber(value);
    }
    public void solveGrid() {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                Cell cell = grid[i][j];
                if(cell.isFixed()){
                    continue;
                }
                int initVal;
                if(cell.getValue()==null){
                    initVal=0;
                }else{
                    initVal=cell.getValue();
                }
                for (int k=initVal+1;k<=10;k++){
                    if(k<10){
                        if(isPossible(i, j, k)){
                            updateCell(i, j, k);
                            break;
                        }
                    }else{
                        updateCell(i,j,0);
                        j-=2;
                        if(j<0){
                            i-=1;
                            j=j+9;
                        }
                        Cell nextCell;
                        if(j==8){
                            nextCell = grid[i+1][0];
                        }else{
                            nextCell = grid[i][j+1];
                        }
                        while (nextCell.isFixed()){
                            j-=1;
                            if(j<0){
                                i-=1;
                                j=j+9;
                            }
                            if(j==8){
                                nextCell = grid[i+1][0];
                            }else{
                                nextCell = grid[i][j+1];
                            }
                        }
                    }
                }
            }
        }
    }
    private void updateCell(int i, int j, int value){
        Cell cell = grid[i][j];
        if(value>0) {
            updateCell(i, j, 0);
            cell.setValue(value);
            try {
                columns[i].addNumber(cell);
                rows[j].addNumber(cell);
                blocks[findBlock(i, j)].addNumber(cell);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            if(cell.getValue()!=null) {
                value = cell.getValue();
                columns[i].removeNumber(value);
                rows[j].removeNumber(value);
                blocks[findBlock(i, j)].removeNumber(value);
                cell.setValue(null);
            }
        }
    }
    public Cell[][] getGrid() {
        return grid;
    }
}
