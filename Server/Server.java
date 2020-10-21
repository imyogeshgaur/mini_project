package Server;
public class Server {

    private Registrar registrar;

    public void startService(int choice) throws Exception{

        if(choice==1) {

            int port=9090;
            String IP="224.1.2.3";

            registrar = new SocketRegistrar(this,IP, port);

            Thread threadRegistrar = new Thread(registrar);

            threadRegistrar.start();

        }
        else if(choice ==0){
            System.out.printf("The Connection is Terminated by the Server !!!");
        }
    }
}
