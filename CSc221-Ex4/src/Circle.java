import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	final double pi = Math.PI;
	private double radius;
	
	public Circle(int x , int y , int radius , Color color) { // constructor
		super(x, y, color);
		this.radius = radius;
	}
	
	public double getArea() {
		return Math.PI * radius * radius;
	}
	
	public int getRadius() {
		return (int) radius;
	}
	
	public void setRadius(double newRadius) {
		this.radius = newRadius;
	}
	
	@Override
	public String toString() {
		return "Circle[radius="+radius+",Perimater="+perimeter()+",area="+area()+"]";
	}
	
	public double area() {
		return pi * Math.pow(radius, 2);
	}
	
	public double perimeter() {
		return 2 * pi * radius;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(super.getColor());
		g.fillOval(super.getX() ,super.getY(), 2 * getRadius(), 2 * getRadius());
	} 
}
