package application;

public class Product {
	
	private int code, quantity;
	private String name, description;
	private Double price;
	
	public Product() {
		
	}

	public Product(int code, int quantity, String name, String description, Double price) {
		this.code = code;
		this.quantity = quantity;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
