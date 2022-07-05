package Server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Remote.IDictionaryGUI;

public class DictionaryGUI extends UnicastRemoteObject implements IDictionaryGUI{

    protected DictionaryGUI() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    //TODO: checkinput 
    @Override
    public void run() throws RemoteException {
        // TODO Auto-generated method stub
        MyFrame myFrame = new MyFrame();
        myFrame.setVisible(true);
        DictionaryService dService = new DictionaryService();
        while(true){
            if(myFrame.getMessage() == null){
                System.out.println("Waiting for querying");
                continue;
            }
            String msg = myFrame.getMessage();
            if(!checkInput(msg)){
                myFrame.setResult("Invalid Operation. Please try again!");
                continue;
            }
            String[] command = msg.split(",");
            if(command[0].equals("query")){
                myFrame.setResult(dService.query(command[1]));
                myFrame.cleartMessage();
                continue;
            }
            if(command[0].equals("add")){
                myFrame.setResult(dService.add(command[1]+","+command[2]));
                myFrame.cleartMessage();
                continue;
            }
            if(command[0].equals("remove")){
                myFrame.setResult(dService.remove(command[1]));
                myFrame.cleartMessage();
                continue;
            }
        }
    }

    public static boolean checkInput(String inputString) {
		String[] input = inputString.split(",");
		boolean flag = true;
		if(input.length<2){
			flag = false;
		}
		else if(input.length == 2){
			if(input[0].equals("query")||input[0].equals("remove")){
				flag = true;
			}
			else{
				flag = false;
			}
		}
		else if(input.length ==3){
			if(input[0].equals("add")){
				flag = true;
			}
			else{
				flag = false;
			}
		}
		return flag;	
    }
}
