public class Controller {
    private Model model;
    private View view;
    public Controller(Model model) {
        this.model = model;
        this.view = new View(this);
    }
    public void start() {
        model.defaultBoard();
        view.frame.board.setBoard();
        view.frame.setVisible(true);
        view.frame.setMessage("Welcome to Draughts! Player1 it's your go.....Press the 'Player1 Done' button when you are done :)");

    }
}
