public class Product {
    private String title;
	private String description;
	private double unitPrice;
	
	public Product(String title, String description, double unitPrice){
		this.title = title;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	
	public String getTitle(){ return title; }
	
	public void setTitle(String title){ this.title = title; }
	
	public String getDescription(){ return description; }
	
	public void setDescription(String description){ this.description = description; }
	
	public double getUnitPrice(){ return unitPrice; }
	
	public void setUnitPrice(double unitPrice){ this.unitPrice = unitPrice; }
	
	public String toString(){ return title + " " + description + " " + unitPrice; }
}