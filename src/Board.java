import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
/*
Board class is a JPanel that gets added to the Draughts Frame
 */
public class Board extends JPanel implements MouseListener {
    private PinkSquare[][] pink;
    private GraySquare[][] gray;
    private final int width = 400;
    private final int height = width;
    private final int borderUnit = 10;
    private Piece lastPiece;
//    private int rowLocation;
//    private int columnLocation;
//    private int[] rowLocations;
//    private int[] columnLocations;
    private Model model;

    public Board() {
        this.model = Model.instance();
        this.setSize(width, height);
        this.setLayout(new GridLayout(8,8,1,1));
        this.setBorder(BorderFactory.createEmptyBorder(borderUnit, borderUnit, borderUnit, borderUnit));

        pink = new PinkSquare[8][8];
        gray = new GraySquare[8][8];

        for(int row=0;row<8;row++) {
            for(int column=0;column<8;column++) {
                gray[row][column] = new GraySquare();
                pink[row][column] = new PinkSquare();
                pink[row][column].setBoardRow(row);
                pink[row][column].setBoardColumn(column);
                pink[row][column].addMouseListener(this);
                pink[row][column].setEnabled(false);
                pink[row][column].revalidate();
                if((row+column)%2 == 0) {
                    this.add(gray[row][column]);
                }
                else {
                    this.add(pink[row][column]);
                }
            }
        }
    }
    //method sets the board depending on model by iterating through rows and columns and adding pieces where
    //model boolean values (for blackPiece[][] and redPiece[][]) are true at their respective locations.
    public void setBoard() {
        for(int row=0;row<8;row++) {
            for (int column = 0; column < 8; column++) {
                if (model.getBlackPiece()[row][column]) {
                    pink[row][column].addRedPiece();
                    pink[row][column].revalidate();
                }
                if (model.getRedPiece()[row][column]) {
                    pink[row][column].addBlackPiece();
                    pink[row][column].revalidate();
                }
            }
        }
    }

//    public void activateSquares() {
//        if(model.isBlackGo()) {
//            for(int row=0;row<8;row++) {
//                for(int column=0;column<8;column++) {
//                    if(pink[row][column].hasBlackPiece) {
//                        if(pink[row--][column--].isEmpty || pink[row--][row++].isEmpty) {
//                            pink[row][column].setEnabled(true);
//                            pink[row][column].revalidate();
//                            if(pink[row--][column--].isEmpty) {
//                                pink[row--][column--].setEnabled(true);
//                                pink[row--][column--].revalidate();
//                            }
//                            if(pink[row--][column++].isEmpty) {
//                                pink[row--][column++].setEnabled(true);
//                                pink[row--][column++].revalidate();
//                            }
//                        }
////                        if(pink[row][column].getPiece().isCrowned()) {
////                            if(pink[row--][column--].isEmpty || pink[row--][column++].isEmpty || pink[row++][column++].isEmpty || pink[row++][column--].isEmpty) {
////                                if (pink[row++][column--].isEmpty) {
////                                    pink[row++][column--].setEnabled(true);
////                                }
////                                if (pink[row--][column++].isEmpty) {
////                                    pink[row--][column++].setEnabled(true);
////                                }
////                            }
////                        }
//                    }
//                }
//            }
//        }
//    }
    @Override
    public void mouseClicked(MouseEvent e) {
        PinkSquare thisSquare = (PinkSquare)e.getComponent();
        if(thisSquare.hasRedPiece) {
            thisSquare.removeRedPiece();
            model.setRedPiece(thisSquare.getBoardRow(), thisSquare.getBoardColumn(), false);
            lastPiece = new RedPiece();
        }
        else if(thisSquare.hasBlackPiece) {
            thisSquare.removeBlackPiece();
            model.setBlackPiece(thisSquare.getBoardRow(), thisSquare.getBoardColumn(), false);
            lastPiece = new BlackPiece();
        }
        else if(thisSquare.isEmpty && lastPiece!=null) {
            if(lastPiece instanceof RedPiece) {
                thisSquare.addRedPiece();
                model.setRedPiece(thisSquare.getBoardRow(), thisSquare.getBoardColumn(), true);
            }
            else {
                thisSquare.addBlackPiece();
                model.setBlackPiece(thisSquare.getBoardRow(), thisSquare.getBoardColumn(), true);
            }
            thisSquare.revalidate();
            lastPiece = null;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        PinkSquare thisSquare = (PinkSquare)e.getComponent();
        thisSquare.setBackground(Color.lightGray);
        if(thisSquare.hasRedPiece || thisSquare.hasBlackPiece) {
            thisSquare.getPiece().setBackground(Color.lightGray);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        PinkSquare thisSquare = (PinkSquare)e.getComponent();
        thisSquare.setBackground(Color.pink);
        if(thisSquare.hasRedPiece || thisSquare.hasBlackPiece) {
            thisSquare.getPiece().setBackground(Color.pink);
        }
    }
}
