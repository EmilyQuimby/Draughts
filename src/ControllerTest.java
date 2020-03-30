public class ControllerTest {
    public static void main(String[] args) {
        Model model = Model.instance();
        Controller controller = new Controller(model);
        controller.start();
    }
}
