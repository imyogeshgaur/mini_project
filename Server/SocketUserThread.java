package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class SocketUserThread extends UserThread {

    Socket clientSocket;
    Server server;
    String clientName;
    static ArrayList<Socket> clientSockets;

    public SocketUserThread(ArrayList<Socket> clientSockets, Socket clientSocket, String clientName, Server server) {
        this.clientSocket = clientSocket;
        this.clientName = clientName;
        this.server = server;
        this.clientSockets = clientSockets;
    }
    public void run() {
        try {
            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            String message = clientName + " Joined The Chat !!";
            boolean running = true;
            sendToAllClients(message);
            while (running) {
                message = input.readUTF();
                if (message.equals("@@#DISCONNECT#")) {
                    message = clientName + "Left The Chat";
                    ClientTable table = new ClientTable(clientSockets);
                    table.remove(clientSocket);
                    running = false;
                } else {
                    message = clientName + " : " + message;
                }
                sendToAllClients(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void sendToAllClients(String message) {
        Socket socket = null;
        DataOutputStream output;
        OutputStream outputStream;

        ClientTable I = new ClientTable(clientSockets);

        while ((socket = I.next()) != null) {

            try {
                outputStream = socket.getOutputStream();
                synchronized (outputStream) {
                    output = new DataOutputStream(outputStream);
                    output.writeUTF(message);
                    output.flush();
                }
            } catch (SocketException p) {
            } catch (Exception e) {
            }

        }
    }
}
