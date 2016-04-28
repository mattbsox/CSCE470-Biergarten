import java.io.Serializable;
import java.util.ArrayList;

class Beer implements Serializable{
	String name;
	String type;
	double color;
	double abv;
	double ibu;
	double origGrav;
	double finalGrav;
	String brewery;
	String description;
	//String imageLoc;

	public Beer(){}

	public Beer(String n, String t, double c, double a, double i, double oG, double fG, String brew){

		name 		= n;
		type 		= t;
		color 		= c;
		abv 		= a;
		ibu 		= i;
		origGrav 	= oG;
		finalGrav 	= fG;
		brewery 	= brew;
		//description = desc;
		//imageLoc = image;

	}

	public ArrayList getData(){
			
		ArrayList<Double> data = new ArrayList();
		
		data.add(color);
		data.add(abv);
		data.add(ibu);
		data.add(finalGrav);

		return data;

	}
}