package Server;
import Remote.IDictionary;
import Remote.IDictionaryGUI;

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
                IDictionaryGUI dictionaryGUI = new DictionaryGUI();
                LocateRegistry.createRegistry(port);
                Naming.rebind(String.format("rmi://%s:%d/dictionary", ipAddress,port), dService);
                Naming.rebind(String.format("rmi://%s:%d/dictionaryGUI", ipAddress,port), dictionaryGUI);
                System.out.printf("start server, port is %d\n", port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Usage: Server <serverIPAddress><serverPort>");
        }
    }
}
