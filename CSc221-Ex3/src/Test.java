import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;
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
		Scanner input = new Scanner(System.in);
		
		System.out.println("Press 1 for draw Circle");
		System.out.println("Press 2 for draw Triangle");
		int choice = input.nextInt();
		
		switch(choice) {
			case 1:
				Shape s = new Circle(50 , 50 , 100, Color.YELLOW);
				s.draw(g);
				break;
				
			case 2:
				Shape s2 = new Triangle(300, 300, Color.GREEN, 100, 100, 100);
				s2.draw(g);
				break;
				
			default:
				System.out.println("Wrong");
				break;
        }
	}
}
