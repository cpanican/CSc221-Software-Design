import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {
	public static void main(String[] args) {
		JFrame f = new JFrame("Shape");
		Test shapeTest = new Test();
		f.add(shapeTest);
		f.setSize(900, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setResizable(false);
	}

	public void paintComponent(Graphics g) {
		Shape circle1 = new Circle(140, 30, 300, Color.YELLOW);
		circle1.draw(g);
		
		Shape triangle1 = new Triangle(326, -55, Color.GREEN, 520, 520, 520);
		triangle1.draw(g);
		
		Shape circle2 = new Circle(290, 180, 150, Color.RED);
		circle2.draw(g);
		
		Shape triangle2 = new Triangle(326, 95, Color.ORANGE, 262, 262, 262);
		triangle2.draw(g);
		
		Shape circle3 = new Circle(365, 255, 75, Color.BLUE);
		circle3.draw(g);
	}
}
