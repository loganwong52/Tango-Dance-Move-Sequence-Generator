package TangoWorld.Moves;

//tango moves:
// weight change
// step forwad
// step backward
// side step
// rock step
// cross system

// follower's cross
// outside partner
// front ochos
// back ochos
// boleo
// molinetes
// foot drag
// parada
// sacada

// gancho
// ocho cortado
// volcada


import TangoWorld.DanceSystems.DanceSystem;

import java.util.ArrayList;
import java.util.List;

public class Move {
	private String name;
	// private Move nextMove;
	private List<Move> possibleNextMoves;
	
	public Move(String name){
		this.name = name;
		this.possibleNextMoves = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public void addPossibleNextMove(Move nextMove) {
		this.possibleNextMoves.add(nextMove);
	}
	
	public List<Move> getPossibleNextMoves() {
		return possibleNextMoves;
	}
	
	public String getName() {
		return name;
	}
	
	public void printPossibleNextMoves(){
		System.out.println("From " + this.name + " you can:");
		for (Move option : getPossibleNextMoves()) {
			System.out.println("  - " + option.getName());
		}
	}
}
