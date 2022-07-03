package Remote;
import java.rmi.Remote;
import java.rmi.RemoteException;

//Interface of Dictionary
public interface IDictionary extends Remote{
    public String query(String message) throws RemoteException;
    public String add(String message) throws RemoteException;
    public String remove(String message) throws RemoteException;
}
