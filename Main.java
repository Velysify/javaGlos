import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class Main {
	HashMap<String, String> wordList = new HashMap<String, String>();
	ArrayList<String> keysAsArray;
	Main(){
		fillList();
	}
	private void fillList(){
		File file = new File("ordlista.txt");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] splitLine = line.split("=");
		       wordList.put(splitLine[0].trim(), splitLine[1].trim());
		    }
		    keysAsArray = new ArrayList<String>(wordList.keySet());
		} catch (FileNotFoundException e) {
			System.out.print("File not Found! What went wrong!?");
		} catch (IOException e) {
			System.out.print("IO Exception");
		}
		return;
	}
	
	public String[] getWord(){
		Random r = new Random();
		String word = keysAsArray.get(r.nextInt(keysAsArray.size()));
		String description = wordList.get(word);
		String[] returnString = {word, description};
		return returnString;
	}
	public static void main(String[] args) {
		new Gui();
	}

}
