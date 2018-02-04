import javax.swing.*;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;

public class LimitFilter extends NavigationFilter {
    private int maxSize;
    private JTextField cell;
    LimitFilter(int maxSize, JTextField cell){
        super();
        this.maxSize = maxSize;
        this.cell = cell;
    }
    @Override
    public void setDot(FilterBypass fb, int dot, Position.Bias bias) {
        if (dot >= maxSize) {
            fb.setDot(0, bias);
            cell.transferFocus();
            return;
        }
        fb.setDot(dot, bias);
    }

    @Override
    public void moveDot(FilterBypass fb, int dot, Position.Bias bias) {
        if (dot >= maxSize) {
            fb.setDot(0, bias);
            cell.transferFocus();
            return;
        }
        fb.moveDot(dot, bias);
    }
}
