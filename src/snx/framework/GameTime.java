package snx.framework;


/**
 * By Waffalz
 * A C# more manageable way of working with times
 */
public class GameTime {
	
	private TimeSpan currentTime;
	
	private TimeSpan elapsedTime;
	
	public GameTime(TimeSpan current, TimeSpan elapsed){
		currentTime = current;
		elapsedTime = elapsed;
	}
	
	public GameTime(long current, long elapsed){
		currentTime = new TimeSpan(current);
		elapsedTime = new TimeSpan(elapsed);
	}
	
	public TimeSpan getTime(){
		return currentTime;
	}
	
	public TimeSpan getElapsedTime(){
		return elapsedTime;
	}
	
}
