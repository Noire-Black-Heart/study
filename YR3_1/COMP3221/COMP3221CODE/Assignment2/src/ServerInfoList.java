import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ServerInfoList {

    ArrayList<ServerInfo> serverInfos;

    public ServerInfoList() {
        serverInfos = new ArrayList<>();
    }

    public void initialiseFromFile(String filename) {
        // implement your code here
    	int size = 0;
    	
    	try {
    	//create reader
			BufferedReader configReader = Files.newBufferedReader(Paths.get(filename));
			
			//create list for storing untuned server infos
			ArrayList<ServerInfo> readedList = new ArrayList<>(100);
			
			String current;
			int serverID = -1;
			String serverHost = "";
		while((current = configReader.readLine()) != null) {
			current = current.trim();
			//if empty line
			if(current.isEmpty()) {
				serverID = -1;
				continue;
			}
			
			//if not empty
			
			//split the line into entries
			String[] entries = current.split("=");
			
			//check length 
			if(entries.length != 2) {
				//reset the flag
				serverID = -1;
				continue;
			}
			//left entry
				//if this left entry is servers.num
				if(entries[0].equals("servers.num")) {
					//remember the size
					size = Integer.parseInt(entries[1]);
					
					//reset the flag
					serverID = -1;
				}
				//else is added here to prevent the next check happens before this one
				else {
					//if this left entry is server+number+host
					if(entries[0].matches("server\\d.host")) {
						//update the flag
						serverID = Integer.parseInt(entries[0].replaceAll("\\D+",""));
						serverHost = entries[1];
					}
					
					//if this left entry is server+number+port
					else if(entries[0].matches("server\\d.port")) {
						//check the flag
						if(serverID == Integer.parseInt(entries[0].replaceAll("\\D+",""))) {
							//if flag match, insert server message
							//check if this id dont exist in the untuned list	
							try {
	                            readedList.get(serverID);
	                          //if this id already exist, that means a previous record has been created, ignore this new line
	                        } catch (IndexOutOfBoundsException e) {
	                        	//add a new entry in the untuned list if not exist
	                        	int serverPort = Integer.parseInt(entries[1]);
	                        	if(serverPort >= 1024 && serverPort <= 65535) {
	                        	readedList.add(serverID, new ServerInfo(serverHost, serverPort));}
	                        }
						}
						
						//set flag to -1 after this line
						serverID = -1;
						
					}
					
				}
		}
		
		
		//after this, everything valid should be stored into the readedList
		for(int i = 0; i < size; i ++) {
			serverInfos.add(null);
		}
			for (int i = 0; i < size; i++) {
				try {
					
					if (readedList.get(i) == null) {
						//serverInfos.set(i, null);
					} else {
						serverInfos.set(i, readedList.get(i));
					}
				} catch ( IndexOutOfBoundsException e ) {
					//serverInfos.add(i, null);
				}
			}
		
		
			
    	} catch(ArrayIndexOutOfBoundsException e) {
           
        } catch (FileNotFoundException e) {
           
        } catch (IOException e) {
           
        }
    	
    	
    }

    public ArrayList<ServerInfo> getServerInfos() {
        return serverInfos;
    }

    public void setServerInfos(ArrayList<ServerInfo> serverInfos) {
        this.serverInfos = serverInfos;
    }

    public boolean addServerInfo(ServerInfo newServerInfo) { 
        // implement your code here
    	serverInfos.add(newServerInfo);
    	return true;
    }

    public boolean updateServerInfo(int index, ServerInfo newServerInfo) { 
        // implement your code here
    	try {
    	serverInfos.set(index, newServerInfo);
    	}catch (IndexOutOfBoundsException e) {
    		return false;
    	}
    	return true;
    }
    
    public boolean removeServerInfo(int index) { 
        // implement your code here
    	try {
    		serverInfos.set(index, null);
    		}catch (IndexOutOfBoundsException e) {
        		return false;
        	}
    	return true;
    }

    public boolean clearServerInfo() { 
        // implement your code here
            for (int i = serverInfos.size() - 1 ; i >= 0; i--) {
            if (serverInfos.get(i) == null) {
                serverInfos.remove(i);
            }
        }
        
        return true;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < serverInfos.size(); i++) {
            if (serverInfos.get(i) != null) {
                s += "Server" + i + ": " + serverInfos.get(i).getHost() + " " + serverInfos.get(i).getPort() + "\n";
            }
        }
        return s;
    }

    // implement any helper method here if you need any
}