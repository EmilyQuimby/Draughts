import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Piece extends JPanel {
    private final int unit = 50;
    private Color color;
    private JLabel crown;
    private boolean crowned;
    private int rowLocation;
    private int columnLocation;
    public Piece() {
        crown = new JLabel("K", SwingConstants.CENTER);
        crown.setFont(new Font("Verdana", Font.BOLD, unit));
        this.crowned = false;
        setPreferredSize(new Dimension(unit, unit));
        setBackground(Color.pink);
        setLayout(new BorderLayout());

    }
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        int centerX = 0;
        int centerY = 0;
        int width = getWidth();
        int height = getHeight();

        Graphics2D graphic2 = (Graphics2D) graphic;
        Shape circle = new Ellipse2D.Double(centerX, centerY, width, height);
        graphic2.setColor(color);
        graphic2.draw(circle);
        graphic2.fill(circle);
    }
    public void crown() {
        add(crown, BorderLayout.CENTER);
        crowned = true;
    }
    public boolean isCrowned() {
        return crowned;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public Piece getPiece() {
        return this;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Piece orangePiece = new Piece();
        orangePiece.setColor(Color.orange);
        frame.getContentPane().add(orangePiece);

        frame.pack();
        frame.setVisible(true);
    }
    public int getRowLocation() {
        return rowLocation;
    }
    public int getColumnLocation() {
        return columnLocation;
    }
    public void setRowLocation(int boardRow) {
        this.rowLocation = boardRow;
    }
    public void setColumnLocation(int boardColumn) {
        this.columnLocation = boardColumn;
    }
}
