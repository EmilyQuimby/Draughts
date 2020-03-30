import java.awt.*;

public class PinkSquare extends Square {
    private Color color;
    private Piece piece;
    boolean hasRedPiece;
    boolean hasBlackPiece;
    boolean isEmpty;
    private int boardRow;
    private int boardColumn;

    public PinkSquare() {
        this.color = Color.pink;
        this.setBackground(color);
        hasRedPiece = false;
        hasBlackPiece = false;
        isEmpty = true;
    }
    public void addRedPiece() {
        this.piece = new RedPiece();
        this.add(piece, BorderLayout.CENTER);
        piece.setRowLocation(this.boardRow);
        piece.setColumnLocation(this.boardColumn);
        hasRedPiece = true;
        isEmpty = false;
    }
    public void addBlackPiece() {
        this.piece = new BlackPiece();
        this.add(piece, BorderLayout.CENTER);
        piece.setRowLocation(this.boardRow);
        piece.setColumnLocation(this.boardColumn);
        hasBlackPiece = true;
        isEmpty = false;
    }
    public void removeRedPiece() {
        this.remove(piece);
        hasRedPiece = false;
        isEmpty = true;
    }
    public void removeBlackPiece() {
        this.remove(piece);
        hasBlackPiece = false;
        isEmpty = true;
    }
    public Piece getPiece() {
        return piece;
    }
    public int getBoardRow() {
        return boardRow;
    }
    public int getBoardColumn() {
        return boardColumn;
    }

    public void setBoardRow(int boardRow) {
        this.boardRow = boardRow;
    }
    public void setBoardColumn(int boardColumn) {
        this.boardColumn = boardColumn;
    }
}
