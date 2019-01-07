public class Item {
    private String productLine;
	private String productType;
	private Product product;
	private int stock;
	private double retailPrice;
	private double profitMargin;
	
	public Item(String productLine, String productType, Product product, int stock, double profitMargin){
		this.productLine = productLine;
		this.productType = productType;
		this.product = product;
		this.stock = stock;
		this.profitMargin = profitMargin / 100;
		double p = product.getUnitPrice();
		this.retailPrice = p + p * profitMargin;
	}
	
	public String toString(){ return productLine + " " + productType + " " + product + " " + stock + " " + retailPrice + " " + profitMargin; }
}