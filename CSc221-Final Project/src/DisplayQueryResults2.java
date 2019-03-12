// Fig. 18.29: DisplayQueryResults.java
// This program displays the ResultSet returned by a
// query on the Books database.
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DisplayQueryResults2 extends JFrame {
   // java.sql types needed for database processing
   private Connection connection;
   private Statement statement;
   private ResultSet resultSet;
   private ResultSetMetaData rsMetaData;

   // javax.swing types needed for GUI
   private JTable table;
   private JTextArea inputQuery;
   private JButton submitQuery;
    
   public DisplayQueryResults2() 
   {   
      super( "Enter Query. Click Submit to See Results." );

      // The URL specifying the Books database to which
      // this program connects using JDBC to connect to a
      // Microsoft ODBC database.
      String url = "jdbc:mysql://localhost:3306/books";  
      String username = "deitel";
      String password = "deitel";

      // Load the driver to allow connection to the database
      try {
         Class.forName( "com.mysql.jdbc.Driver" );

         connection = DriverManager.getConnection( 
            url, username, password );
      } 
      catch ( ClassNotFoundException cnfex ) {
         System.err.println( 
            "Failed to load JDBC/ODBC driver." );
         cnfex.printStackTrace();
         System.exit( 1 );  // terminate program
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to connect" );
         sqlex.printStackTrace();
         System.exit( 1 );  // terminate program
      }

      // If connected to database, set up GUI      
      inputQuery =
         new JTextArea( "SELECT * FROM Authors", 4, 30 );
      submitQuery = new JButton( "Submit query" );
      submitQuery.addActionListener(
         new ActionListener() {
            public void actionPerformed( ActionEvent e ) 
            {
               getTable();
            }
         }   
      );

      JPanel topPanel = new JPanel();
      topPanel.setLayout( new BorderLayout() );
      topPanel.add( new JScrollPane( inputQuery), 
                    BorderLayout.CENTER );
      topPanel.add( submitQuery, BorderLayout.SOUTH );
 
      table = new JTable( 4, 4 );

      Container c = getContentPane();
      c.setLayout( new BorderLayout() );
      c.add( topPanel, BorderLayout.NORTH );
      c.add( table, BorderLayout.CENTER );      

      getTable();

      setSize( 500, 500 );
      show();
   }

   private void getTable()
   {
      try {
         String query = inputQuery.getText();

         statement = connection.createStatement();
         resultSet = statement.executeQuery( query );         
         displayResultSet( resultSet );
      }
      catch ( SQLException sqlex ) {
         sqlex.printStackTrace();
      }
   }

   private void displayResultSet( ResultSet rs )
      throws SQLException
   {
      // position to first record
      boolean moreRecords = rs.next();  

      // If there are no records, display a message
      if ( ! moreRecords ) {
         JOptionPane.showMessageDialog( this, 
            "ResultSet contained no records" );
         setTitle( "No records to display" );
         return;
      }

      Vector columnHeads = new Vector();
      Vector rows = new Vector();

      try {
         // get column heads
         ResultSetMetaData rsmd = rs.getMetaData();
      
         for ( int i = 1; i <= rsmd.getColumnCount(); ++i ) 
            columnHeads.addElement( rsmd.getColumnName( i ) );

         // get row data
         do {
            rows.addElement( getNextRow( rs, rsmd ) ); 
         } while ( rs.next() );

         // display table with ResultSet contents
         table = new JTable( rows, columnHeads );
         JScrollPane scroller = new JScrollPane( table );
         Container c = getContentPane();
         c.remove( 1 );
         c.add( scroller, BorderLayout.CENTER );
         c.validate();
      }
      catch ( SQLException sqlex ) {
         sqlex.printStackTrace();
      }
   }

   private Vector getNextRow( ResultSet rs, 
                              ResultSetMetaData rsmd )
       throws SQLException
   {
      Vector currentRow = new Vector();
      
      for ( int i = 1; i <= rsmd.getColumnCount(); ++i )
         switch( rsmd.getColumnType( i ) ) {
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
                  currentRow.addElement( rs.getString( i ) );
               break;
            case Types.INTEGER:
                  currentRow.addElement( 
                     new Long( rs.getLong( i ) ) );
               break;
            default: 
               System.out.println( "Type was: " + 
                  rsmd.getColumnTypeName( i ) );
         }
      
      return currentRow;
   }

   public void shutDown()
   {
      try {
         connection.close();
      }
      catch ( SQLException sqlex ) {
         System.err.println( "Unable to disconnect" );
         sqlex.printStackTrace();
      }
   }

   public static void main( String args[] ) 
   {
      final DisplayQueryResults2 app = 
         new DisplayQueryResults2();

      app.addWindowListener( 
         new WindowAdapter() {
            public void windowClosing( WindowEvent e ) 
            {  
               app.shutDown();
               System.exit( 0 );
            }
         }
      );
   }
}

/**************************************************************************
 * (C) Copyright 1999 by Deitel & Associates, Inc. and Prentice Hall.     *
 * All Rights Reserved.                                                   *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/