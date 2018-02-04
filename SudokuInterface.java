import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class SudokuInterface extends JFrame{
    private JButton solveButton, clearButton;
    private JPanel sudokuGrid;
    SudokuInterface(){
        setTitle("Sudoku Solver");
        JPanel panel = new JPanel();
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setLayout(new BorderLayout());
        sudokuGrid = new JPanel();
        sudokuGrid.setLayout(new GridLayout(3,3));
        solveButton = new JButton("SOLVE");
        clearButton = new JButton("CLEAR");
        JPanel buttonBar = new JPanel();
        buttonBar.setLayout(new GridLayout(1,2));
        buttonBar.add(solveButton);
        buttonBar.add(clearButton);
        panel.add(sudokuGrid, BorderLayout.CENTER);
        panel.add(buttonBar, BorderLayout.SOUTH);
        for(int i=0;i<9;i++){
            JPanel block = new JPanel();
            block.setLayout(new GridLayout(3,3));
            for(int j=0;j<9;j++){
                JTextField cell = new JTextField(1);
                LimitFilter filter = new LimitFilter(1, cell);
                cell.setHorizontalAlignment(JTextField.CENTER);
                cell.setNavigationFilter(filter);
                ((AbstractDocument) cell.getDocument()).setDocumentFilter(new SudokuFilter(1));
                cell.setFont(new Font(cell.getFont().getName(), Font.PLAIN, 18));
                block.add(cell);
            }
            block.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            sudokuGrid.add(block);
        }
        setMinimumSize(new Dimension(300, 320));
        setVisible(true);
    }

    public JButton getSolveButton() {
        return solveButton;
    }

    public JPanel getSudokuGrid() {
        return sudokuGrid;
    }

    public JButton getClearButton() {
        return clearButton;
    }
}
