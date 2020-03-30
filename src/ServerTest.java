import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTest implements Runnable{
    private class ClientRunner implements Runnable {
        private Socket s = null;
        private ServerTest parent = null;
        private ObjectInputStream inputStream = null;
        private ObjectOutputStream outputStream = null;
        public ClientRunner(Socket s, ServerTest parent) {
            this.s = s;
            this.parent = parent;
            try {
                outputStream = new ObjectOutputStream(this.s.getOutputStream());
                inputStream = new ObjectInputStream(this.s.getInputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            // receive model update
            try {
                Model model = null;
                while((model = (Model)inputStream.readObject())!= null) {
                    this.parent.transmit(model);
                }
                inputStream.close();
            }catch(ClassNotFoundException e) {
                e.printStackTrace();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void transmitModel(Model m) {
            try {
                outputStream.writeObject(m);
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    private ServerSocket server;
    private ArrayList<ClientRunner> clients = new ArrayList<ClientRunner>();
    public ServerTest() {
        try {
            server = new ServerSocket(8765);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            Socket clientSocket = null;
            try {
                clientSocket = server.accept();
                System.out.println("New client connected");
                if(clients.size()<2) {
                    System.out.println("Please wait for another player to join");
                }
                else if(clients.size()>2) {
                    System.out.println("Please wait your turn to play");
                }
                else {
                    System.out.println("Let's Play!");
                }
                ClientRunner client = new ClientRunner(clientSocket,this);
                clients.add(client);
                new Thread(client).start();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void transmit(Model m) {
        for(ClientRunner c: clients) {
            if(c != null) {
                c.transmitModel(m);
            }
        }
    }
    public static void main(String[] args) {
        Thread t = new Thread(new ServerTest());
        t.start();
        System.out.println("Draughts server is running....");
        Model model = null;
        model = model.instance();
        try {
            t.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }


    }
}
