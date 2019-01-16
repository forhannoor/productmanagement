public class Item {
	private String sku;
    private String productLine;
	private String productType;
	private Product product;
	private int stock;
	private double retailPrice;
	private double profitMargin;

	private final int SKU_LENGTH = 20;
	private final int TITLE_LENGTH = 50;
	private final int STOCK_LENGTH = 8;
	private final int UP_LENGTH = 8;
	private final int RP_LENGTH = 8;
	private final int PM_LENGTH = 8;
	
	private static StringBuilder sb;
	
	public Item(String productLine, String productType, Product product, int stock, double retailPrice, double profitMargin){
		this.productLine = productLine;
		this.productType = productType;
		this.product = product;
		this.stock = stock;
		this.retailPrice = retailPrice;
		this.profitMargin = profitMargin / 100;
	}
	
	public void generateSku(){
		if(sb == null){
			sb = new StringBuilder();
		}
		
		sb.setLength(0);
		String [] tokens = productLine.split(" ");
		
		if(tokens.length == 1){
			sb.append(tokens[0].charAt(0));
			sb.append(tokens[0].charAt(1));
		}

		else{
			sb.append(tokens[0].charAt(0));
			sb.append(tokens[1].charAt(0));
		}
		
		sb.append("-");
		tokens = productType.split(" ");
		
		if(tokens.length == 1){
			sb.append(tokens[0].charAt(0));
			sb.append(tokens[0].charAt(1));
		}

		else{
			sb.append(tokens[0].charAt(0));
			sb.append(tokens[1].charAt(0));
		}
		
		sb.append("-");
		sb.append(product.getTitle().substring(0, 2));
		tokens = product.getDescription().split(" ");
		
		for(String t: tokens){
			sb.append("-");
			sb.append(t.substring(0, 2));
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
	
	public String toString(){
		int i, spaceCount;
		sb.setLength(0);
		sb.append(sku);
		spaceCount = SKU_LENGTH - sku.length();

		for(i = 0; i < spaceCount; i++){
			sb.append(" ");
		}

		sb.append(product.getTitle());
		spaceCount = TITLE_LENGTH - product.getTitle().length();

		for(i = 0; i < spaceCount; i++){
			sb.append(" ");
		}

		sb.append(stock);
		spaceCount = STOCK_LENGTH - String.valueOf(stock).length();

		for(i = 0; i < spaceCount; i++){
			sb.append(" ");
		}

		sb.append(product.getUnitPrice());
		spaceCount = UP_LENGTH - String.valueOf(product.getUnitPrice()).length();

		for(i = 0; i < spaceCount; i++){
			sb.append(" ");
		}

		sb.append(retailPrice);
		spaceCount = RP_LENGTH - String.valueOf(retailPrice).length();

		for(i = 0; i < spaceCount; i++){
			sb.append(" ");
		}

		System.out.println(sb.toString());
		return sb.toString();
	}
}