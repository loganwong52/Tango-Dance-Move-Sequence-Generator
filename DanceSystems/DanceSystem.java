package TangoWorld.DanceSystems;

public enum DanceSystem {
	PARALLEL_SYSTEM("Parallel System"),
	CROSS_SYSTEM("Cross System"),
	OUTSIDE_PARTNER("Outside Partner");
	
	private final String displayName;
	
	DanceSystem(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
}
