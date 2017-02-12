package inventory;

/**
 * @author danEx
 *
 */
public class Main {
	public static void main(String[] args) {
		StorageManager store = new StorageManager();		
		StoreCapable capable = new PersistentStore();
		
        store.addStorage(capable);

        store.addCDProduct("Bud Spencer and Terence Hill Movie Soundtrack", 2016, 42);
        store.addBookProduct("Kötelezõk röviden", 2751, 120);

        System.out.println(store.listProducts());
        System.out.println(store.getTotalProductPrice());

	}

}
