import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DraughtsFrame extends JFrame implements ActionListener {
    Board board;
    String message;
    JLabel playerMessage;

    public DraughtsFrame() {
        board = new Board();
        message = null;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(850, 850);
        this.setLocation(20, 20);
        this.setTitle("Draughts");
        this.setLayout(new BorderLayout());

        this.add(board, BorderLayout.CENTER);

        this.playerMessage = new JLabel(message, JLabel.CENTER);
        playerMessage.setForeground(Color.black);
        JPanel southPanel = new JPanel();

        southPanel.setPreferredSize(new Dimension(getWidth() / 6, getHeight() / 6));
        southPanel.setLayout(new BorderLayout());
        southPanel.add(playerMessage, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        JPanel westPanel = new JPanel();
        JButton playerOne = new JButton("Player1 Done");
        playerOne.setForeground(Color.red);
        playerOne.setEnabled(true);
        JButton playerTwo = new JButton("Player2 Done");
        playerTwo.setForeground(Color.black);
        playerTwo.setEnabled(false);
        westPanel.setPreferredSize(new Dimension(getWidth() / 6, getHeight() / 6));
        westPanel.setLayout(new GridLayout(7,1, 1, 1));
        westPanel.add(playerOne);
        westPanel.add(playerTwo);
        this.add(westPanel, BorderLayout.WEST);

        this.setVisible(true);
    }
    public void setMessage(String message) {
        playerMessage.setText(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
