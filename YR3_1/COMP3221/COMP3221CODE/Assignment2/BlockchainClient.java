import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BlockchainClient {

    public static void main(String[] args) {

        if (args.length != 1) {
            return;
        }
        String configFileName = args[0];

        ServerInfoList pl = new ServerInfoList();
        pl.initialiseFromFile(configFileName);

        Scanner sc = new Scanner(System.in);

        while (true) {
            String message = sc.nextLine();
            // implement your code here
            
            //split the command entered into entries
            String[] entries = message.split("\\|");
            
            //************************all commands that dont need further augment
            
            //list command
            if (entries[0].equals("ls")) {
                System.out.print(pl.toString() + "\n");
            }
            
            //clear command
            else if (entries[0].equals("cl")) {
            	try {
                pl.clearServerInfo();
                System.out.print("Succeeded\n\n");
            	}catch(Exception e) {
            		System.out.print("Failed\n\n");
            	}
            }
            
            //shut down command
            else if (entries[0].equals("sd")) {
                break;
            }
            
            //*************************commands that need string operation
            
            //add lnfo command
            else if (entries[0].equals("ad")) {
            	
                //check command format
                if (entries.length == 3) {
                    //check if entry at port window is number
                    if (isInteger(entries[2])) {
                        Integer serverPort = Integer.parseInt(entries[2]);
                        //check if port is in bound
                        if (serverPort >= 1024 && serverPort <= 65535) {
                            pl.addServerInfo(new ServerInfo(entries[1], serverPort));
                            System.out.print("Succeeded\n\n");
                        } else {
                        	System.out.println("port error");
                           System.out.print("Failed for\n\n"); 
                        }
                    } else {
                    	System.out.println("3 entry not integer");
                        System.out.print("Failed\n\n");
                    }
                } else {
                	System.out.println("too many arguments");
                    System.out.print("Failed\n\n");
                }
            }
            
            //remove lnfo command
             else if (entries[0].equals("rm")) {
                
                //check command format
                if (entries.length == 2) {
                    //check if index is number
                    if (isInteger(entries[1])) {
                    	//check if the remove method succeeded
                        if (pl.removeServerInfo(Integer.parseInt(entries[1]))) {
                            System.out.print("Succeeded\n\n");
                        } else {
                        	//if index doesnt exist, remove method will return false
                            System.out.print("Failed\n\n");
                        }
                    } else {
                        System.out.print("Failed\n\n");
                    }
                } else {
                    System.out.print("Failed\n\n");
                }
            }
            
            
            //update info command
            else if (entries[0].equals("up")) {
                
                //check command format
                if (entries.length == 4) {
                	//check if index and port is number
                    if (isInteger(entries[1]) && isInteger(entries[3])) {
                        Integer serverPort = Integer.parseInt(entries[3]);
                        //check if port is in bound
                        if (serverPort >= 1024 && serverPort <= 65535) {
                        	//check if update method succeeded
                        	if(pl.updateServerInfo(Integer.parseInt(entries[1]), new ServerInfo(entries[2], serverPort))) {
                        		System.out.print("Succeeded\n\n");
                        	} else {
                        		//if index doesnt exist, remove method will return false
                        		System.out.print("Failed\n\n");
                        	}
                        }
                        else {
                           System.out.print("Failed\n\n"); 
                        }
                    } else {
                        System.out.print("Failed\n\n");
                    }
                } else {
                    System.out.print("Failed\n\n");
                }
            }
            
            //transact command
            else if (entries[0].equals("tx")) {
                
                //check format
                if (entries.length == 3) {
                    broadcast(pl, message);
                }
            }
            
            //print blockchain command
            else if (entries[0].equals("pb")) {
            	//if broadcast
                if (entries.length == 1) {
                    broadcast(pl, "pb");
                } else {
                	//if specified server index
                    ArrayList<Integer> serverIndex = new ArrayList<Integer>();
                    
                    //add indexes to list
                    for (int i = 1; i < entries.length; i++) {
                        if (isInteger(entries[i])) {
                            serverIndex.add(Integer.parseInt(entries[i]));
                        } 
                    }
                    //multicast with list after adding
                    multicast(pl, serverIndex, "pb");
                }
            }
            
            //unknown command
            else {
                System.out.print("Unknown Command\n\n");
            }
            
        }
    }

    public static void unicast (int serverNumber, ServerInfo p, String message) {
        // implement your code here
    	Thread thread = new Thread(new BlockchainClientRunnable(serverNumber, p.getHost(), p.getPort(), message));
    	thread.start();
    	
    	long timeOut = System.currentTimeMillis() + 20;
        while (thread.isAlive()) {
            if (System.currentTimeMillis() > timeOut) {
            		try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    System.out.print("Server is not available\n\n");
                break;
            }
        }
    	
    }

    public static void broadcast (ServerInfoList pl, String message) {
        // implement your code here
    	for (int i = 0; i < pl.getServerInfos().size(); i++) {
            ServerInfo server = pl.getServerInfos().get(i);
            if (server != null) {
                unicast(i, server, message);
            }
        }
    }

    public static void multicast (ServerInfoList serverInfoList, ArrayList<Integer> serverIndices, String message) {
        // implement your code here
    	for (int i = 0; i < serverInfoList.getServerInfos().size(); i++) {
            if (serverIndices.contains(i)) {
            	ServerInfo server = serverInfoList.getServerInfos().get(i);
                if (server != null) {
                    unicast(i, server, message);
                }
            }
        }
    }

    // implement any helper method here if you need any
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) { 
            return false; 
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}