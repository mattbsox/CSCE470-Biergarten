import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.lang.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class BeerServer implements Runnable{

	private Socket client;
	private ServerSocket socket;
	private final ExecutorService clientPool = Executors.newFixedThreadPool(10);
	private Map<String, BeerCluster> beerMap;

	public BeerCluster beers = new BeerCluster();;

	public void getBeerData(){
		
		Beer shinerBock = new Beer("Shiner Bock", "Bock", 18.0, 4.4, 13, 1.046, 1.009, "Spoetzl Brewery");
		Beer guinnessDraught = new Beer("Guinness Draught", "Irish Dry Stout", 40, 4.2, 45, 1.038, 1.006, "Guinnatess");
		Beer shinerPremium = new Beer("Shiner Premium", "Pale Lager", 6.0, 4.4, 13, 1.046, 1.009, "Spoetzl Brewery");
		Beer shinerBlack = new Beer("Shiner Black Lager", "Black Beer", 25.0, 4.9, 26, 1.047, 1.015, "Spoetzl Brewery");
		Beer fullSailPilsner = new Beer("Full Sail Pilsner", "Pilsner", 3, 6, 60, 1.042, 1.007, "Full Sail Brewing");
		Beer luchesaLager = new Beer("Luchesa Lager", "Pilsner", 5, 4.8, 35, 1.040, 1.008, "Oasis Texas brewing Company");
		Beer blarneySisters = new Beer("Blarney Sisters Stout", "Irish Stout", 42, 4.1, 38, 1.01, 1.008, ""); 
		Beer samBlackLager  = new Beer("Sam Adams Black Lager", "Black Beer", 25, 4.9, 19, 1.044, 1.010, "Boston Beer");
		Beer blackBavarian  = new Beer("Black Bavarian", "Black Beer", 30, 5.86, 32, 1.048, 1.014, "Sprecher Brewery");

		beers.addBeer(shinerBock);
		beers.addBeer(guinnessDraught);
		beers.addBeer(shinerPremium);
		beers.addBeer(shinerBlack);
		beers.addBeer(luchesaLager);
		beers.addBeer(fullSailPilsner);
		beers.addBeer(blarneySisters);
		beers.addBeer(samBlackLager);
		beers.addBeer(blackBavarian);
	}

	public void loadBeers(){
		beerMap  = new HashMap<String, BeerCluster>();
		beerMap.put("all beers", beers);
	}

	public void startServer(){
		loadBeers();
        run();
	}

	public void run() {
        try {
            socket = new ServerSocket(6969);
            
            while (true) {
                Socket clientSocket = socket.accept();
                clientPool.execute(new RequestHandler(clientSocket, beerMap));
            }
        } 
        catch (Exception e) {
            System.err.println("Unable to process client request");
            e.printStackTrace();
            clientPool.shutdown();
        }
    }
}