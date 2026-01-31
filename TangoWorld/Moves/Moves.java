package TangoWorld.Moves;

public enum Moves {
	// Basic steps
	WEIGHT_CHANGE("Weight Change"),
	STEP_FORWARD("Step Forward"),
	STEP_BACKWARD("Step Backward"),
	SIDE_STEP("Side Step"),
	ROCK_STEP("Rock Step"),
	
	// System changes
	CROSS_SYSTEM("Cross System"),
	
	// Figures
	FOLLOWERS_CROSS("Follower's Cross"),
	OUTSIDE_PARTNER("Outside Partner"),
	FRONT_OCHO("Front Ocho"),
	BACK_OCHO("Back Ocho"),
	BOLEO("Boleo"),
	MOLINETES("Molinetes"),
	FOOT_DRAG("Foot Drag"),
	PARADA("Parada"),
	SACADA("Sacada"),
	GANCHO("Gancho"),
	OCHO_CORTADO("Ocho Cortado"),
	VOLCADA("Volcada");
	
	private final String displayName;
	
	Moves(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
}
