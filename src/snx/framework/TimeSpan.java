package snx.framework;

/**
 * Represents a duration of time
 */
public class TimeSpan {
	//amount of time in milliseconds
	private long value;
	
	/**
	 * Creates a new TimeSpan with a specified time in milliseconds
	 * @param mil Time in milliseconds
	 */
	public TimeSpan(long mil){
		value = mil;
	}
	
	/**
	 * Gets the amount of time in milliseconds
	 * @return Time in milliseconds
	 */
	public long getTimeMillis(){
		return value;
	}
	
	/**
	 * Gets the amount of time in seconds
	 * @return Time in seconds
	 */
	public float getTimeSeconds(){
		return ((float)value)/1000;
	}
	
	/**
	 * Gets the amount of time in minutes
	 * @return Time in minutes
	 */
	public float getTimeMinutes(){
		return getTimeSeconds()/60;
	}
	
}