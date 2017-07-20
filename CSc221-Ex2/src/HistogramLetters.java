// CSC221 Software Design Lab
// HistogramLetters.java by Chris Panican.

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

public class HistogramLetters extends JPanel {
	
	public HistogramLetters() {
		super();
		this.setPreferredSize(new Dimension(w,h));
	}
	
	public static int w = 700; // Panel width
	public static int h = 500; // Panel height
	int x = 30; // X coordinate
	int y = 55; // Y coordinate
	public static int selection; // For input. Cannot be greater than 26 or less than 0
	public static double[] probRoundVal = new double[27]; // Final probability values
	public static double sum = 0; // We will use this value to calculate all other letters
	
	// We will use these arrays as reference:
	// banned[] are the let of char that are not from the alphabet
	// white[] are white spaces, tabs, and return
	// alphabets[] contain letters from a-z not including capital letters
	public static char banned[] = {'1','2','3','4','5','6','7','8','9','0','/'};
	public static char white[] = {'\n','\t',' '};
	public static char alphabets[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	// The function counts individual letters from the text file and puts them in an array
	// then the function returns the array
	public static int[] letterScan(String fileName) {
		int occurrence[] = new int[27]; // This is where we will store our data. occurrence[26] is total character count
		String filename = fileName;
		try { 
			Scanner finput = new Scanner(new FileReader(filename));
			while(finput.hasNextLine()) {
				String input = finput.nextLine();
				char[] letters = input.toCharArray();
				for(int h = 0; h < input.length(); h++) {
					for(int h2 = 0; h2 < alphabets.length; h2++) {
						if(alphabets[h2] == letters[h])
							occurrence[h2] += 1;
					}
				}
				// Character counter
				// occurrence[26] will contain total character count
				for(int i = 0; i < input.length(); i++) {
					occurrence[26]++;
				}
			}
		}
		catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			System.exit(1);
		}
		return occurrence;
	}
	
	// Sorts values and alphabet corresponding to the values
	public static int[] valueSort(int[] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j = i +1; j < array.length; j++) {
				int tmp = 0;
				char tmp2;
				if(array[i] > array[j]) {
					// array acts like a guide to sort alphabets
					tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
					// Alphabets are sorted according to their values
					tmp2 = alphabets[i];
					alphabets[i] = alphabets[j];
					alphabets[j] = tmp2;
				}
			}
		}
		return array;
	}
	
	// Calculates the probability of each occurrences
	// Probability of letter = Frequency of letter / frequencies of all letters
	public static double[] probabilityCalc(int[] sortedVal) {
		double[] probArr = new double[sortedVal.length];
		for(int i=0; i<sortedVal.length; i++) {
		    probArr[i] = sortedVal[i];
		}
		for (int i = 0; i < sortedVal.length-1; i++) {
			probArr[i] = probArr[i] / sortedVal[26];
		}
		return probArr;
	}
	
	// Rounds the values in the array 4 decimal places
	// Used after probability was found
	public static double[] probRound(double[] probVal) {
		for (int i = 0; i < probVal.length-1; i++) {
			probVal[i] = (double)Math.round(probVal[i] * 10000)/10000;
		}
		return probVal;
	}
	
	// Color the graph according to input and letter probabilities
	public void paintComponent(Graphics g) {
		// This part of the code will draw the arcs
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("Arial", Font.BOLD, 16));
		g.drawString("Legend", 520, 30);
		
		double prevAngle = 0;
		g.setColor(Color.LIGHT_GRAY);
		g2d.fill(new Arc2D.Double(x, y, 400, 400, 0, 360, Arc2D.PIE));
		for (int i = 25; i >= 25-selection+1; i--) {
			//Draw the arcs
			g.setColor(new Color((int)(Math.random() * 0x1000000)));
			g2d.fill(new Arc2D.Double(x, y, 400, 400, prevAngle, (probRoundVal[i]*114.8)*Math.PI, Arc2D.PIE));
			prevAngle += (probRoundVal[i]*114.8)*Math.PI;
			//Draw the legend
			if (i >= 13) {
				g.drawString("██ " + alphabets[i] + " = " + probRoundVal[i], 460, ((25-i)*25)+55);
			} else {
				g.drawString("██ " + alphabets[i] + " = " + probRoundVal[i], 580, ((25-i)*25)-270);
			}
		}
		
		if (selection != 26) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("██ " + "All other letters" + " = " + (double)Math.round(sum * 10000)/10000, 460, 400);
		}
	}
	
	public static void main(String[] args) {
		// Input file name. Throw exception if file not found
//		JFrame fileInput = new JFrame("File Input");
//		String filename = JOptionPane.showInputDialog(fileInput, "Input file name (Example: xWords.txt)");
		String filename = "xWords.txt";
		
		int occurrenceArray[] = letterScan(filename);
		int sortedValues[] = valueSort(occurrenceArray);
		double probValues[] = probabilityCalc(sortedValues);
		HistogramLetters.probRoundVal = probRound(probValues);
		
		// Input which letters will be shown on histogram
		boolean wrongInput = true;
		do {
			JFrame nInput = new JFrame("Histogram Letters");
			selection = Integer.parseInt(JOptionPane.showInputDialog(nInput, "Input number n to show letter with most probability (1-26)"));
			if(selection < 0 || selection > 26) {
				JOptionPane.showMessageDialog(nInput, "Please enter numers between 1-26");
			} else {
				wrongInput = false;
			}
		} while (wrongInput);
		
		// This will reverse the array, then calculate the sum of letters that are not included on pie chart
		Collections.reverse(Arrays.asList(probRoundVal));
		for (int i = 25-selection; i >= 0; i--) {
			sum += probRoundVal[i];
		}
		
		// This will be the output pie chart
		JPanel output = new HistogramLetters();
		output.setPreferredSize(output.getPreferredSize());
		JFrame frame = new JFrame("Histogram Letters");
		frame.setResizable(false);
		frame.setContentPane(output);
		frame.setBackground(Color.WHITE);
		frame.pack();
		frame.setVisible(true);
	}
}