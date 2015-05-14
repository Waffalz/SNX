package framework;


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

class TimeSpan {
	//amount of time in milliseconds
	private long value;
	
	public TimeSpan(long mil){
		value = mil;
	}
	
	public long getTimeMillis(){
		return value;
	}
	
	public float getTimeSeconds(){
		return ((float)value)/1000;
	}
	
	public float getTimeMinutes(){
		return getTimeSeconds()/60;
	}
	
}