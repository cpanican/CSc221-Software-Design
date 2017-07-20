// CSC221 Software Design Lab
// VennDiagram.java by Chris Panican.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class VennDiagram extends JPanel {
	
	public VennDiagram() {
		super();
		this.setPreferredSize(new Dimension(w,h));
	}
	
	public static int w = 650; // Panel width
	public static int h = 500; // Panel height
	public static int option = 0; // For switch statement
	public static int x1 = 0; // Circle1
	public static int x2 = 0; // Circle2
	int y = 75; // Y coordinate
	int d = 300; // Diameter
	
	public void paintComponent(Graphics g) {
		g.setXORMode(Color.WHITE); // Color intersecting area
		// Left Circle
		g.setColor(Color.BLUE);
		g.fillOval(x1, y, d, d);
		// Right Circle
		g.setColor(Color.RED);
		g.fillOval(x2, y, d, d);
		// Draw Text based on input
		Font font = new Font("Arial", Font.PLAIN, w/20);
		g.setFont(font);
		g.setColor(Color.BLACK);
		switch(option) {
			case 1:
				g.drawString("A = B Intersection", (int)(w/3), 460);
				g.drawString("C", (int)(w/2.11), 240);
				break;
			case 2:
				g.drawString("A ∩ B = Ø", (int)((w/3)+40), 460);
				g.drawString("A", (int)((w/2)-d/1.8), 50);
				g.drawString("B", (int)((w/2)+d/2), 50);
				break;
			case 3:
				g.drawString("A ∩ B Intersection", (int)(w/3), 460);
				g.drawString("A", (int)((w/2)-d/2), 50);
				g.drawString("B", (int)((w/2)+d/3), 50);
				g.drawString("C", (int)(w/2.11), 240);
				break;
		}
	}

	public static void main(String[] args) {
		// Input activity. User will select 3 choices
		JPanel input = new JPanel();
		JRadioButton button1 = new JRadioButton("A = B");
		JRadioButton button2 = new JRadioButton("A ∩ B = Ø");
		JRadioButton button3 = new JRadioButton("A ∩ B ≠ Ø");
		input.add(button1);
		input.add(button2);
		input.add(button3);
		JOptionPane.showOptionDialog(null, input, "Please pick one condition.", JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (button1.isSelected()) {
			VennDiagram.x1 = w/4;
			VennDiagram.x2 = w/4;
			VennDiagram.option = 1;
		} else if (button2.isSelected()) {
			VennDiagram.x1 = w/38;
			VennDiagram.x2 = w/2;
			VennDiagram.option = 2;
		} else if (button3.isSelected()){
			VennDiagram.x1 = w/10;
			VennDiagram.x2 = (w/2)-50;
			VennDiagram.option = 3;
		} else {
			System.exit(0);
		}
		// Output Activity. Display based on input
		JPanel output = new VennDiagram();
		output.setPreferredSize(output.getPreferredSize());
		JFrame frame = new JFrame("Venn Diagram");
		frame.setResizable(false);
		frame.setContentPane(output);
		frame.pack();
		frame.setVisible(true);
	}
}