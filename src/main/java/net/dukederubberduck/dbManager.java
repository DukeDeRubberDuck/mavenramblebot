package net.dukederubberduck;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;

class dbManager implements Textconstants
{
    Connection conn = null;
    Statement stmt = null;

    ArrayList<String> ALnoun1 = new ArrayList<>();
    ArrayList<String> ALnoun2 = new ArrayList<>();
    ArrayList<String> ALverb = new ArrayList<>();
    ArrayList<String> ALadj = new ArrayList<>();
    ArrayList<String> ALnoun3 = new ArrayList<>();

    public void dbInput (String input, wordtype wt)
    {
        try
        {
            //Class.forName(JDBC_DRIVER);
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO words." + wt + " VALUES ('" + input + "')";
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }
        catch (SQLException e) { e.printStackTrace();}
        catch (Exception e1) {e1.printStackTrace();}
        finally
        {
            try
            {
                if(stmt!=null) stmt.close();
            }
            catch(SQLException e2) { }
            try
            {
                if(conn!=null) conn.close();
            }
            catch(SQLException s3) {s3.printStackTrace();}
        }

    }

    private void dbCheck ()
    {
        ALnoun1.clear();
        ALnoun2.clear();
        ALverb.clear();
        ALadj.clear();
        ALnoun3.clear();

        try
        {
            //Class.forName(JDBC_DRIVER);
            conn = getConnection();
            stmt = conn.createStatement();

            for (wordtype wt : wordtype.values())
            {
                String sql = "SELECT input FROM words." + wt;
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next())
                {
                    String s = rs.getString("input");
                    switch (wt)
                    {
                        case noun1: ALnoun1.add(s); break;
                        case noun2: ALnoun2.add(s); break;
                        case verb: ALverb.add(s); break;
                        case adj: ALadj.add(s); break;
                        case noun3: ALnoun3.add(s); break;
                    }
                }
                rs.close();
            }
        }
        catch (SQLException se) {se.printStackTrace();}
        catch (Exception e) {e.printStackTrace();}
        finally
        {
            try
            {
                if (stmt != null) stmt.close();
            }
            catch (SQLException se2){ }

            try
            {
                if (conn != null) conn.close();
            }
            catch (SQLException se) {se.printStackTrace();}
        }
    }

    public boolean dbCheckForDuplicate(String input, wordtype wt)
    {
        ArrayList<String> base = new ArrayList<>();
        boolean answer = true;

        try
        {
            //Class.forName(JDBC_DRIVER);
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT input FROM words." + wt;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                base.add(rs.getString("input"));
            }
            if (base.contains(input))
            {
                answer = false;
            }
            rs.close();
        }
        catch (SQLException se){ se.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }
        finally
        {
            try
            {
                if (stmt != null) stmt.close();
            }
            catch (SQLException se2) { }
            try
            {
                if (conn != null) conn.close();
            }
            catch (SQLException se){ se.printStackTrace(); }
        }
        return answer;
    }

    public String dbGetRandomPhrase ()
    {
        dbCheck();

        int r1 = (int) (Math.random() * ALnoun1.size());
        int r2 = (int) (Math.random() * ALnoun2.size());
        int r3 = (int) (Math.random() * ALverb.size());
        int r4 = (int) (Math.random() * ALadj.size());
        int r5 = (int) (Math.random() * ALnoun3.size());

        return ALnoun1.get(r1).substring(0,1).toUpperCase() +  ALnoun1.get(r1).substring(1).toLowerCase()
                + "-" + ALnoun2.get(r2) +
                " " + ALverb.get(r3) +
                " " + ALadj.get(r4) +
                " " + ALnoun3.get(r5);
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv(DB_URL));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
