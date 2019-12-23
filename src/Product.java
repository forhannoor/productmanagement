// Represents actual product.

public class Product {
    private String title;
	private String description;
	private double vendorPrice;
	private double retailPrice;
	
	public Product(String title, String description, double vendorPrice, double retailPrice){
		this.title = title;
		this.description = description;
		this.vendorPrice = vendorPrice;
		this.retailPrice = retailPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getVendorPrice() {
		return vendorPrice;
	}

	public void setVendorPrice(double vendorPrice) {
		this.vendorPrice = vendorPrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@Override
	public String toString() {
		return title + description + vendorPrice + retailPrice;
	}
}