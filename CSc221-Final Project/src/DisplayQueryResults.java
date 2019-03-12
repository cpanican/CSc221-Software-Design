import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.ClassNotFoundException;

public class DisplayQueryResults extends JFrame {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private JTable table;
	private JComboBox inputQuery;
	private JButton submitQuery;
	private JTextField input;
	public DisplayQueryResults() {
		super( "Select Query. Click Submit to See Results" );
		// The URL specifying the books database to which this program connects to using JDBC
		String url =  "jdbc:mysql://localhost:3306/books";
		// Load the driver to allow connection to the database
		try
		{
			Class.forName( "com.mysql.jdbc.Driver" );
			connection = DriverManager.getConnection(url, "deitel", "deitel");
		}
		catch (ClassNotFoundException cnfex)
		{
			System.err.println("Failed to load JDBC driver.");
			cnfex.printStackTrace();
			System.exit(1); // terminate the program
		}
		catch (SQLException sqlex)
		{
			System.err.println("Unable to connect");
			sqlex.printStackTrace();
			System.exit(1); // terminate the program
		}
		String names[] = { "All authors", "All titles", 
				 "A specific author", "A specific title"};
		// If connected to database, set up GUI
		inputQuery = new JComboBox(names);
		submitQuery = new JButton("Submit query");
		submitQuery.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						getTable();
					}
				}
		);
		JPanel topPanel = new JPanel();
		input = new JTextField(20);
		input.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							String query = input.getText();
							statement = connection.createStatement();
							resultSet = statement.executeQuery(query);
							displayResultSet(resultSet);
						}
						catch (SQLException sqlex)
						{
							sqlex.printStackTrace();
						}
					}
				});
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout());
		centerPanel.add(new JLabel("Enter query, author or title"));
		centerPanel.add(input);
		topPanel.setLayout(new BorderLayout());
		topPanel.add(inputQuery, BorderLayout.NORTH);
		topPanel.add(centerPanel, BorderLayout.CENTER);
		topPanel.add(submitQuery, BorderLayout.SOUTH);
		table = new JTable( 4, 4 );
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(topPanel, BorderLayout.NORTH);
		c.add(table, BorderLayout.CENTER);
		
		getTable();
		setSize(500, 500);
		setVisible(true);
	} // end constructor
	
	private void getTable()
	{
		try
		{
			int selection = inputQuery.getSelectedIndex();
			String query = null;
			switch (selection)
			{
			case 0:
				query = "SELECT * FROM Authors";
				break;
			case 1:
				query = "SELECT * FROM Titles";
				break;
			case 2:
				query = "SELECT Authors.LastName, Authors.FirstName, "
						+ "Titles.Title, Titles.Price, "
						+ "Titles.ISBN FROM "
						+ "Titles INNER JOIN (AuthorISBN INNER JOIN Authors ON"
						+ " AuthorISBN.AuthorID = Authors.AuthorID) ON "
						+ "Titles.ISBN = AuthorISBN.ISBN WHERE Authors.LastName"
						+ " = '"
						+ input.getText() + "' ORDER BY "
						+ "Authors.LastName, Authors.FirstName ASC";
				break;
			case 3:
				query = "SELECT Title.PublisherName, Titles.Title, " 
						+ "Titles.Price, Titles.ISBN FROM Titles INNER JOIN "
						+ "Publishers ON Publishers.PublisherID = "
						+ "Titles.PublisherID WHERE Publishers.PublisherName = '"
						+ input.getText() + "' ORDER BY Titles.Title ASC";
				break;
			}
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			displayResultSet(resultSet);
		} // end try
		catch (SQLException sqlex)
		{
			sqlex.printStackTrace();
		}
	} // end method getTable
	
	private void displayResultSet (ResultSet rs) throws SQLException
	{
		// position to first record
		boolean moreRecords = rs.next();
		// If there are no records, display a message
		if (!moreRecords)
		{
			JOptionPane.showMessageDialog(this, "ResultSet contained no records" );
			setTitle("No records to display");
			return;
		}
		// here
		Vector columnHeads = new Vector();
		Vector rows = new Vector();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; 1 <= rsmd.getColumnCount(); ++i) {
				columnHeads.addElement(rsmd.getColumnName(i));
			} do {
				rows.addElement(getNextRow(rs, rsmd));
			} while (rs.next());
			table = new JTable(rows, columnHeads);
			JScrollPane scroller = new JScrollPane(table);
			Container c = getContentPane();
			c.remove(1);
			c.add(scroller, BorderLayout.CENTER);
			c.validate();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}
	
	private Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector currentRow = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
			switch(rsmd.getColumnType(i)) {
			case Types.VARCHAR:
			case Types.LONGNVARCHAR:
				currentRow.addElement(rs.getString(i));
				break;
			case Types.INTEGER:
				currentRow.addElement(new Long(rs.getLong(i)));
				break;
			case Types.REAL:
				currentRow.addElement(new Float(rs.getDouble(i)));
				break;
			default:
				System.out.println("Type was: " + rsmd.getColumnTypeName(i));
			}
		}
		return currentRow;
	}
	
	public void shutDown() {
		try {
			connection.close();
		} catch (SQLException sqlex) {
			System.err.println("Unable to disconnect");
			sqlex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		final DisplayQueryResults app = new DisplayQueryResults();
		app.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				app.shutDown();
				System.exit(0);
			}
		});
	}
}




























