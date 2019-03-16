public class Transaction {
    private String sender;
    private String content;

    // getters and setters
    public void setSender(String sender) { this.sender = sender; }
    public void setContent(String content) { this.content = content; }
    public String getSender() { return sender; }
    public String getContent() { return content; }

    public String toString() {
        return String.format("|%s|%70s|\n", sender, content);
    }

    // implement helper functions here if you need any
    public boolean isValidTransaction() {
    	
    			// check sender
    			if(!this.getSender().matches("[a-z]{4}[0-9]{4}")) {
    				return false;
    			}
    			
    			// check content length
    			if(this.getContent().length() > 70) {
    				return false;
    			}
    			
    	
    	return true;
    }
    
    
    
}
