package Client;

import java.rmi.Naming;
import java.util.Scanner;

import Remote.IDictionary;

//handle io streams 
public class Client {
    public static void main(String[] args) {
        if(args.length>=2){
            String ipAddress = args[0];
            int port = Integer.parseInt(args[1]);
            try {
                IDictionary dService = (IDictionary) Naming.lookup(String.format("rmi://%s:%d/dictionary",ipAddress,port));
                System.out.println("\nWelcome to Online Dictionary! You can query, add or remove words!");
                System.out.println("You should follow below instructions:\n>>>>>>>>>>>>>>>>>>>>>>");
                System.out.println("Query: query,word\nAdd: add,word,definition\nRemove: remove,word");
                System.out.println("For example: query,hello\n>>>>>>>>>>>>>>>>>>>>>>");
			    Scanner scanner = new Scanner(System.in);
			    String inputStr = null;
                while (!(inputStr = scanner.nextLine()).equals("exit")) {
				
                    String received = null;
                    if(!checkInput(inputStr)){
                        System.out.println("Invalid Input!");
                        continue;
                    }
                    String[] command = inputStr.split(",");
                    if(command[0].equals("query")){
                        received= dService.query(command[1]);
                    }
                    if(command[0].equals("add")){
                        received = dService.add(command[1]+","+command[2]);
                    }
                    if(command[0].equals("remove")){
                        received = dService.remove(command[1]);
                    }
                    System.out.println(received);
                }
                scanner.close();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Usage: Client <serverIPAddress><serverPort>");
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

