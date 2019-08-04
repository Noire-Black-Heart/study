import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.*;
import java.net.*;

public class CatchUpThread implements Runnable {
    /*
        student Michael Matta(460157667) helped me with the idea of 
        creating a separate thread for catch up action + error catching& handling
        he advised me on the blockchain reconstruction part
    */
    private Blockchain blockchain;
    private int port;
    private String host;

    public CatchUpThread(String host, int port, Blockchain blockchain) {
        this.blockchain = blockchain;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {

        //initial catchup
        try {

            //create a new socket to init catchup
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 2000);

            //send the message forward
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.print("cu\n");
            pw.flush();

            //handling the returned message

            //use the naive solution, put all remote blocks into a list, then restore them one by one
            ArrayList<Block> remoteBlockList = new ArrayList<Block>();

            String currentHash = Base64.getEncoder().encodeToString(new byte[32]);

            // initiate everything for one block transmission
            while (true) {
                try {

                    //create input streams to get block
                    ObjectInputStream blockIn = null;
                    Block received = null;
                    try{
                    blockIn = new ObjectInputStream(socket.getInputStream());

                    received = (Block) blockIn.readObject();
                        
                    } catch (EOFException receivedEoF){
                        break;
                    }

                    if(received == null) {
                        break;
                    }
                    
                    remoteBlockList.add(received);

                    //renew current hash to progress
                    try{
                    currentHash = Base64.getEncoder().encodeToString(received.calculateHash());
                    } catch(NullPointerException receivedNullPointer){
                        return;
                    }

                    String genesisHash = Base64.getEncoder().encodeToString(new byte[32]);

                    String prevHash = Base64.getEncoder().encodeToString(received.getPreviousHash());
                    //break if we got the genesis block
                    if(prevHash.equals(genesisHash)) {
                        break;
                    }

                    //close writer and socket for last block
                    pw.close();
                    socket.close();

                    //create new socket for transmitting one block object
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(host, port), 2000);

                    //sending cu command to remote to get block
                    String message = "cu|" + prevHash;
                    pw = new PrintWriter(socket.getOutputStream(), true);
                    pw.print(message + "\n");
                    pw.flush();

                    //Thread.sleep(100);

                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            //after receiving all blocks, assemble them into a new list, and replace current one
            if(remoteBlockList.size() == 0) {
                //blockchain.setLength(0);
            }
            else {
                 blockchain.setLength(0);
                 //reverse the order of remoteBlockList, so the order is genesis --> head
                Collections.reverse(remoteBlockList);
                blockchain.setHead(remoteBlockList.get(0));
                Block prevHead = remoteBlockList.get(0);
                
                for(Block b : remoteBlockList){
                    //check if this is the first iteration
                    if(Base64.getEncoder().encodeToString(b.calculateHash()).equals(Base64.getEncoder().encodeToString(prevHead.calculateHash()))){
                        continue;
                    }
                    //if not first iteration, set current block as new head, set prev, advace prev
                    b.setPreviousBlock(prevHead);
                    blockchain.setHead(b);
                    prevHead = b; 
                }
                blockchain.setLength(remoteBlockList.size());

            }

            //close printWriter and socket
            pw.close();
            socket.close();

        } catch (IOException e) {

        }

    }
}
