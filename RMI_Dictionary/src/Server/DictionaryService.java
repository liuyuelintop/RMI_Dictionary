package Server;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Remote.IDictionary;

//implementation of the interface
public class DictionaryService extends UnicastRemoteObject implements IDictionary {

    static String file = "/Users/liuyuelin/Code/Java/RMI_Dictionary/RMI_Dictionary/src/Server/dictionary.json";

    protected DictionaryService() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    public static String getFile() {
        return file;
    }
   

    @Override
    public String query(String message) throws RemoteException {
        JSONParser myParser = new JSONParser();
        String result = null;
        try{
            Object myObject = myParser.parse(new FileReader(getFile()));
            JSONObject myJsonObject = (JSONObject) myObject;
            
            // check the meaning from dictionary
            if(message != null){
                
                String meaning = (String) myJsonObject.get(message);
                System.out.println("Word to query: " + message);
            
                if (meaning == null)
                    meaning = "No such word in dictionary, please try again!";
                result = meaning + "\n";
            }
        
        }  catch (FileNotFoundException e) {
        // e.printStackTrace();
            System.out.println("ERROR: FILE NOT FOUND!");
        } catch (IOException e) {
        // e.printStackTrace();
            System.out.println("ERROR: I/O EXCEPTION!");
        } catch (ParseException e) {
        // e.printStackTrace();
            System.out.println("ERROR: PARSE EXCEPTION!");
        }
        
        return result;
    }

    //add(): put(message[0],message[1])
    @Override
    public String add(String message) throws RemoteException {
        JSONParser myParser = new JSONParser();
        String result = null;
        try{
            Object myObject = myParser.parse(new FileReader(file));
            JSONObject myJsonObject = (JSONObject) myObject;
            String[] input = message.split(",");
            String word = input[0];
            String def, meaning;
            meaning = (String) myJsonObject.get(word);
            
            // check the meaning from dictionary
            if(meaning == null){
                def = input[1];
                myJsonObject.put(word,def);
                System.out.println(myJsonObject);
                
                @SuppressWarnings("resource")
                FileWriter fw = new FileWriter(file, false);
                try {
                    fw.write(myJsonObject.toJSONString());
                    fw.flush();
                } catch (IOException e) {
                // e.printStackTrace();
                    System.out.println("ERROR: I/O EXCEPTION!");
                }

            System.out.println("Word to add: " +  word);
            result =  word +" has been updated to the dictionary successfully!\n";
            }
            else if( word != null){
                System.out.println("Word already exists! ");
                result = "Sorry, add operation failed: the word is already in the dictionary!\n";
            }
            else 
                result = "ERROR!";
        } catch (FileNotFoundException e) {
        // e.printStackTrace();
            System.out.println("ERROR: FILE NOT FOUND");
        } catch (IOException e) {
        // e.printStackTrace();
            System.out.println("ERROR: I/O EXCEPTION");
        } catch (ParseException e) {
        // e.printStackTrace();
            System.out.println("ERROR: PARSE EXCEPTION");
        }
        return result;
    }

    @Override
    public String remove(String message) throws RemoteException {
        JSONParser myParser = new JSONParser();
        String result = null;
        try{
        Object myObject = myParser.parse(new FileReader(file));
        JSONObject myJsonObject = (JSONObject) myObject;
        
        // check the meaning from dictionary
        String meaning = (String) myJsonObject.get(message);

        if(meaning != null){
        
            myJsonObject.remove(message);
            System.out.println(myJsonObject);
            
            @SuppressWarnings("resource")
            FileWriter fw = new FileWriter(file, false);
            try {
                fw.write(myJsonObject.toJSONString());
                fw.flush();
            } catch (IOException e) {
                System.out.println("ERROR: I/O EXCEPTION");
            }

            System.out.println("Deletes" + message +" from the dictionary successfully!\nThe current dictionary is:\n");
            System.out.println(myJsonObject);
            result = "Deletes word successfully!\n";
        }  
        else if(meaning == null){
            System.out.println("Delete operation failed: No such word in the dictionary!");
            result = "ERROR: No such word in the dictionary!\n";
        }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: FILE NOT FOUND");
        } catch (IOException e) {
            System.out.println("ERROR: I/O EXCEPTION");
        } catch (ParseException e) {
            System.out.println("ERROR: PARSE EXCEPTION");
        }
        return result;
    }
    
}
