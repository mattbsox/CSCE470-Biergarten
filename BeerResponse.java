import java.io.Serializable;
import java.util.ArrayList;

public class BeerResponse implements Serializable{

	ArrayList<Beer> beers;

	public BeerResponse(){

		beers = new ArrayList<Beer>();
	}

	public void addBeer(Beer b){
		beers.add(b);
	}


}