package Server;
import java.rmi.registry.Registry;

import Remote.IDictionary;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

//Bind Servant
public class Server {
    public static void main(String[] args) {
        if(args.length>=2){
            String ipAddress = args[0];
            int port = Integer.parseInt(args[1]);
            try {
                IDictionary dService = new DictionaryService();
                LocateRegistry.createRegistry(port);
                Naming.rebind(String.format("rmi://%s:%d/dictionary", ipAddress,port), dService);
                System.out.printf("start server, port is %d\n", port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Usage: Server <serverIPAddress><serverPort>");
        }
    }
}
