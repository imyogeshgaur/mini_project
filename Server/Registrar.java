package Server;

public abstract class Registrar implements Runnable {
    public Server server;
    public Registrar(Server server){
        this.server=server;
    }
}
