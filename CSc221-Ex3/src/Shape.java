import java.awt.Color;

public abstract class Shape implements ShapeInterface {
	private int X;
	private int Y;
	private Color color;
	
	// Constructor for shape object
	public Shape(int X, int Y, Color color) {
		this.X = X;
		this.Y = Y;
		this.color = color;
	}
	
	// returns the x coordinate value of the shape object
	public int getX() {
		return X;
	}
	// returns the y coordinate value of the shape object
	public int getY() {
		return Y;
	}
	
	// returns the color of the shape object
	public Color getColor() {
		return color;
	}
	
	// set the x coordinate value of the shape object
	public void setX(int X) {
		this.X = X;
	}
	
	// set the y coordinate value of the shape object
	public void setY(int Y) {
		this.Y = Y;
	}
	
	// set the color of the shape object
	public void setColor(Color color) {
		this.color = color;
	}
	
	// move point (x,y) to point (x + delta x, y + delta y)
	public void moveTo(int newX, int newY) {
		X = getX() + newX;
		Y = getY() + newY;
	}
	
	// returns the string representation of the generic shape
	@Override
	public String toString() {
		return ("Generic Shape");
	}
}