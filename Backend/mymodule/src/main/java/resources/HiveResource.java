package resources;

import java.sql.*;

public class HiveResource {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        //replace "hive" here with the name of the user the queries should run as
        Connection con = DriverManager.getConnection("jdbc:hive2://localhost:10000/default", "hive", "hive");
        Statement stmt = con.createStatement();
        String tableName = "testHiveDriverTable";
        stmt.execute("drop table if exists " + tableName);
        stmt.execute("create table " + tableName + " (key int, value string)");
        // show tables
         String insert = "insert into employee(eid) values(1)";
        String sql = ("select * from  employee");
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
    }
}