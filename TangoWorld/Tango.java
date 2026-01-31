package TangoWorld;

import TangoWorld.DanceSystems.CrossSystem;
import TangoWorld.DanceSystems.DanceSystem;
import TangoWorld.DanceSystems.OutsidePartner;
import TangoWorld.DanceSystems.ParallelSystem;
import TangoWorld.Moves.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tango extends Dance {
	// All moves as fields - single instances
	private Move weightChange = new WeightChange();
	private Move parallelSystem = new ParallelSystem();
	private Move stepForward = new StepForward();
	private Move stepBackward = new StepBackward();
	private Move sideStep = new SideStep();
	private Move rockStep = new RockStep();
	private Move crossSystem = new CrossSystem();
	private Move followersCross = new FollowersCross();
	private Move outsidePartner = new OutsidePartner();
	
	private Move frontOcho = new FrontOcho();
	private Move backOcho = new BackOcho();
	private Move boleo = new Boleo();
	private Move molinetes = new Molinetes();
	private Move parada = new Parada();
	private Move sacada = new Sacada();
	private Move gancho = new Gancho();
	
	private Move footDrag = new FootDrag();
	// private Move ochoCortado = new OchoCortado();
	// private Move volcada = new Volcada();
	
	// The very first move will be a weight change
	private Move root;
	
	private DanceSystem state;
	
	public Tango(String song) {
		super(song);
		// Hard code the Rules of Tango
		buildMoveTree();
		
		// The first thing you do is change weight (to make sure both people are on the same foot)
		root = weightChange;
		state = DanceSystem.PARALLEL_SYSTEM;
	}
	
	private void buildMoveTree() {
		// Connect moves using addPossibleNextMove()
		weightChange.addPossibleNextMove(weightChange);
		weightChange.addPossibleNextMove(stepForward);
		weightChange.addPossibleNextMove(stepBackward);
		weightChange.addPossibleNextMove(sideStep);
		weightChange.addPossibleNextMove(rockStep);
		
		stepForward.addPossibleNextMove(stepForward);
		stepForward.addPossibleNextMove(weightChange);
		stepForward.addPossibleNextMove(stepBackward);
		stepForward.addPossibleNextMove(sideStep);
		stepForward.addPossibleNextMove(rockStep);
		stepForward.addPossibleNextMove(crossSystem);
		stepForward.addPossibleNextMove(outsidePartner);
		
		crossSystem.addPossibleNextMove(stepForward);
		crossSystem.addPossibleNextMove(stepBackward);
		crossSystem.addPossibleNextMove(frontOcho);
		crossSystem.addPossibleNextMove(backOcho);
		crossSystem.addPossibleNextMove(followersCross);
		crossSystem.addPossibleNextMove(parallelSystem);
		
		parallelSystem.addPossibleNextMove(weightChange);
		parallelSystem.addPossibleNextMove(stepForward);
		parallelSystem.addPossibleNextMove(stepBackward);
		parallelSystem.addPossibleNextMove(sideStep);
		
		
		outsidePartner.addPossibleNextMove(stepForward);
		outsidePartner.addPossibleNextMove(followersCross);
		outsidePartner.addPossibleNextMove(sideStep);
		outsidePartner.addPossibleNextMove(stepBackward);
		
		followersCross.addPossibleNextMove(crossSystem);
		followersCross.addPossibleNextMove(stepForward);
		followersCross.addPossibleNextMove(parallelSystem);
		
		stepBackward.addPossibleNextMove(stepBackward);
		stepBackward.addPossibleNextMove(stepForward);
		stepBackward.addPossibleNextMove(weightChange);
		stepBackward.addPossibleNextMove(sideStep);
		stepBackward.addPossibleNextMove(rockStep);
		
		sideStep.addPossibleNextMove(sideStep);
		sideStep.addPossibleNextMove(stepBackward);
		sideStep.addPossibleNextMove(stepForward);
		sideStep.addPossibleNextMove(weightChange);
		sideStep.addPossibleNextMove(rockStep);
		sideStep.addPossibleNextMove(frontOcho);
		sideStep.addPossibleNextMove(backOcho);
		sideStep.addPossibleNextMove(molinetes);
		sideStep.addPossibleNextMove(sacada);
		
		rockStep.addPossibleNextMove(rockStep);
		rockStep.addPossibleNextMove(stepBackward);
		rockStep.addPossibleNextMove(stepForward);
		rockStep.addPossibleNextMove(weightChange);
		rockStep.addPossibleNextMove(sideStep);
		
		frontOcho.addPossibleNextMove(frontOcho);
		frontOcho.addPossibleNextMove(molinetes);
		frontOcho.addPossibleNextMove(backOcho);
		frontOcho.addPossibleNextMove(sacada);
		frontOcho.addPossibleNextMove(parada);
		frontOcho.addPossibleNextMove(boleo);
		frontOcho.addPossibleNextMove(gancho);
		frontOcho.addPossibleNextMove(crossSystem);
		frontOcho.addPossibleNextMove(parallelSystem);
		
		backOcho.addPossibleNextMove(backOcho);
		backOcho.addPossibleNextMove(frontOcho);
		backOcho.addPossibleNextMove(molinetes);
		backOcho.addPossibleNextMove(parada);
		backOcho.addPossibleNextMove(boleo);
		backOcho.addPossibleNextMove(gancho);
		backOcho.addPossibleNextMove(crossSystem);
		backOcho.addPossibleNextMove(parallelSystem);
		
		boleo.addPossibleNextMove(frontOcho);
		boleo.addPossibleNextMove(stepForward);
		boleo.addPossibleNextMove(molinetes);
		boleo.addPossibleNextMove(parada);
		boleo.addPossibleNextMove(gancho);
		
		molinetes.addPossibleNextMove(frontOcho);
		molinetes.addPossibleNextMove(molinetes);
		molinetes.addPossibleNextMove(backOcho);
		molinetes.addPossibleNextMove(sacada);
		molinetes.addPossibleNextMove(parada);
		molinetes.addPossibleNextMove(boleo);
		molinetes.addPossibleNextMove(gancho);
		molinetes.addPossibleNextMove(crossSystem);
		molinetes.addPossibleNextMove(parallelSystem);
		molinetes.addPossibleNextMove(stepForward);
		molinetes.addPossibleNextMove(footDrag);
		
		footDrag.addPossibleNextMove(parada);
		
		sacada.addPossibleNextMove(crossSystem);
		sacada.addPossibleNextMove(stepForward);
		sacada.addPossibleNextMove(parada);
		sacada.addPossibleNextMove(molinetes);
		
		parada.addPossibleNextMove(stepForward);
		parada.addPossibleNextMove(stepBackward);
		parada.addPossibleNextMove(gancho);
		parada.addPossibleNextMove(frontOcho);
		parada.addPossibleNextMove(backOcho);
		parada.addPossibleNextMove(molinetes);
		
		gancho.addPossibleNextMove(frontOcho);
		gancho.addPossibleNextMove(crossSystem);
		gancho.addPossibleNextMove(stepForward);
		
	}
	
	public List<Move> createDance(int numberOfMoves, long seed) {
		Random random = new Random(seed);
		List<Move> danceSequence = new ArrayList<>();
		Move currentMove = getRoot();
		// ensure you start in parallel system
		state = DanceSystem.PARALLEL_SYSTEM;
		
		for (int i = 0; i < numberOfMoves; i++) {
			// Add current move to sequence
			danceSequence.add(currentMove);
			
			// If outside partner AND follower's cross, decide to be in cross system or parallel system
			if(state.equals(DanceSystem.OUTSIDE_PARTNER)
					&& currentMove.getName().equals(Moves.FOLLOWERS_CROSS.getDisplayName())){
				int systemCoinFlip = random.nextInt(2);

				if(systemCoinFlip == 0){
					//parallel
					currentMove = parallelSystem;
				}else{
					// cross
					currentMove = crossSystem;
				}
				danceSequence.add(currentMove);
			}
			
			// If outside partner AND side step, HARD CODE you go into parallel system
			if(state.equals(DanceSystem.OUTSIDE_PARTNER) && currentMove.getName().equals(Moves.SIDE_STEP.getDisplayName())){
				currentMove = parallelSystem;
				danceSequence.add(currentMove);
			}
			
			// If cross system AND chang weight, HARD CODE you go into parallel system
			if(state.equals(DanceSystem.CROSS_SYSTEM) && currentMove.getName().equals(Moves.WEIGHT_CHANGE.getDisplayName())){
				currentMove = parallelSystem;
				danceSequence.add(currentMove);
			}
			
			// If necessary, update state based on current move
			updateState(currentMove);
			
			// Get possible next moves from current position
			List<Move> possibleNext = currentMove.getPossibleNextMoves();
			
			// If no possible moves, end the dance
			if (possibleNext.isEmpty()) {
				break;
			}
			// Otherwise, pick a random next move
			int randomIndex = random.nextInt(possibleNext.size());
			currentMove = possibleNext.get(randomIndex);
			
			
			// REROLL
			while(state.getDisplayName().equals(currentMove.getName())){
				// if state == Cross System && the randomly selected currentMove is cross system, then you can't go into it again...
				// OR if state == Parallel System && the randomly selected currentMove is Parallel system
				// same with Outside Partner
				randomIndex = random.nextInt(possibleNext.size());
				currentMove = possibleNext.get(randomIndex);
			}
			
			// No side step when in cross system
			while (state.equals(DanceSystem.CROSS_SYSTEM) &&
					currentMove.getName().equals(Moves.SIDE_STEP.getDisplayName())){
				randomIndex = random.nextInt(possibleNext.size());
				currentMove = possibleNext.get(randomIndex);
			}
			
		}
		
		return danceSequence;
	}
	
	private void updateState(Move move){
		String moveName = move.getName();
		
		switch (moveName) {
			case "Cross System" -> state = DanceSystem.CROSS_SYSTEM;
			case "Outside Partner" -> state = DanceSystem.OUTSIDE_PARTNER;
			case "Parallel System" -> state = DanceSystem.PARALLEL_SYSTEM;
		}
	}
	
	public DanceSystem getState() {
		return state;
	}
	
	public Move getRoot() {
		return root;
	}
	
	// Optional: Getters for individual moves if needed
	public Move getWeightChange() { return weightChange; }
	public Move getStepForward() { return stepForward; }
}
