public class Item {
	private String sku;
    private String productLine;
	private String productType;
	private Product product;
	private int stock;
	private double retailPrice;
	private double profitMargin;
	
	private static StringBuilder sb = new StringBuilder();
	
	public Item(String productLine, String productType, Product product, int stock, double retailPrice, double profitMargin){
		this.productLine = productLine;
		this.productType = productType;
		this.product = product;
		this.stock = stock;
		this.retailPrice = retailPrice;
		this.profitMargin = profitMargin / 100;
	}
	
	public void generateSku(){
		sb.setLength(0);
		String [] tokens = productLine.split(" ");
		
		for(String t: tokens){
			sb.append(t.charAt(0));
		}
		
		sb.append("-");
		tokens = productType.split(" ");
		
		for(String t: tokens){
			sb.append(t.charAt(0));
		}
		
		sb.append("-");
		sb.append(product.getTitle().substring(0, 2));
		tokens = product.getDescription().split(" ");
		
		for(String t: tokens){
			sb.append("-");
			
			if(t.length() < 4){
				sb.append(t);
			}
			
			else{
				sb.append(t.substring(0, 2));
			}
		}
		
		sku = sb.toString().toUpperCase();
	}
	
	public String getSku(){ return sku; }
	
	public String getProductLine(){ return productLine; }
	
	public void setProductLine(String p){ productLine = p; }
	
	public String getProductType(){ return productType; }
	
	public void setProductType(String p){ productType = p; }
	
	public Product getProduct(){ return product; }
	
	public void setProduct(Product p){ product = p; }
	
	public int getStock(){ return stock; }
	
	public void setStock(int s){ stock = s; }
	
	public double getRetailPrice(){ return retailPrice; }
	
	public void setRetailPrice(double p){ retailPrice = p; }
	
	public double getProfitMargin(){ return profitMargin; }
	
	public void setProfitMargin(double p){ profitMargin = p; }
	
	public String toString(){ return sku + " " + productLine + " " + productType + " " + product + " " + stock + " " + retailPrice + " " + profitMargin; }
}