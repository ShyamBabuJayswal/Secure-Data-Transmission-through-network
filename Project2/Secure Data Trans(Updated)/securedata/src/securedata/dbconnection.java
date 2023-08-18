/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package securedata;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;


public class dbconnection {

    static Connection con = null;
    PreparedStatement pst = null;

    public static Connection makeConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/securedata", "root", "root");

        } catch (Exception e) {
        }
        return con;
    }
}
