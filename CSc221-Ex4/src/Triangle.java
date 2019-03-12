import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape {
	private double sideA;
	private double sideB;
	private double sideC;
	
	public Triangle(int x, int y, Color color, double sideA, double sideB, double sideC) { // constructor 
		super(x, y, color);
		this.sideA = sideA;
		this.sideB = sideB;
		this.sideC = sideC;
	}
	
	public boolean isTriangle () {
		double max = Math.max(Math.max(sideA, sideB), sideC);
		return sideA > 0 && sideB > 0 && sideC > 0 && (max < sideA + sideB + sideC - max);
	}
	
	public double getAngleC() { 
		double oc = sideC / 2;
		double angleC = Math.toDegrees(Math.acos(oc/sideA));
		return angleC;
	}
	
	public double getAngleB() {
		double ob = sideC / 2;
		double angleB = Math.toDegrees(Math.acos(ob/sideB));
		return angleB;
	}
	
	public double getAngleA() {
		double angleA = 180 - (getAngleB() + getAngleC());
		return angleA;
	}
	
	public double area() {
		double s = (sideA + sideB + sideC) / 2;
		return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
	}
	
	public double perimeter() {
		// P = a + b + c
		return sideA+ sideB + sideC;
	}
	
	@Override
	public String toString() {
		return " Triangle  : ";
		//     + "sideA(" + vertexA.X() + "," + vertexA.Y() + "), "
		  //   + "B(" + vertexB.X() + "," + vertexB.Y() + "), "
		    // + "C(" + vertexC.X() + "," + vertexC.Y() + ").";
	}
	
	public double getSideA() {
		return sideA;
	}
	
	public double getSideB() {
		return sideB;
	}
	
	public double getSideC() {
		return sideC;
	}
	
	@Override
	public void draw(Graphics g) {
		double p2Y = 85 + super.getY() + Math.sqrt((sideA * sideA) - ((sideC / 2)* (sideC / 2)) );
		double p2X = 114 + super.getX() - (sideC/2);
		double p3X = 114 + super.getX() + (sideC/2);
		int [] x= {114 + super.getX(),(int) p2X,(int) p3X};
		int [] y= {85 + super.getY(),(int) p2Y,(int) p2Y};
		
		g.setColor(super.getColor());
		g.fillPolygon(x, y, 3);
	}
}

