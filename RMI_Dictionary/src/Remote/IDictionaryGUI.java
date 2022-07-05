package Remote;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public interface IDictionaryGUI extends Remote{
    public void run() throws RemoteException;
}
