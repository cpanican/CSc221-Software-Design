import java.awt.Color;

public abstract class Shape implements ShapeInterface {
	private int X;
	private int Y;
	private Color color;
	
	public Shape(int X, int Y, Color color) {
		this.X = X;
		this.Y = Y;
		this.color = color;
	}
	
	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public Color getColor() {
		return color;
	}
	
	public void setX(int X) {
		this.X = X;
	}
	
	public void setY(int Y) {
		this.Y = Y;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void moveTo(int nX, int nY) {
		X = getX() + nX;
		Y = getY() + nY;
	}
	
	@Override
	public String toString() {
		return ("Shape");
	}
}
