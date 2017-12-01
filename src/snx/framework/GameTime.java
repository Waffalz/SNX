package snx.framework;


/**
 * Represents time in the game
 */
public class GameTime {
	
	private TimeSpan currentTime;
	
	private TimeSpan elapsedTime;
	
	/**
	 * Creates a new GameTimes using TimeSpans
	 * @param current The span of time since the game started
	 * @param elapsed The span of time since the last event that created this GameTime
	 */
	public GameTime(TimeSpan current, TimeSpan elapsed){
		currentTime = current;
		elapsedTime = elapsed;
	}
	
	/**
	 * Creates a new GameTime using longs
	 * @param current The time since the game started
	 * @param elapsed The time since the last event that created this GameTime
	 */
	public GameTime(long current, long elapsed){
		currentTime = new TimeSpan(current);
		elapsedTime = new TimeSpan(elapsed);
	}
	
	/**
	 * Gets the amount of time since the game first started running
	 * @return The span of time since the game started
	 */
	public TimeSpan getTime(){
		return currentTime;
	}
	
	/**
	 * Gets the amount of time since the last event
	 * @return The time since the last event  that created this GameTime
	 */
	public TimeSpan getElapsedTime(){
		return elapsedTime;
	}
	
}
