import java.lang.Thread;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class RequestHandler implements Runnable{
	Socket clientSocket;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	BeerRequest request;
	Map<String, BeerCluster> beerClusters;

	public RequestHandler(Socket socket, Map<String, BeerCluster> beers){
		clientSocket = socket;
		beerClusters = beers;
	}

	//process request from socket
	public void run(){
		try{
			ois = new ObjectInputStream(clientSocket.getInputStream());
			request = (BeerRequest)ois.readObject();
			sendResponse(processRequeset(request));
		}
		catch(Exception e){
			System.out.println("There was an issue handling a client request.");
		}

	}

	public Beer findBeer(String beerName){

		//go through beer map and find the beer object, go to its type cluster, run similarity check, and return top 5 results

		//need to add state bounds

		Iterator it = beerClusters.entrySet().iterator();
    	while (it.hasNext()) {
        	Map.Entry pair = (Map.Entry)it.next();

        	BeerCluster clust = (BeerCluster)(pair.getValue());

			for(Beer b: clust.beers){
				if(b.name.equals(beerName)){
					return b;
				}
			}

        	it.remove(); // avoids a ConcurrentModificationException
    	}

		return null;

	}

	public BeerResponse processRequeset(BeerRequest b){
		
		BeerResponse response = new BeerResponse();

		Beer searchBeer = findBeer(b.beerName);

		if(searchBeer!=null){
			BeerCluster cluster = beerClusters.get(searchBeer.type);

			ArrayList<Tuple<Beer, Double>> beerSims = cluster.similarBeers(searchBeer);

			for(int i = 0; i < beerSims.size() && i < 5; i++){
				response.addBeer(beerSims.get(i).x);
			}
		}

		return response;
	}

	public void sendResponse(BeerResponse b){
		try{
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(b);
			oos.flush();
		}

		catch(Exception e){
			System.out.println("Something broke when responding to the client.");
		}
	}


}