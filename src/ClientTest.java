import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTest implements ActionListener {

    private class DraughtsWorker extends SwingWorker<Void,Void> {
        private Socket socket = null;
        private ObjectInputStream inputStream = null;
        private ClientTest parent;
        public DraughtsWorker(Socket s, ClientTest parent) {
            this.socket = s;
            this.parent = parent;
            try {
                inputStream = new ObjectInputStream(this.socket.getInputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public Void doInBackground() {
            System.out.println("Started Draughts Worker");
            m = null;
            try {
                while((m = (Model)inputStream.readObject())!= null) {
                   // System.out.println(m);
                    parent.makeController(m);
                }
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }finally {
                return null;
            }
        }
    }

    private Socket server = null;
//    private JTextArea textArea;
    private ObjectOutputStream outputStream;
    private Controller controller;
    private Model m;
//    private JTextField messageField;
//    private JButton sendButton;
//    private String name = "Sarah";
    public ClientTest() {

        this.controller = new Controller(m);
        controller.start();
        connect();

        try {
            outputStream = new ObjectOutputStream(server.getOutputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
        DraughtsWorker draughtsWorker = new DraughtsWorker(server,this);
        draughtsWorker.execute();
        System.out.println("Player has connected");
    }
    private void connect() {
        try {
            server = new Socket("127.0.0.1",8765);
            System.out.println("Connected");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void makeController(Model m) {
        this.controller = new Controller(m);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
////    public void actionPerformed(ActionEvent e) {
////        if(e.getSource() == sendButton) {
////            String messageText = messageField.getText();
////            try {
////                outputStream.writeObject(new Message(messageText,name));
////                messageField.setText("");
////            }catch(IOException ex) {
////                ex.printStackTrace();
////            }
////        }
//    }

    public static void main(String[] args) {

        new ClientTest();
    }
}
