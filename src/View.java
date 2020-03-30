public class View {
    Model model;
    Controller controller;
    DraughtsFrame frame;
    public View(Controller controller) {
        this.model = Model.instance();
        this.controller = controller;

        this.frame = new DraughtsFrame();
    }
}
