public class Cell {
    private boolean fixed;
    private Integer value;
    private Integer[] position;

    Cell(Integer column, Integer row){
        fixed = false;
        value = null;
        position = new Integer[]{column,row};
    }
    Cell(Integer column, Integer row, int value){
        fixed = true;
        this.value = value;
        position = new Integer[]{column, row};
    }

    public boolean isFixed() {
        return fixed;
    }

    public Integer[] getPosition() {
        return position;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}