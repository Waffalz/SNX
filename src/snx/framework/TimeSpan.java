package snx.framework;

/**
 * By Waffalz
 * Represents a duration of time
 */
public class TimeSpan {
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