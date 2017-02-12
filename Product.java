package inventory;

public abstract class Product {

	public String name;
	public Integer price;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}
}
