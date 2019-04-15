import java.util.ArrayList;
import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ServerInfoList {

    ArrayList<ServerInfo> serverInfos;

    public ServerInfoList() {
        serverInfos = new ArrayList<>();
    }

    public void initialiseFromFile(String filename) {
        // implement your code here
    	try {
    	//create reader
			BufferedReader configReader = Files.newBufferedReader(Paths.get(filename));
		while()	
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
    	}catch (ArrayIndexOutOfBoundsException e) {
    		return false;
    	}
    	return true;
    }
    
    public boolean removeServerInfo(int index) { 
        // implement your code here
    	try {
        	serverInfos.remove(index);
        	}catch (ArrayIndexOutOfBoundsException e) {
        		return false;
        	}
        	return true;
    }

    public boolean clearServerInfo() { 
        // implement your code here
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