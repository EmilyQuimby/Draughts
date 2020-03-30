import java.io.Serializable;

//model class implemented using singleton pattern, to limit it to one instance of model within the program.
//Serializable so that it can be sent via the Server
public class Model implements Serializable {
    //2D boolean arrays contain data on whether or not a redPiece or a blackPiece is present
    private boolean[][] redPiece;
    private boolean[][] blackPiece;
    private boolean blackGo;
    private boolean gameOver;

    private static Model model = new Model();

    private Model() {
        //two arrays that hold booleans to say whether or not a red/black piece is present at a given location
        redPiece = new boolean[8][8];
        blackPiece = new boolean[8][8];
        blackGo = true;
        gameOver = false;

    }
    //method that sets boolean values in both redPiece/blackPiece arrays to say whether or not a piece is present - values set
    //according to default/starting positions of the pieces.
    public void defaultBoard() {
        for(int row=0;row<8;row++) {
            for(int column=0;column<8;column++) {
                if((row == 0 && column%2 != 0) || (row == 1 && column%2 == 0) || (row == 2 && column%2 != 0)) {
                    redPiece[row][column] = true;
                }
                if((row == 5 && column%2 == 0) || (row == 6 && column%2 != 0) || (row == 7 && column%2 == 0)) {
                    blackPiece[row][column] = true;
                }
            }
        }
    }
    public boolean[][] getRedPiece() {
        return redPiece;
    }

    public void setRedPiece(int row, int column, boolean status) {
        this.blackPiece[row][column] = status;
    }

    public boolean[][] getBlackPiece() {
        return blackPiece;
    }

    public void setBlackPiece(int row, int column, boolean status) {
        this.blackPiece[row][column] = status;
    }

    public boolean redIsWinner() {
        boolean redWin = false;
        int blackPieces = 0;
        for(int row=0;row<8;row++) {
            for (int column = 0; column < 8; column++) {
                if(blackPiece[row][column] = true) {
                    blackPieces++;
                }
            }
        }
        if(blackPieces == 0) {
           redWin = true;
        }
        return redWin;
    }

    public boolean blackIsWinner() {
        boolean blackWin = false;
        int redPieces = 0;
        for(int row=0;row<8;row++) {
            for (int column = 0; column < 8; column++) {
                if(redPiece[row][column] = true) {
                    redPieces++;
                }
            }
        }
        if(redPieces == 0) {
            blackWin = true;
        }
        return blackWin;
    }

    public boolean isBlackGo() {
        return blackGo;
    }

    public void setBlackGo(boolean blackGo) {
        this.blackGo = blackGo;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public static Model instance() {
        return model;
    }
}



