package Client;
public abstract class ServerConnection extends Thread {
    static Client client;
    public abstract void send() throws Exception;
    public abstract void receive()throws Exception;
    public abstract void register(String Name) throws Exception;
    public abstract void disconnect() throws Exception;
}
