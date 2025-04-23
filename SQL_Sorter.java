import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

//vendor
//reference
//datetime
//receiptdb
//receipt_table

public class SQL_Sorter {

    static final String url = "jdbc:mysql://localhost:3306/receiptdb";
    static final String user = "Bleep";
    static final String password = "Bleep";

    public static Displays display = new Displays();


    //This function checks if ID doesn't exists because in the Insert function there is an if
    //statement. If the condition is true, it would execute the insert row
    //TO DO: MODIFY THE STATEMENT SELECT. DONE
    //TO DO: MODIFY THE REFERENCE. Done.
    public static boolean doesRefNotExist(Connection conn, String reference)
    {
        String statement = "SELECT * FROM receipt_table Where ref =?";
        try
        {
            PreparedStatement statementDoesIDAndRefNotExist = conn.prepareStatement(statement);
            statementDoesIDAndRefNotExist.setString(1, reference.strip());
            ResultSet resultSet = statementDoesIDAndRefNotExist.executeQuery();
            while(resultSet.next())
            {
                if (resultSet.getString("ref").equals(reference.strip()))
                {
                    return false;
                }
            }
            resultSet.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    //TO DO: MODIFY THE STATEMENT SELECT.
    //To do: Modify the reference.
    public boolean isRefValid(String reference)
    {
        String statementIDAndRefFind = "SELECT * FROM receipt_table WHERE ref =?";
        try
        {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement statementDoesIDNotExist = conn.prepareStatement(statementIDAndRefFind);
            statementDoesIDNotExist.setString(1, reference.strip());
            ResultSet resultSet = statementDoesIDNotExist.executeQuery();
            while(resultSet.next())
            {
                if (resultSet.getString("ref").equals(reference.strip()))
                {
                    return false;
                }
            }
            resultSet.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    //To do: modify reference.done
    public static boolean doesReferenceExist(Connection conn, String reference, String statement)
    {
        try
        {

            PreparedStatement statementDoesIDNotExist = conn.prepareStatement(statement);
            statementDoesIDNotExist.setString(1, reference.strip());
            ResultSet resultSet = statementDoesIDNotExist.executeQuery();
            while(resultSet.next())
            {
                if (resultSet.getString("ref").equals(reference))
                {
                    return true;
                }
                else
                {
                    System.out.println("Error Reference does not exist");
                }
            }
            resultSet.close();
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    //Find Entry
    //TO DO: MODIFY THE SELECT STATEMENT. Done
    //to do: modify the reference and datetime. done
    public void findEntry(String reference) {
        String statementFindEntry = "SELECT * From receipt_table Where ref=?";
        boolean doesReferenceExist;

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn  = DriverManager.getConnection(url, user, password);
            doesReferenceExist = doesReferenceExist(conn, reference, statementFindEntry);
            if (doesReferenceExist)
            {
                PreparedStatement statementSearch = conn.prepareStatement(statementFindEntry);
                statementSearch.setString(1, reference.strip());
                ResultSet resultSet = statementSearch.executeQuery();

                while (resultSet.next())
                {
                    display.displaySearchItem(resultSet.getString("ref"), resultSet.getString("vendor"),
                            resultSet.getTimestamp("date_time").toString());
                }
                resultSet.close();
            }
            else
            {
                System.out.println("Reference does not exist");
            }
        }
        catch (SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        catch (ClassNotFoundException cex)
        {
            System.out.println(cex.getMessage());
        }
    }

    //This function adds entries
    //TO DO: MODIFY THE insert statement.
    public void addEntry(String vendor, String reference, LocalDateTime dateTime)
    {
        boolean doesRefNotExist;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            doesRefNotExist = doesRefNotExist(conn, reference);
            if (doesRefNotExist)
            {
                String sql = "INSERT INTO receipt_table VALUES (NULL,?,?,?)";
                PreparedStatement statementInsertItem = conn.prepareStatement(sql);
                statementInsertItem.setString(1, vendor.strip());
                statementInsertItem.setString(2, reference.strip());
                statementInsertItem.setObject(3, Timestamp.valueOf(dateTime));

                statementInsertItem.execute();

                System.out.println("Item has been inserted!");
            }
            else
            {
                System.err.println("Error! Item Already exists!");
            }
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        catch(ClassNotFoundException cex)
        {
            System.out.println("Class not found!");
            System.out.println(cex.getMessage());
            Logger.getLogger(SQL_Sorter.class.getName()).log(Level.SEVERE, null, cex);
        }

    }

    //TO DO: MODIFY THE SELECT AND UPDATE. Done
    //to do: modify reference. done
    public void editEntry(String reference, String newReference)
    {
        String statementEditEntry = "UPDATE receipt_table set ref ='" + newReference.strip()  + "' where ref = '" + reference.strip() + "'";
        String statementFindEntry = "SELECT * From receipt_table where ref =?";
        boolean canEntryEdited;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            canEntryEdited = doesReferenceExist(conn, reference.strip(), statementFindEntry);
            if (canEntryEdited)
            {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(statementEditEntry);
                System.out.println("Row Edited!");
            }
            else
            {
                display.rowCouldNotBeFound();
                display.displayWarning(4);
            }

        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        catch(ClassNotFoundException cex)
        {
            System.out.println("Class not found!");
            System.out.println(cex.getMessage());
            Logger.getLogger(SQL_Sorter.class.getName()).log(Level.SEVERE, null, cex);
        }
    }

    //TO DO: MODIFY DELETE AND SELECT. Done
    //to do modify reference. done
    public void deleteEntry(String reference)
    {
        String statementDelete = "DELETE FROM receipt_table Where ref =?";
        String findReference = "SELECT * FROM receipt_table where ref =?";
        boolean doesItExist;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            doesItExist = doesReferenceExist(conn, reference, findReference);
            if (doesItExist) {

                PreparedStatement statementDeleting = conn.prepareStatement(statementDelete);
                statementDeleting.setString(1, reference.strip());
                statementDeleting.executeUpdate();
                System.out.println("Row deleted!");
            }
            else
            {
                display.rowCouldNotBeFound();
                display.displayWarning(4);
            }
        }
        catch(SQLException sqlEx)
        {
            System.out.println(sqlEx.getMessage());
        }
        catch(ClassNotFoundException cex)
        {
            System.out.println("Class not found!");
            System.out.println(cex.getMessage());
            Logger.getLogger(SQL_Sorter.class.getName()).log(Level.SEVERE, null, cex);
        }
    }
}
