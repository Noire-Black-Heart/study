package reader;

public class TableHolder {
	String colour;
	Double friction;
	Long tableX;
	Long tableY;
	//getters and setters
	public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
    
    public void setFriction(Double friction) {
        this.friction = friction;
    }

    public Double getFriction() {
        return friction;
    }
    
    public void setTableX(Long x) {
        this.tableX = x;
    }

    public Long getTableX() {
        return tableX;
    }
    
    public void setTableY(Long y) {
        this.tableY = y;
    }

    public Long getTableY() {
        return tableY;
    }
}
