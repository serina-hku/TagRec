package social;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsNetwork {
	
	private HashMap<String, ArrayList<String>> friendNetwork;
	
	FriendsNetwork(String filepath){
		
		File friendsFile = new File(filepath);
		if (!friendsFile.exists()){
			System.out.println("the file doesnt exist in path: " + filepath + "\n");
		}else{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(friendsFile));
				this.friendNetwork = createFriendNetwork(bufferedReader);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private HashMap< String , ArrayList<String>> createFriendNetwork(BufferedReader bufferedReader){
		HashMap<String, ArrayList<String>> friendNetwork = new HashMap<String, ArrayList<String>>();
		
		String line = "";
		try {
			while((line = bufferedReader.readLine()) != null){
				String[] tokens = line.split("\t");
				if (tokens.length == 2){
					String user = tokens[0];
					String friend = tokens[1];
					if (!friendNetwork.containsKey(user)){
						friendNetwork.put(user, new ArrayList<String>());
						friendNetwork.get(user).add(friend);
					}else{
						friendNetwork.get(user).add(friend);
					}
				}
			}
			
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friendNetwork;
	}
	
	public HashMap<String,	ArrayList<String>> getFriendNetwork(){
		return friendNetwork;
	}
	
	public static void main(String[] args){
		FriendsNetwork friend_network = new FriendsNetwork("/home/spujari/SNET/dataset/TwitterNetwork/follow_nw.csv");
		HashMap<String, ArrayList<String>> friendNetwork = friend_network.getFriendNetwork();
		friend_network.getFriendNetwork().size();
	}
}
