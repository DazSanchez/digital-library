package me.hsanchez.digital_library.dto;

/**
 *
 * @author hsanchez
 */
public class DeliveryTimeDTO {
    private int id;
    private int time;
    private String unit;
    
    public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public int getTime() {
		return time;
	}
    
    public void setTime(int time) {
		this.time = time;
	}
    
    public String getUnit() {
		return unit;
	}
    
    public void setUnit(String unit) {
		this.unit = unit;
	}
    
    public String toString() {
    	return this.time + " " + this.unit;
    }
}
