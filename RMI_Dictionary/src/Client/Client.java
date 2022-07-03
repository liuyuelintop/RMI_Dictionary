package Client;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Remote.IDictionary;

import java.io.*;

//handle io streams 
public class Client {
    public static void main(String[] args) {
        if(args.length>=2){
            String ipAddress = args[0];
            int port = Integer.parseInt(args[1]);
            try {
                IDictionary dService = (IDictionary) Naming.lookup(String.format("rmi://%s:%d/dictionary",ipAddress,port));
                System.out.println("Client Test:\nWord: c, Meaning: "+dService.query("c"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Usage: Client <serverIPAddress><serverPort>");
        }
    }
}

