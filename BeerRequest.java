import java.io.Serializable;

public class BeerRequest implements Serializable{

	String beerName;
	String state;

	public BeerRequest(String beerName, String state){
		this.beerName = beerName;
		this.state = state;
	}
}