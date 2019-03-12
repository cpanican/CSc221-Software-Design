import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel {
	
	public static void addComponentsToPane(Container pane) {
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		
		JButton button1 = new JButton("A ∪  B");
		button1.setAlignmentX(Component.LEFT_ALIGNMENT);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button1.setActionCommand("b1");
			}
		});
		pane.add(button1);
		
		JButton button2 = new JButton("A - B");
		button2.setAlignmentX(Component.LEFT_ALIGNMENT);
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button2.setActionCommand("b2");
			}
		});
		pane.add(button2);
		
		JButton button3 = new JButton("B - A");
		button3.setAlignmentX(Component.LEFT_ALIGNMENT);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button3.setActionCommand("b3");
			}
		});
		pane.add(button3);
		
		JButton button4 = new JButton("A = B");
		button4.setAlignmentX(Component.LEFT_ALIGNMENT);
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button4.setActionCommand("b4");
			}
		});
		pane.add(button4);
		
		JButton button5 = new JButton("A ∩ B = Ø");
		button5.setAlignmentX(Component.LEFT_ALIGNMENT);
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button5.setActionCommand("b5");
			}
		});
		pane.add(button5);
		
		JButton button6 = new JButton("A ∩ B ≠ Ø");
		button6.setAlignmentX(Component.LEFT_ALIGNMENT);
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button6.setActionCommand("b6");
			}
		});
		pane.add(button6);
	}
	
	public void paintComponent(Graphics g, JButton button) {
		if("b1".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		if("b2".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		if("b3".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		if("b4".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		if("b5".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		if("b6".equals(button)) {
			Shape circle1 = new Circle(200, 0, 150, Color.BLUE);
			circle1.draw(g);
			Shape circle2 = new Circle(300, 0, 150, Color.RED);
			circle2.draw(g);
		}
		else {
			
		}
	}

    public static void main(String[] args) {
    	JFrame frame = new JFrame("BoxLayoutDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponentsToPane(frame.getContentPane());
		Test shapeTest = new Test();
		frame.add(shapeTest);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setResizable(false);

    }
}