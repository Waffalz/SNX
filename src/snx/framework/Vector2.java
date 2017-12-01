package snx.framework;

/**
 * Represents 2-dimensional coordinates
 */
public class Vector2 {
	public float x;
	public float y;
	
	/**
	 * Default constructor; creates a new Vector2 of X and Y 0
	 */
	public Vector2(){
		x = 0;
		y = 0;
	}
	
	/**
	 * Creates a new Vector2 of specified X and Y ocmponents
	 * @param x The X component of the Vector2
	 * @param y The Y component of the Vector2
	 */
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Creates a copy of an existing Vector2
	 * @param toCopy The Vector2 to copy
	 */
	public Vector2(Vector2 toCopy){
		this.x = toCopy.x;
		this.y = toCopy.y;
	}
	
	/**
	 * returns the length of the vector. You know, Pythagorean theorem stuff
	 * @return The length of the vector
	 */
	public float getLength(){
		return (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * returns the length of the vector, but left squared because finding roots takes a lot of true
	 * @return The length of the vector squared
	 */
	public float getLength2(){
		return (float)(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/*
	 * returns a copy of this, but with a length of 1.
	 */
	
	//TODO: implement more overloads for math functions taking two float vals
	
	public Vector2 unit(){
		return divide(getLength());
	}
	
	public Vector2 add(Vector2 toAdd){
		return new Vector2(this.x + toAdd.x, this.y + toAdd.y);
	}
	
	public Vector2 add(float toAdd){
		return new Vector2(this.x + toAdd, this.y + toAdd);
	}
	
	public Vector2 add(float toAddx, float toAddy){
		return new Vector2(this.x + toAddx, this.y + toAddy);
	}
	
	public Vector2 subtract(Vector2 toSub){
		return new Vector2(this.x - toSub.x, this.y - toSub.y);
	}
	
	public Vector2 subtract(float toSub){
		return new Vector2(this.x - toSub, this.y - toSub);
	}
	
	public Vector2 subtract(float toSubx, float toSuby){
		return new Vector2(this.x - toSubx, this.y - toSuby);
	}
	
	public Vector2 multiply(Vector2 toMult){
		return new Vector2(this.x * toMult.x, this.y * toMult.y);
	}
	
	public Vector2 multiply(float toMult){
		return new Vector2(this.x * toMult, this.y * toMult);
	}
	
	public Vector2 multiply(float toMultx, float toMulty){
		return new Vector2(this.x * toMultx, this.y * toMulty);
	}
	
	public Vector2 divide(Vector2 toDiv){
		return new Vector2(this.x / toDiv.x, this.y / toDiv.y);
	}
	
	public Vector2 divide(float toDiv){
		return new Vector2(this.x / toDiv, this.y / toDiv);
	}
	
	public Vector2 divide(float toDivx, float toDivy){
		return new Vector2(this.x / toDivx, this.y / toDivy);
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	
	public Object clone(){
		return new Vector2(this);
	}
}
