import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.lang.Math;

public class BeerCluster{
	ArrayList<Beer> beers;

	public BeerCluster(){

		beers = new ArrayList<Beer>();
	}

	public BeerCluster(ArrayList<Beer> b){

		beers = b;
	}

	public void addBeer(Beer b){
		beers.add(b);
	}

	public ArrayList<Tuple<Beer, Double>> similarBeers(Beer b){
		ArrayList<Tuple<Beer, Double>> mostSimilarBeers = new ArrayList<Tuple<Beer, Double>>();

		for(Beer beer: beers){
			if(beer != b){
				double cosine = cosineSim(b, beer);
				mostSimilarBeers.add(new Tuple<Beer, Double>(beer, cosine));
			}
		}

		Comparator<Tuple<Beer, Double>> comparator = new Comparator<Tuple<Beer, Double>>(){
	        public int compare(Tuple<Beer, Double> tupleA, Tuple<Beer, Double> tupleB){
	            return -1 * tupleA.y.compareTo(tupleB.y);
	        }
    	};

    	Collections.sort(mostSimilarBeers, comparator);

		return mostSimilarBeers;
	}

	public double cosineSim(Beer b1, Beer b2){
		double sim = 0;

		ArrayList<Double> b1Data = b1.getData();
		ArrayList<Double> b2Data = b2.getData();

		double dotP = 0;

		double b1Mag = 0;
		double b2Mag = 0;

		for(int i = 0; i < b1Data.size(); i ++){
			dotP += b1Data.get(i) * b2Data.get(i);
			b1Mag += Math.pow(b1Data.get(i), 2);
			b2Mag += Math.pow(b2Data.get(i), 2);
		}

		b1Mag = Math.sqrt(b1Mag);
		b2Mag = Math.sqrt(b2Mag);

		sim = dotP/(b1Mag*b2Mag);

		return sim;
	}

}