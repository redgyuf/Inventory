package inventory;

public class CDProduct extends Product{
	public int numOfTracks;
	
	public CDProduct(String name, int price, int numOfTracks){
		super(name, price);
		this.numOfTracks = numOfTracks;
	}
}
