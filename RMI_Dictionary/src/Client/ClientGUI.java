package Client;

import java.rmi.Naming;

import Remote.IDictionaryGUI;

public class ClientGUI {
    public static void main(String[] args) {
        if(args.length>=2){
            String ipAddress = args[0];
            int port = Integer.parseInt(args[1]);
            try {
                IDictionaryGUI dictionaryGUI = (IDictionaryGUI) Naming.lookup(String.format("rmi://%s:%d/dictionaryGUI",ipAddress,port));
                dictionaryGUI.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Usage: ClientGUI <serverIPAddress> <serverPort>");
        }
    }

}
