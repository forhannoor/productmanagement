// Represents items in the inventory.
// Contains information about respective product, i.e. stock.

public class Item {
	private byte [] id;
    private Product product;
	private double currentStock;
	
	public Item(Product product, double currentStock) {
		this.id = Utility.generateId(product);
		this.product = product;
		this.currentStock = currentStock;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(double currentStock) {
		this.currentStock = currentStock;
	}

	public byte[] getId() {
		return id;
	}
}