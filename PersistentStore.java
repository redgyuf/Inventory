package inventory;

import java.util.ArrayList;

public class PersistentStore extends Store{

	private ArrayList<Product> productsArrayList = new ArrayList<Product>();
	
	public void storeProduct(Product product){
		productsArrayList.add(product);
	}

	@Override
	public ArrayList<Product> getAllProduct() {
		return productsArrayList;
	}

	@Override
	public void storeCDProduct(String name, int price, int tracks) {
		store(createProduct("CD", name, price, tracks));
		
	}

	@Override
	public void storeBookProduct(String name, int price, int size) {
		store(createProduct("Book", name, price, size));
		
	}
}