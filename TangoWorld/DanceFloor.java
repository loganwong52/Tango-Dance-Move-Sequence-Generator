package TangoWorld;

import TangoWorld.Moves.Move;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DanceFloor {
	public static void printDanceSequence(List<Move> danceSequence){
		for(int i = 0; i < danceSequence.size(); i++){
			System.out.println(i + ". " + danceSequence.get(i));
		}
	}
	
	public static List<Object> getUserInput(){
		Scanner scanner = new Scanner(System.in);
		
		// Prompt user to enter song name
		System.out.print("Enter song name (default: Gato): ");
		String song = scanner.nextLine().trim();
		if (song.isEmpty()) song = "Gato";
		
		// prompt user to enter # of moves
		System.out.print("Enter number of moves (default: 100): ");
		String movesInput = scanner.nextLine().trim();
		int numberOfMoves = movesInput.isEmpty() ? 100 : Integer.parseInt(movesInput);
		
		// Prompt user to enter a # for the seed
		System.out.print("Enter seed number (default: 316): ");
		String seedInput = scanner.nextLine().trim();
		long seed = seedInput.isEmpty() ? 316 : Long.parseLong(seedInput);
		
		scanner.close();
		
		return Arrays.asList(song, numberOfMoves, seed);
	}
	
	public static void main(String[] args) {
		// Prompt user to enter song name, # of moves, and a # for seed
		List<Object> userInput = getUserInput();
		String song = userInput.get(0).toString();
		int numberOfMoves = (int) userInput.get(1);
		long seed = (long) userInput.get(2);
		
		System.out.println("Song: " + song);
		Tango tango = new Tango(song);
		
		// Print all options from root
		// Move current = tango.getRoot();
		// current.printPossibleNextMoves();
		
		// create random dance sequence
		List<Move> danceSequence = tango.createDance(numberOfMoves, seed);
		
		// Print sequence
		printDanceSequence(danceSequence);
	}
}
