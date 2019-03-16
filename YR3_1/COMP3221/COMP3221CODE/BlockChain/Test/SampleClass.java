import static org.junit.Assert.assertTrue;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.concurrent.atomic.AtomicBoolean;

public class SampleClass {

    private ByteArrayOutputStream outContent;
    private ServerSocket s;
    private Socket client;
	private AtomicBoolean stop;

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }  
	
    @Test
	public void sampleTest() throws Exception {
        stop = new AtomicBoolean(false);
        Thread t = new Thread() {
            public void run() {
                try {
                    s = new ServerSocket(8333);
                    client = s.accept();
                    serverHandler(client.getInputStream(), client.getOutputStream());
                    client.close();
                    s.close();
                } catch (IOException e) {

                }
            }
        };
        t.start();

        Thread.sleep(500);
        String userInput = "tx|abcd1234|123456\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        BlockchainClient.main(new String[]{"localhost", "8333"});
        assertTrue("Accepted\n\n".equals(new String(outContent.toByteArray())));
        stop.getAndSet(true);
    }
	
	public void serverHandler(InputStream clientInputStream, OutputStream clientOutputStream) {
        Blockchain blockchain = new Blockchain();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientInputStream));
		PrintWriter outWriter = new PrintWriter(clientOutputStream, true);

		while (!stop.get()) {
			// TODO: your server here
		}
    }
	
	  @After
    public void cleanUpStreams() {
        System.setOut(null);
    }
}