public class Product {
    
    private int id;
    private String name;
    private int category;
    private String brand;
    private float price;
    private String unit;
    private String img;
	
    public Product(int id, String name, int category, String brand, float price, String unit, String img) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.brand = brand;
		this.price = price;
		this.unit = unit;
		this.img = img;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", brand=" + brand + ", price="
				+ price + ", unit=" + unit + ", img=" + img + "]";
	}
}