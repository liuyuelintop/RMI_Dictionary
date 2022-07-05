package Server;

import javax.swing.JFrame;
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
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener{

    public static int WIDTH = 600;
    public static int HEIGHT = 500;
    private static JTextArea userInput, result_on_screen;
    static String inputStr = null;
    static String msg = null;
    static String error_msg = null;

    MyFrame(){
        super("RMI Dictionary");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
         //User Input Panel
        JTextArea inputPanel = new JTextArea(10,10);
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(new Color(102,178,255));

        JLabel welcomeJLabel = new JLabel("Thanks for using Online Dictionary");
        JLabel instructionLabel1 = new JLabel("Please follow the instruction below:  Query:word; Add:word,definition; Remove:word");
        inputPanel.add(welcomeJLabel,BorderLayout.NORTH);
        inputPanel.add(instructionLabel1,BorderLayout.CENTER);

        userInput = new JTextArea(5,10);
        userInput.setBackground(new Color(224,224,224));
        inputPanel.add(userInput,BorderLayout.SOUTH);

        add(inputPanel);
        
        //Output Panel
        JTextArea outputPanel = new JTextArea(10,10);
        outputPanel.setLayout(new BorderLayout());
        outputPanel.setBackground(new Color(102,178,255));
        JLabel serverOutputLabel = new JLabel("Information get from the server");
        outputPanel.add(serverOutputLabel,BorderLayout.NORTH);
        result_on_screen = new JTextArea(8,10);
        result_on_screen.setBackground(new Color(224,224,224));
        outputPanel.add(result_on_screen,BorderLayout.SOUTH);
        add(outputPanel);

    // Buttons Panel:  for Query, Add and Remove operations
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(102,178,255));
        JButton queryBtn = new JButton("Query");
        queryBtn.addActionListener(this);
        buttonPanel.add(queryBtn);
        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        buttonPanel.add(addBtn);
        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener(this);
        buttonPanel.add(removeBtn);
        add(buttonPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String actionCommand = e.getActionCommand();
    
            if(actionCommand.equals("Query")){
                msg = "query" + "," + userInput.getText();
            }   
            if(actionCommand.equals("Add"))
                msg = "add" + "," + userInput.getText();
            if(actionCommand.equals("Remove"))
                msg = "remove" + "," + userInput.getText();
    }

    public String getMessage() {
        return msg;
    }

    public void cleartMessage(){
        msg = null;
    }

    public void setResult(String receivedStr) {
        result_on_screen.setText(receivedStr);
    }
}
