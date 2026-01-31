package TangoWorld;

public abstract class Dance {
	private final String song;
	
	public Dance(String song){
		this.song = song;
	}
	
	public String getSong() {
		return song;
	}
}
