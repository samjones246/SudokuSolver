import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller {
    private SudokuInterface window;
    public static void main(String[] args) {
        new Controller(new SudokuInterface());
    }
    private Controller(SudokuInterface window){
        this.window = window;
        this.window.getRootPane().setDefaultButton(window.getSolveButton());
        setupClearButton();
        setupSolveButton();
    }
    private void setupSolveButton(){
        JButton button = window.getSolveButton();
        JPanel[] blocks = getBlocks();
        button.addActionListener(e -> {
            Solver solver;
            try {
                solver = new Solver(getInputClues(blocks));
                solver.solveGrid();
                Cell[][] grid = solver.getGrid();
                philGridd(grid, blocks);
            }catch (Exception uDoneGoofedMate){
                JOptionPane.showMessageDialog(window, "Invalid Sudoku!");
                revertFormat();
            }
        });
    }

    private Cell[] getInputClues(JPanel[] blocks){
        ArrayList<Cell> clues = new ArrayList<>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) {
                int col, row;
                col = (j % 3) + (3 * (i % 3));
                row = Math.floorDiv(j, 3) + (3 * (Math.floorDiv(i, 3)));
                JTextField cell = (JTextField) blocks[i].getComponents()[j];
                if (!cell.getText().equals("")) {
                    int value = Integer.parseInt(cell.getText());
                    clues.add(new Cell(col, row, value));
                    Font boldFont = new Font(cell.getFont().getName(), Font.BOLD, cell.getFont().getSize());
                    cell.setFont(boldFont);
                }else{
                    cell.setForeground(Color.BLUE);
                }
            }
        }
        Cell[] clueCluxClan = new Cell[clues.size()];
        for(int i=0;i<clues.size();i++){
            clueCluxClan[i] = clues.get(i);
        }
        return clueCluxClan;
    }
    private void philGridd(Cell[][] gridd, JPanel[] blocks){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j=j+1){
                int col, row;
                col = (j % 3) + (3 * (i % 3));
                row = Math.floorDiv(j, 3) + (3 * (Math.floorDiv(i, 3)));
                JTextField cell = (JTextField) blocks[i].getComponents()[j];
                cell.setText(gridd[col][row].getValue().toString());
                cell.setEditable(false);
            }
        }
    }
    private void setupClearButton(){
        JButton button = window.getClearButton();
        JPanel[] blocks = getBlocks();
        button.addActionListener(e -> clearGrid(blocks));
    }

    private JPanel[] getBlocks(){
        Component[] cBlocks = window.getSudokuGrid().getComponents();
        JPanel[] blocks = new JPanel[9];
        for(int i=0;i<9;i++){
            blocks[i] = (JPanel) cBlocks[i];
        }
        return blocks;
    }

    private void clearGrid(JPanel[] blocks){
        for(JPanel block:blocks){
            for(Component cell:block.getComponents()){
                JTextField tfCell = (JTextField)cell;
                tfCell.setText("");
                tfCell.setEditable(true);
                Font normalFont = new Font(tfCell.getFont().getName(), Font.PLAIN, tfCell.getFont().getSize());
                tfCell.setFont(normalFont);
                tfCell.setForeground(Color.BLACK);
            }
        }
    }

    private void revertFormat(){
        for(JPanel block:getBlocks()){
            for(Component _cell:block.getComponents()){
                JTextField cell = (JTextField)_cell;
                cell.setFont(new Font(cell.getFont().getName(), Font.PLAIN, cell.getFont().getSize()));
                cell.setForeground(Color.BLACK);
            }
        }
    }
}